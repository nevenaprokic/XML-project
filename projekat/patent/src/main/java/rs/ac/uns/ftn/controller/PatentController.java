package rs.ac.uns.ftn.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.exception.ErrorMessage;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.services.PatentService;

@Controller
@RequestMapping(value="/patent", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class PatentController {
	
	@Autowired
	private PatentService patentService;
	
	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";
	private final static String USER_API_KORISNIK = "http://localhost:8903/xml/user/authkorisnik";
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> saveNewFile(@RequestBody ZahtevZaPriznanjePatenta zahtev, @RequestHeader MultiValueMap<String, String> headers) {
//		this.chechAuthority(headers, USER_API_KORISNIK);
		try {
			patentService.saveNewFile(zahtev);
			return ResponseEntity.ok().build();
		} catch (XMLDBException e) {
			ErrorMessage message = new ErrorMessage(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                e.getMessage()
	        );

	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/{documentId}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> getZahtevZaAutorskoDeloById(@PathVariable String documentId) {
			ZahtevZaPriznanjePatenta zahtev = patentService.getZahtevZaPriznanjePatenta(documentId);
			return ResponseEntity.ok(zahtev);
	}
	
	@GetMapping("/get-pdf/{documentId}")
	public ResponseEntity<?> getPDF(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			patentService.getPDF(documentId);
			return ResponseEntity.ok("Generisan PDF");
		} catch (IOException | DocumentException e) {
			ErrorMessage message = new ErrorMessage(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                e.getMessage()
	        );

	        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/all-patents")
	public ResponseEntity<?> getAllPatents(@RequestHeader MultiValueMap<String, String> headers){
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			return new ResponseEntity<>(this.patentService.findAll(), HttpStatus.OK);
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
}
