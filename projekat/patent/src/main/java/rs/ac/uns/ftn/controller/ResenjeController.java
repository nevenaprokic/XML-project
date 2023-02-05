package rs.ac.uns.ftn.controller;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.services.ResenjeService;

@Controller
@RequestMapping(value="/resenje", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ResenjeController {
	@Autowired
	private ResenjeService resenjeService;
	
	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";
	private final static String USER_API_SLUZBENIK_GET = "http://localhost:8903/xml/user/";
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST)
	public ResponseEntity<?> saveNewFile(@RequestBody Resenje resenje, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		System.out.println(headers);
		try {
			String user = this.getSluzbenik(headers, USER_API_SLUZBENIK_GET);
			String id = resenjeService.saveNewFile(resenje, user);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/get-pdf/{documentId}")
	public ResponseEntity<?> getPDF(@PathVariable String documentId, @RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			String encodedFile = resenjeService.getPDF(documentId);
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
	
	private String getSluzbenik(MultiValueMap<String, String> headers, String api) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders newHeader = new HttpHeaders(headers);
		newHeader.setHost(new InetSocketAddress("localhost", 8903));
		newHeader.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> respEntity = restTemplate.exchange(api, HttpMethod.GET, entity, String.class);
		return respEntity.toString();
	}
	
	
}
