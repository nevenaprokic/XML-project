package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.services.ZigService;

@Controller
@RequestMapping("/zig")
public class ZigController {

	@Autowired
	private ZigService zigService;
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> saveNewFile(@RequestBody ZahtevZaPriznanjeZiga zahtev) {
		try {
			zigService.saveNewFile(zahtev);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
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
}
