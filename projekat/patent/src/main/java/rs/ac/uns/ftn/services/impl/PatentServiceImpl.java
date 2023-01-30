package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.Jaxb;

import rs.ac.uns.ftn.jaxb.PatentList;
import rs.ac.uns.ftn.jaxb.p1.PodaciODodatnojPrijavi;
import rs.ac.uns.ftn.jaxb.p1.RanijaPrijava;
import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.mapper.PatentMapper;
import rs.ac.uns.ftn.repository.PatentRepository;
import rs.ac.uns.ftn.services.PatentService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class PatentServiceImpl implements PatentService {

	public static final String PATH = "src/main/resources/xslt/";
	public static final String XSL_FILE = "src/main/resources/xslt/P1.xsl";

	@Autowired
	private PatentRepository patentRepository;

	@Autowired
	private Jaxb jaxb;

	public void saveNewFile(ZahtevZaPriznanjePatenta zahtevDTO) throws XMLDBException {
//		String xpath = "/Zahtev_za_priznanje_patenta[@broj_prijave='" + zahtevDTO.getBrojPrijave() + "']";
//		if (patentRepository.getZahtevZaPriznanjePatentaByXPath(xpath).getSize() != 0) {
//			throw new BadRequestException(ErrorMessageConstants.DOCUMENT_ALREADY_EXITS);
//		}
        if (jaxb.validate(zahtevDTO.getClass(), zahtevDTO)) {
        	zahtevDTO.setStatus(StatusZahteva.ODOBREN);     	
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
	public void getPDF(String documentId) throws IOException, DocumentException {
		// ucitavanje xml-a iz baze
		Node zaPatent = patentRepository.getXMLZahtevZaPatentbyId(documentId);

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";

		// Creates parent directory if necessary
		File pdfFile = new File(outputFilePDF);

		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}

		PDFTransformer pdfTransformer = new PDFTransformer();

		pdfTransformer.generateHTML(zaPatent, outputFileXHTML, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);

		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

	@Override
	public ListaZahtevaPatent findAll() throws XMLDBException, JAXBException {
		ResourceSet result = patentRepository.getByXQuery(QueryUtils.FIND_ALL);
		return resourceSetToList(result);
	}

	private ListaZahtevaPatent resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
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
}
