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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.services.IzvestajService;

@Controller
@RequestMapping(value = "/izvestaj", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class IzvestajController {

	@Autowired
	private IzvestajService izvestajService;

	private final static String USER_API_SLUZBENIK = "http://localhost:8903/xml/user/authsluzbenik";

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<Izvestaj> saveNewFile(@RequestBody Izvestaj izvestaj,
			@RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			Izvestaj iz = izvestajService.getIzvestaj(izvestaj);
			return new ResponseEntity<Izvestaj>(iz, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/getPDF", method = RequestMethod.POST)
	public ResponseEntity<String> getPDFIzvestaj(@RequestBody Izvestaj izvestaj,
			@RequestHeader MultiValueMap<String, String> headers) {
		this.chechAuthority(headers, USER_API_SLUZBENIK);
		try {
			String encodedFile = izvestajService.getPDF(izvestaj);
			 return ResponseEntity.ok(encodedFile);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

	private void chechAuthority(MultiValueMap<String, String> headers, String api) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders newHeader = new HttpHeaders(headers);
		newHeader.setHost(new InetSocketAddress("localhost", 8903));
		newHeader.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		restTemplate.exchange(api, HttpMethod.GET, entity, String.class);
	}
}
