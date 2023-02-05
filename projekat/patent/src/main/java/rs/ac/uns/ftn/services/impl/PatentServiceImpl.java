package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.Jaxb;
import rs.ac.uns.ftn.jaxb.p1.PodaciODodatnojPrijavi;
import rs.ac.uns.ftn.jaxb.p1.RanijaPrijava;
import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.mapper.PatentMapper;
import rs.ac.uns.ftn.repository.PatentRepository;
import rs.ac.uns.ftn.services.MetadataService;
import rs.ac.uns.ftn.services.PatentService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class PatentServiceImpl implements PatentService {

	public static final String PATH = "src/main/resources/xslt/";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/p1/";
	public static final String XSL_FILE = "src/main/resources/xslt/P1.xsl";

	@Autowired
	private PatentRepository patentRepository;

	@Autowired
	private Jaxb jaxb;

	@Autowired
	private MetadataService metadataService;

	public void saveNewFile(ZahtevZaPriznanjePatenta zahtevDTO) throws XMLDBException {
		System.out.println("tuuu");
        if (jaxb.validate(zahtevDTO.getClass(), zahtevDTO)) {
        	zahtevDTO.setStatus(StatusZahteva.NEOBRADJEN);     	
        	try {
        		GregorianCalendar c = new GregorianCalendar();
        		c.setTime(new Date());
        		XMLGregorianCalendar date2;
    			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    			zahtevDTO.setDatumPrijemaPrijave(date2);
    		} catch (DatatypeConfigurationException e) {
    			e.printStackTrace();
    		}
    		String documentId = generateDocumentId();
    		documentId = documentId.replace('/', '_');
    		this.checkDopunskaPrijava(zahtevDTO.getPodaciODodatnojPrijavi());
    		this.checkLinkedDocuments(zahtevDTO);
    		ZahtevZaPriznanjePatenta zahtev = PatentMapper.mapFromDTO(zahtevDTO, documentId);
    		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, documentId);
        }
	}

	@Override
	public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id) {
		id = id.replace('/', '_');
		return patentRepository.getZahtevZaPriznanjePatentaById(id);
	}

	@Override
	public String generateDocumentId() {
		int curretnNumber = patentRepository.getLenghtOfCollection();
	    int currentYear = LocalDate.now().getYear();
		return "P" + String.valueOf(curretnNumber + 1) + "/" + currentYear; 
	}

	@Override
	public void saveFile(ZahtevZaPriznanjePatenta zahtevDTO, String documentId) {
		ZahtevZaPriznanjePatenta zahtev = PatentMapper.mapFromDTO(zahtevDTO, documentId);
		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, documentId);

	}

	@Override
	public String getPDF(String documentId) throws IOException, DocumentException {
		// ucitavanje xml-a iz baze
		Node zaPatent = patentRepository.getXMLZahtevZaPatentbyId(documentId);

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String inputFile = PATH + documentId + "-" + timeStamp + ".html";

		// Creates parent directory if necessary
		File pdfFile = new File(outputFilePDF);

		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}

		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateSource(zaPatent, inputFile, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, inputFile);	
		removeFile(inputFile);
		return convertPdfToBase64(outputFilePDF);
	}
	
	@Override
	public String getHTML(String documentId) throws IOException, DocumentException, FOPException, TransformerException {
		//ucitavanje xml-a iz baze
		Node zaPatent = patentRepository.getXMLZahtevZaPatentbyId(documentId);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFileHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File htmlFile = new File(outputFileHTML);
		if (!htmlFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + htmlFile.getParentFile().getAbsolutePath() + ".");
			htmlFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateSource(zaPatent, outputFileHTML, XSL_FILE);
		
		return convertPdfToBase64(outputFileHTML);
	}

	@Override
	public ListaZahtevaPatent findAll() throws XMLDBException, JAXBException {
		ResourceSet result = patentRepository.getByXQuery(QueryUtils.FIND_ALL);
		return resourceSetToList(result);
	}

	private ListaZahtevaPatent resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
		System.out.println(result);
		List<ZahtevZaPriznanjePatenta> zahteviList = new ArrayList<>();
		ResourceIterator i = result.getIterator();

		while (i.hasMoreResources()) {
			XMLResource xmlResource = (XMLResource) i.nextResource();
			ZahtevZaPriznanjePatenta zahtev = JaxbMapper.unmarshalZahtevFromNode(xmlResource.getContentAsDOM());
			zahteviList.add(zahtev);
		}
		return new ListaZahtevaPatent(zahteviList);
	}

	private void checkLinkedDocuments(ZahtevZaPriznanjePatenta zahtevDTO) {
		TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava prvenstvo = zahtevDTO.getZahtevZaPriznanjePrvenstvaIzRanijihPrijava();
		if(prvenstvo != null) {
			for(RanijaPrijava prijava : prvenstvo.getRanijaPrijava()) {
				String brPrijave = prijava.getBrojPrijave().replace('/', '_');
				ZahtevZaPriznanjePatenta zavedenaPrijava = this.patentRepository.getZahtevZaPriznanjePatentaById(brPrijave);
				if (zavedenaPrijava == null) {
					throw new BadRequestException(ErrorMessageConstants.NEPOSTOJECI_DOKUMENT);
					
				}
				else if(zavedenaPrijava.getStatus() != StatusZahteva.ODOBREN) {
					throw new BadRequestException(ErrorMessageConstants.NEODOBREN_DOKUMENT);
				}
			}
		}
	}
	
	private void checkDopunskaPrijava(PodaciODodatnojPrijavi dodatnaPrijava) {
		
		if (dodatnaPrijava != null) {
			String brPrijave = dodatnaPrijava.getBrojPrvobitnePrijave().replace('/', '_');
			ZahtevZaPriznanjePatenta zavedenaPrijava = this.patentRepository.getZahtevZaPriznanjePatentaById(brPrijave);
			if (zavedenaPrijava == null) {
				throw new BadRequestException(ErrorMessageConstants.NEPOSTOJECI_DOKUMENT);
				
			}
			else if(zavedenaPrijava.getStatus() != StatusZahteva.ODOBREN) {
				throw new BadRequestException(ErrorMessageConstants.NEODOBREN_DOKUMENT);
			}
		}
		
	}

	@Override
	public InputStreamResource getMetadataAsRdf(String documentId) throws IOException {
		return metadataService.getAsRdf(documentId);
	}

	@Override
	public InputStreamResource getMetadataAsJson(String documentId) throws IOException {
		return metadataService.getAsJson(documentId);
	}

	@Override
	public ListaZahtevaPatent searchText(String txt, String status) throws XMLDBException, JAXBException {
		String[] keywords = txt.split(";");
		String conditions =  "" ;
		for (int i = 0; i < keywords.length; i++) {
			conditions += String.format(QueryUtils.CONDITION_TEPMLATE, "'" + keywords[i] + "'");
			if(i != keywords.length-1) {
				conditions += " and ";
			}
		}
		if(status.equals(StatusZahteva.ODOBREN.value())) {
			conditions += " and " + String.format(QueryUtils.STATUS_TEPMLATE, "'" + status + "'");
		}
		String xQuery = String.format(QueryUtils.SEARCH_TEXT, conditions);
		System.out.println(xQuery);
		ResourceSet result = patentRepository.getByXQuery(xQuery);
		return resourceSetToList(result);
	}

	@Override
	public ListaZahtevaPatent searchMetadata(String request, String status) throws IOException {
		List<ZahtevZaPriznanjePatenta> zahtevi = new ArrayList<ZahtevZaPriznanjePatenta>();
		List<String> ids = metadataService.searchByMetadata(request);
		for (String id : ids) {
			String documentId = id.split(TARGET_NAMESPACE)[1];
			ZahtevZaPriznanjePatenta zahtev = getZahtevZaPriznanjePatenta(documentId);
			if(status.equals(StatusZahteva.ODOBREN.value())) {
				if(zahtev.getStatus().value().equals(status)) {
					zahtevi.add(zahtev);
				}
			}
			else {
				zahtevi.add(zahtev);
			}
			
		}
		return new ListaZahtevaPatent(zahtevi);
	}
	
	@Override
	public ListaZahtevaPatent findAllApproved() throws XMLDBException, JAXBException {
		ResourceSet result = patentRepository.getAllApproved();
		return resourceSetToList(result);
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
