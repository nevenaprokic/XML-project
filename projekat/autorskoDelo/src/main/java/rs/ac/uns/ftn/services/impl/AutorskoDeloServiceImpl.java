package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
import rs.ac.uns.ftn.jaxb.a1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.a1.TPodnosilac;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.mapper.AutorskoDeloMapper;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.repository.AutorskoDeloRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.services.MetadataService;
import rs.ac.uns.ftn.services.PrilogService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class AutorskoDeloServiceImpl implements AutorskoDeloService{
	public static final String PATH = "src/main/resources/xslt/";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/a1";
	public static final String XSL_FILE = "src/main/resources/xslt/A1.xsl";

	@Autowired
	private PrilogService prilogService;
	
	@Autowired
	private AutorskoDeloRepository autorskoDeloRepository;
	
	@Autowired
	private MetadataService metadataService;

	@Override
	public String saveNewFile(ZahtevZaAutorskoDelo zahtevDTO) {
		String documentId = generateDocumentId();
		setDatumPodnosenja(zahtevDTO);
		prilogService.extractPrilozi(zahtevDTO, documentId);
		ZahtevZaAutorskoDelo zahtev = AutorskoDeloMapper.mapFromDTO(zahtevDTO, documentId);
		autorskoDeloRepository.saveAutorskoDelo(zahtev, documentId);
		return documentId;
	}

	private void setDatumPodnosenja(ZahtevZaAutorskoDelo zahtevDTO) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar date2;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			zahtevDTO.setDatumPodnosenja(date2);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id) {
		return autorskoDeloRepository.getZahtevZaAutorskoDelobyId(id);
	}
	
	

	@Override
	public String generateDocumentId() {
		int curretnNumber = autorskoDeloRepository.getLenghtOfCollection();
		return "A" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void saveFile(ZahtevZaAutorskoDelo zahtevDTO, String documentId) {
		ZahtevZaAutorskoDelo zahtev = AutorskoDeloMapper.mapFromDTO(zahtevDTO, documentId);
		autorskoDeloRepository.saveAutorskoDelo(zahtev, documentId);
		
	}
	
	@Override
	public void getPDF(String documentId) throws IOException, DocumentException {
		//ucitavanje xml-a iz baze
		Node zaAutorskoDelo = autorskoDeloRepository.getXMLZahtevZaAutorskoDelobyId(documentId);
		
		//kreiranje imena za pdf i html
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		//generisanje pdf-a i html-a
		pdfTransformer.generateHTML(zaAutorskoDelo, outputFileXHTML, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}
	
	@Override
	public ListaZahtevaAutorskoDelo findAll() throws XMLDBException, JAXBException {
		ResourceSet result = autorskoDeloRepository.getByXQuery(QueryUtils.FIND_ALL);
		return resourceSetToList(result);
	}
	
	private ListaZahtevaAutorskoDelo resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
		List<ZahtevZaAutorskoDelo> zahteviList= new ArrayList<>();
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		ZahtevZaAutorskoDelo zahtev = JaxbMapper.unmarshalZahtevZaAutorskoDeloFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(zahtev);
        }
		return new ListaZahtevaAutorskoDelo(zahteviList);
	}

	@Override
	public ListaZahtevaAutorskoDelo searchText(String txt, String status) throws XMLDBException, JAXBException {
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
		ResourceSet result = autorskoDeloRepository.getByXQuery(xQuery);
		return resourceSetToList(result);
	}

	@Override
	public ListaZahtevaAutorskoDelo searchMetadata(String request, String status) throws IOException {
		List<ZahtevZaAutorskoDelo> zahtevi = new ArrayList<ZahtevZaAutorskoDelo>();
		List<String> ids = metadataService.searchByMetadata(request);
		for (String id : ids) {
			String documentId = id.split(TARGET_NAMESPACE)[1];
			ZahtevZaAutorskoDelo zahtev = getZahtevZaAutorskoDeloById(documentId);
			if(status.equals(StatusZahteva.ODOBREN.value())) {
				if(zahtev.getStatus().value().equals(status)) {
					zahtevi.add(zahtev);
				}
			}
			else {
				zahtevi.add(zahtev);
			}
		}
		return new ListaZahtevaAutorskoDelo(zahtevi);
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
	public ListaZahtevaAutorskoDelo findAllApproved() throws XMLDBException, JAXBException {
		ResourceSet result = autorskoDeloRepository.getAllApproved();
		return resourceSetToList(result);
	}

	@Override
	public PrilogImage getPrilog(String documentId, String imgName) {
		return prilogService.getPrilog(documentId, imgName);
	}

	@Override
	public Map<String, String> getPodaciPodnosioca(ZahtevZaAutorskoDelo zahtev) {
		TPodnosilac podnosilacDto = zahtev.getPodnosilac();
		Map<String, String> podaciPodnosioca = new HashMap<String, String>();

		if(podnosilacDto.getPravnoLice()!=null) {
			String email = podnosilacDto.getPravnoLice().getKontaktPodaci().getEmail();
			String name = podnosilacDto.getPravnoLice().getNaziv();
			podaciPodnosioca.put("email", email);
			podaciPodnosioca.put("name", name);
		}
		else if(podnosilacDto.getPunomocnik()!=null) {
			String email = podnosilacDto.getPunomocnik().getKontaktPodaci().getEmail();
			String name = formatName(podnosilacDto.getPunomocnik().getIme(), podnosilacDto.getPunomocnik().getPrezime(), null);
			podaciPodnosioca.put("email", email);
			podaciPodnosioca.put("name", name);
		}
		else if(podnosilacDto.getAutor()!=null){
			String email = podnosilacDto.getAutor().getKontaktPodaci().getEmail();
			String name = formatName(podnosilacDto.getAutor().getIme(), podnosilacDto.getAutor().getPrezime(), podnosilacDto.getAutor().getPseudonim());
			podaciPodnosioca.put("email", email);
			podaciPodnosioca.put("name", name);
		}
		return podaciPodnosioca;
	}
	
	private String formatName(String ime, String prezime, String pseudonim) {
		ime = ime != null ? ime : "";
		prezime = prezime != null ? prezime : "";
		pseudonim = pseudonim != null ? pseudonim : "";
		return String.format("%1$s %2$s %3$s", ime, prezime, pseudonim).trim();
	}


}
