package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andrewoma.dexx.collection.ArrayList;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.services.AutorskoDeloService;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/autorsko-delo", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	
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
	public ResponseEntity<ListaZahtevaAutorskoDelo> findAll() {
		try {
			ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.findAll();
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping(value="/searchText", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ListaZahtevaAutorskoDelo> searchText(@RequestParam("txt") String txt) {
		try {
			ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.searchText(txt);
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
	

}
