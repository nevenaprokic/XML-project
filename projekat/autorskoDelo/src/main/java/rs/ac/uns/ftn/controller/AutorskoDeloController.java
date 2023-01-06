package rs.ac.uns.ftn.controller;

import org.springframework.web.bind.annotation.*;


import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.services.AutorskoDeloService;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/autorsko-delo")
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity saveNewFile(@RequestBody ZahtevZaAutorskoDelo zahtev) {
		try {
			autorskoDeloService.saveNewFile(zahtev);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
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
	
	@GetMapping("/get-pdf")
	public ResponseEntity<String> getPDF() {
		try {
			autorskoDeloService.getPDF();
			return ResponseEntity.ok("Generisan PDF");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

}
