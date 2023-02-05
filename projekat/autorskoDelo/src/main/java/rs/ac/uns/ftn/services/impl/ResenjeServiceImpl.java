package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtilsResenje;
import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.a1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaResenja;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.jaxb.resenje.StatusResenja;
import rs.ac.uns.ftn.jaxb.resenje.TOdobren;
import rs.ac.uns.ftn.jaxb.resenje.TSluzbenik;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.repository.ResenjeRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.services.ResenjeService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class ResenjeServiceImpl implements ResenjeService {

	@Autowired
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	@Autowired
	private MailingService emailService;
	
	public static final String PATH = "src/main/resources/xslt/";
	public static final String XSL_FILE = "src/main/resources/xslt/Resenje.xsl";

	@Override
	public String saveNewFile(Resenje resenje, String user) {
		TSluzbenik sluzbenik = getSluzbenik(user);
		resenje.setSluzbenik(sluzbenik);
		
		String documentId = generateDocumentId();
		ZahtevZaAutorskoDelo zahtev = autorskoDeloService.getZahtevZaAutorskoDeloById(resenje.getIdAutorskogDela().getIdA());
		
		if(zahtev.getStatus() != StatusZahteva.NEOBRADJEN) {
			throw new BadRequestException(ErrorMessageConstants.DOCUMENT_ALREADY_HAS_RESENJE);
		}

		if(resenje.getStatus() == StatusResenja.ODOBREN) {
			zahtev.setStatus(StatusZahteva.ODOBREN);
			if(resenje.getDodatak() instanceof TOdobren) {
				zahtev.setBrojPrijave(((TOdobren) resenje.getDodatak()).getSifra());
			}
		}
		if(resenje.getStatus() == StatusResenja.ODBIJEN) {
			zahtev.setStatus(StatusZahteva.ODBIJEN);
		}

		zahtev.setIdAutorskogDela(resenje.getIdAutorskogDela());
		autorskoDeloService.saveFile(zahtev, resenje.getIdAutorskogDela().getIdA());
		resenjeRepository.saveResenje(resenje, documentId);
		
//		sendAsPdfToEmail(resenje, zahtev);
		
		return documentId;
	}


	private TSluzbenik getSluzbenik(String user) {
		String[] tokens = user.split(",");
		String name = tokens[2];
		String surname = tokens[1];
		
		TSluzbenik sluzbenik = new TSluzbenik();
		sluzbenik.setIme(name);
		sluzbenik.setPrezime(surname);
		return sluzbenik;
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = resenjeRepository.getLenghtOfCollection();
		return "R" + String.valueOf(curretnNumber + 1); 
	}

	@Override
	public ListaResenja findAll() throws XMLDBException, JAXBException {
		ResourceSet result = resenjeRepository.getByXQuery(QueryUtilsResenje.FIND_ALL);
		return resourceSetToList(result);
	}
	
	private ListaResenja resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
		List<Resenje> zahteviList= new ArrayList<>();
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		Resenje resenje = JaxbMapper.unmarshalResenjeFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(resenje);
        }
		return new ListaResenja(zahteviList);
	}
	
	private void sendAsPdfToEmail(Resenje resenje, ZahtevZaAutorskoDelo zahtev) {
		try {
			File pdf = getPDF(resenje);
			Map<String, String> podaci = this.autorskoDeloService.getPodaciPodnosioca(zahtev);
			podaci.put("resenjeType", resenje.getStatus().toString() + "O");
			podaci.put("zahtevNumber", zahtev.getIdAutorskogDela().getIdA());
			emailService.sendEmailResenje(podaci, pdf);
			
			pdf.delete();
		} catch (IOException | DocumentException | ParserConfigurationException | JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public File getPDF(Resenje resenje) throws IOException, DocumentException, ParserConfigurationException, JAXBException {
		Document document = JaxbMapper.marshalResenjeToDocument(resenje);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + "iz-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + "iz-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateSource(document, outputFileXHTML, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
		
		return pdfFile;
	}
	
	@Override
	public String getPDF(String documentId) throws IOException, DocumentException {
		//ucitavanje xml-a iz baze
		Node resenje = resenjeRepository.getResenjeById(documentId);
		
		//kreiranje imena za pdf i html
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String inputFile = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    		
		//generisanje pdf-a i html-a
		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateSource(resenje, inputFile, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, inputFile);	
		removeFile(inputFile);
		
		return convertPdfToBase64(outputFilePDF);

	}


	@Override
	public Resenje getById(String documentId) {
		return resenjeRepository.getById(documentId);
	}



	private String convertPdfToBase64(String filepath) throws IOException {;
	    byte[] inputFile = Files.readAllBytes(Paths.get(filepath));
	
	    byte[] encodedBytes = Base64.getEncoder().encode(inputFile);
	    String encodedString =  new String(encodedBytes);
	    
	    removeFile(filepath);
	    return encodedString;
	}
	
	private void removeFile(String sourceFilePath) {
		File source = new File(sourceFilePath); 
		source.delete();
	}

	

}