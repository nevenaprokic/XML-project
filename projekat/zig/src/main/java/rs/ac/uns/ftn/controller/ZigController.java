package rs.ac.uns.ftn.controller;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.services.ZigService;


@Controller
@RequestMapping(value="/zig", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ZigController {

	@Autowired
	private ZigService zigService;
	
	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";
	private final static String USER_API_KORISNIK = "http://localhost:8903/xml/user/authkorisnik";
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST)
	public ResponseEntity<?> saveNewFile(@RequestBody ZahtevZaPriznanjeZiga zahtev) {
		try {
			zigService.saveNewFile(zahtev);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping(value="/{documentId}")
	public ResponseEntity<ZahtevZaPriznanjeZiga> getZahtevZaAutorskoDeloById(@PathVariable String documentId) {
		try {
			ZahtevZaPriznanjeZiga zahtev = zigService.getZahtevZaPriznanjeZiga(documentId);
			return ResponseEntity.ok(zahtev);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/get-pdf/{documentId}")
	public ResponseEntity<String> getPDF(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			zigService.getPDF(documentId);
			return ResponseEntity.ok("Generisan PDF");
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
}
