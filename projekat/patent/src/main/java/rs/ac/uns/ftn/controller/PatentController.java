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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.exception.ErrorMessage;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;
import rs.ac.uns.ftn.services.PatentService;

@Controller
@RequestMapping(value="/patent", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class PatentController {
	
	@Autowired
	private PatentService patentService;
	
	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";
	private final static String USER_API_KORISNIK = "http://localhost:8903/xml/user/authkorisnik";
	private final static String USER_API_KORISNIK_SLUZBENIK = "http://localhost:8903/xml/user/authkorisnik-or-sluzbenik";

	@RequestMapping(value="/save-new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> saveNewFile(@RequestBody ZahtevZaPriznanjePatenta zahtev, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_KORISNIK);
		try {
			patentService.saveNewFile(zahtev);
			return ResponseEntity.ok().build();
		} catch (XMLDBException e) {
			e.printStackTrace();
			ErrorMessage message = new ErrorMessage(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                e.getMessage()
	        );

	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping(value="/{documentId}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> getById(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
			this.chechAuthority(headers, USER_API_KORISNIK_SLUZBENIK);
			ZahtevZaPriznanjePatenta zahtev = patentService.getZahtevZaPriznanjePatenta(documentId);
			return ResponseEntity.ok(zahtev);
	}
	
	@GetMapping("/get-pdf/{documentId}")
	public ResponseEntity<?> getPDF(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			String encodedFile = patentService.getPDF(documentId);
            return ResponseEntity.ok(encodedFile);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/get-xhtml/{documentId}")
	public ResponseEntity<?> getXHTML(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			String encodedFile = patentService.getHTML(documentId);
            return ResponseEntity.ok(encodedFile);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
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
	
	@GetMapping(value = "/get-rdf/{documentId}")
    public ResponseEntity<?> downloadRdf(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

       try {
    	   InputStreamResource rdf = patentService.getMetadataAsRdf(documentId);
           HttpHeaders headers = getDownloadFileHeaders("zahtevZaPriznanjePatenta" + documentId + ".rdf");
           return new ResponseEntity<>(rdf, headers, HttpStatus.OK);
       } catch (Exception e) {
    	   e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
	}

	@GetMapping(value = "/get-json/{documentId}")
    public ResponseEntity<?> downloadJson(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {
        try {
        	InputStreamResource json = patentService.getMetadataAsJson(documentId);
        	HttpHeaders headers = getDownloadFileHeaders("zahtevZaPriznanjePatenta" + documentId + ".json");
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
	
	@GetMapping(value="/searchText", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ListaZahtevaPatent> searchText(@RequestParam("txt") String txt, @RequestParam("status") String status) {
		try {
			ListaZahtevaPatent zahtevi = patentService.searchText(txt, status);
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping(value="/searchMetadata")
	public ResponseEntity<ListaZahtevaPatent> searchMetadata(@RequestParam("request") String request, @RequestParam("status") String status) {
		try {
			ListaZahtevaPatent zahtevi = patentService.searchMetadata(request, status);
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping("/find-all-approved")
	public ResponseEntity<?> getAllApproved(@RequestHeader MultiValueMap<String, String> headers){
		this.chechAuthority(headers, USER_API_KORISNIK);
		try {
			return new ResponseEntity<>(this.patentService.findAllApproved(), HttpStatus.OK);
		} catch (XMLDBException | JAXBException e) {
			ErrorMessage message = new ErrorMessage(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                ErrorMessageConstants.INTERNAL_ERROR
	        );

	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<ListaZahtevaPatent> findAll(@RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			ListaZahtevaPatent zahtevi = patentService.findAll();
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
