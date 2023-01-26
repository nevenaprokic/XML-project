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
import org.xmldb.api.modules.XMLResource;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.dataAccess.utils.QueryUtilsResenje;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.IdZiga;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.mapper.ZigMapper;
import rs.ac.uns.ftn.repository.ZigRepository;
import rs.ac.uns.ftn.services.ZigService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class ZigServiceImpl implements ZigService {
	
	public static final String PATH = "src/main/resources/xslt/";
	
	@Autowired
	private ZigRepository zigRepository;

	@Override
	public void saveNewFile(ZahtevZaPriznanjeZiga zahtevDTO) {
		String documentId = generateDocumentId();
		IdZiga idZiga = new IdZiga();
		idZiga.setIdZ(documentId);
		zahtevDTO.setId(idZiga);
		System.out.println(documentId);
		ZahtevZaPriznanjeZiga zahtev = ZigMapper.mapFromDTO(zahtevDTO, documentId);
		zigRepository.saveZahtevZaPriznanjeZiga(zahtev, documentId);
		
	}

	@Override
	public ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id) {
		return zigRepository.getZahtevZaPriznanjeZigaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = zigRepository.getLenghtOfCollection();
		return "Z" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void getPDF(String documentId) throws IOException, DocumentException {
		
		//ucitavanje xml-a iz baze
		Node zaZig = zigRepository.getXMLZahtevZaZigbyId(documentId);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = PATH + documentId  + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
//		String xml_fileString = "src/main/resources/data/zahtev_za_prijavu_ziga.xml";
		
		pdfTransformer.generateHTML(zaZig, outputFileXHTML);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}
	
	@Override
	public ListaZahtevaZiga findAll() throws XMLDBException, JAXBException {
		ResourceSet result = zigRepository.getByXQuery(QueryUtils.FIND_ALL);
		return resourceSetToList(result);
	}
	
	private ListaZahtevaZiga resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
		List<ZahtevZaPriznanjeZiga> zahteviList= new ArrayList<>();
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		ZahtevZaPriznanjeZiga zahtev = JaxbMapper.unmarshalZahtevFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(zahtev);
        }
		return new ListaZahtevaZiga(zahteviList);
	}

}
