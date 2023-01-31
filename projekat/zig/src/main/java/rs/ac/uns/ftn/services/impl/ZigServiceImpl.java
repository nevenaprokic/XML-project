package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

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

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.IdZiga;
import rs.ac.uns.ftn.jaxb.z1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.mapper.ZigMapper;
import rs.ac.uns.ftn.repository.ZigRepository;
import rs.ac.uns.ftn.services.MetadataService;
import rs.ac.uns.ftn.services.PrilogService;
import rs.ac.uns.ftn.services.ZigService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class ZigServiceImpl implements ZigService {
	
	public static final String PATH = "src/main/resources/xslt/";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/z1";
	public static final String XSL_FILE = "src/main/resources/xslt/Z1.xsl";
	
	@Autowired
	private ZigRepository zigRepository;

	@Autowired
	private PrilogService prilogService;
	
	@Autowired
	private MetadataService metadataService;

	@Override
	public void saveNewFile(ZahtevZaPriznanjeZiga zahtevDTO) {
		String documentId = generateDocumentId();
		IdZiga idZiga = new IdZiga();
		idZiga.setIdZ(documentId);
		zahtevDTO.setId(idZiga);
		prilogService.extractPrilozi(zahtevDTO, documentId);
		ZahtevZaPriznanjeZiga zahtev = ZigMapper.mapFromDTO(zahtevDTO, documentId);
		zigRepository.saveZahtevZaPriznanjeZiga(zahtev, documentId);
	}
	
	@Override
	public void saveFile(ZahtevZaPriznanjeZiga zahtevDTO, String documentId) {
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
		String outputFilePDF = PATH + documentId  + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateHTML(zaZig, outputFileXHTML, XSL_FILE);
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
		System.out.println(zahteviList.size());
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		ZahtevZaPriznanjeZiga zahtev = JaxbMapper.unmarshalZahtevFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(zahtev);
        }
		return new ListaZahtevaZiga(zahteviList);
	}

	@Override
	public ListaZahtevaZiga findAllApproved() throws XMLDBException, JAXBException {
		ResourceSet result = zigRepository.getAllApproved();
		return resourceSetToList(result);
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
	public ListaZahtevaZiga searchMetadata(String request, String status) throws IOException {
		List<ZahtevZaPriznanjeZiga> zahtevi = new ArrayList<ZahtevZaPriznanjeZiga>();
		List<String> ids = metadataService.searchByMetadata(request);
		for (String id : ids) {
			String documentId = id.split(TARGET_NAMESPACE)[1];
			ZahtevZaPriznanjeZiga zahtev = getZahtevZaPriznanjeZiga(documentId);
			if(status.equals(StatusZahteva.ODOBREN.value())) {
				if(zahtev.getStatus().value().equals(status)) {
					zahtevi.add(zahtev);
				}
			}
			else {
				zahtevi.add(zahtev);
			}
		}
		return new ListaZahtevaZiga(zahtevi);
	}

	@Override
	public ListaZahtevaZiga searchText(String txt, String status) throws XMLDBException, JAXBException {
		String[] keywords = txt.split(";");
		String conditions = "";
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
		ResourceSet result = zigRepository.getByXQuery(xQuery);
		return resourceSetToList(result);
	}

}
