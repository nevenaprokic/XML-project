package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.services.ResenjeService;

@Controller
@RequestMapping(value="/resenje", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ResenjeController {
	@Autowired
	private ResenjeService resenjeService;
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST)
	public ResponseEntity<?> saveNewFile(@RequestBody Resenje resenje) {
		try {
			resenjeService.saveNewFile(resenje);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
	}
}
