package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.services.IzvestajService;

@Controller
@RequestMapping(value="/izvestaj", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class IzvestajController {
	
	@Autowired
	private IzvestajService izvestajService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ResponseEntity<?> saveNewFile(@RequestBody Izvestaj izvestaj) {
		try {
			Izvestaj iz= izvestajService.getIzvestaj(izvestaj);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
	}

}
