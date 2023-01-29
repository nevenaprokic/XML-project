package rs.ac.uns.ftn.controller;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.exception.ErrorMessage;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.services.AutorskoDeloService;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/autorsko-delo", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";
	private final static String USER_API_KORISNIK = "http://localhost:8903/xml/user/authkorisnik";

	@PostMapping(value="/save-new")
	public ResponseEntity<String> saveNewFile(@RequestBody ZahtevZaAutorskoDelo zahtev) {
		try {
			String id = autorskoDeloService.saveNewFile(zahtev);
			return new ResponseEntity<>(id, HttpStatus.CREATED);

		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/{documentId}")
	public ResponseEntity<ZahtevZaAutorskoDelo> getZahtevZaAutorskoDeloById(@PathVariable String documentId) {
		try {
			ZahtevZaAutorskoDelo zahtev = autorskoDeloService.getZahtevZaAutorskoDeloById(documentId);
			return ResponseEntity.ok(zahtev);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<ListaZahtevaAutorskoDelo> findAll(@RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.findAll();
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping(value="/searchText", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ListaZahtevaAutorskoDelo> searchText(@RequestParam("txt") String txt) {
		try {
			ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.searchText(txt);
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping(value="/searchMetadata")
	public ResponseEntity<ListaZahtevaAutorskoDelo> searchMetadata(@RequestParam("request") String request) {
		try {
			ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.searchMetadata(request);
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	

	@GetMapping("/get-pdf/{documentId}")
	public ResponseEntity<String> getPDF(@PathVariable String documentId) {
		try {
			autorskoDeloService.getPDF(documentId);
			return ResponseEntity.ok("Generisan PDF");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/get-rdf/{documentId}")
    public ResponseEntity<?> downloadRdf(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

       try {
    	   InputStreamResource rdf = autorskoDeloService.getMetadataAsRdf(documentId);
           HttpHeaders headers = getDownloadFileHeaders("zahtevZaAutorskoDelo" + documentId + ".rdf");
           return new ResponseEntity<>(rdf, headers, HttpStatus.OK);
       } catch (Exception e) {
    	   e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
	}
    
	@GetMapping(value = "/get-json/{documentId}")
    public ResponseEntity<?> downloadZalbaCutanjeJson(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        try {
        	InputStreamResource json = autorskoDeloService.getMetadataAsJson(documentId);
        	HttpHeaders headers = getDownloadFileHeaders("zahtevZaAutorskoDelo" + documentId + ".json");
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	private HttpHeaders getDownloadFileHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml; charset=utf-8");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName );
		return headers;
	}
	

	@GetMapping("/find-all-approved")
	public ResponseEntity<?> getAllApproved(@RequestHeader MultiValueMap<String, String> headers){
		this.chechAuthority(headers, USER_API_KORISNIK);
		try {
			return new ResponseEntity<>(this.autorskoDeloService.findAllApproved(), HttpStatus.OK);
		} catch (XMLDBException | JAXBException e) {
			ErrorMessage message = new ErrorMessage(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                ErrorMessageConstants.INTERNAL_ERROR
	        );

	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	private void chechAuthority(MultiValueMap<String, String> headers, String api) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders newHeader = new HttpHeaders(headers);
		newHeader.setHost(new InetSocketAddress("localhost", 8903));
		newHeader.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(api, HttpMethod.GET, entity, String.class);
	}
	
	@GetMapping(value="/get-prilog/{documentId}/{imgName}")
	public ResponseEntity<PrilogImage> getPrilog(@PathVariable String documentId, @PathVariable String imgName) {
		try {
			PrilogImage prilog = autorskoDeloService.getPrilog(documentId, imgName);
			return new ResponseEntity<>(prilog, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
}
