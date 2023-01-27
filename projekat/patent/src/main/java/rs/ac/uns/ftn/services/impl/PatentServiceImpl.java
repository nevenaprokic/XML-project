package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.Jaxb;
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

	@Autowired
	private PatentRepository patentRepository;
	
    @Autowired
    private Jaxb jaxb;
	
	public void saveNewFile(ZahtevZaPriznanjePatenta zahtevDTO) throws XMLDBException{
		String xpath = "/Zahtev_za_priznanje_patenta[@broj_prijave='" + zahtevDTO.getBrojPrijave() + "']";
		if (patentRepository.getZahtevZaPriznanjePatentaByXPath(xpath).getSize() != 0) {
			throw new BadRequestException(ErrorMessageConstants.DOCUMENT_ALREADY_EXITS);
		}
        if (jaxb.validate(zahtevDTO.getClass(), zahtevDTO)) {
    		String documentId = generateDocumentId();
    		ZahtevZaPriznanjePatenta zahtev = PatentMapper.mapFromDTO(zahtevDTO, documentId);
    		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, documentId);
        }
	}

	@Override
	public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id) {
		return patentRepository.getZahtevZaPriznanjePatentaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = patentRepository.getLenghtOfCollection();
		return "P" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void saveFile(ZahtevZaPriznanjePatenta zahtevDTO, String documentId) {
		ZahtevZaPriznanjePatenta zahtev = PatentMapper.mapFromDTO(zahtevDTO, documentId);
		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, documentId);
		
	}
	
	@Override
	public void getPDF(String documentId) throws IOException, DocumentException {
		//ucitavanje xml-a iz baze
		Node zaPatent = patentRepository.getXMLZahtevZaPatentbyId(documentId);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		pdfTransformer.generateHTML(zaPatent, outputFileXHTML);
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
		List<ZahtevZaPriznanjePatenta> zahteviList= new ArrayList<>();
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		ZahtevZaPriznanjePatenta zahtev = JaxbMapper.unmarshalZahtevFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(zahtev);
        }
		return new ListaZahtevaPatent(zahteviList);
	}
}
