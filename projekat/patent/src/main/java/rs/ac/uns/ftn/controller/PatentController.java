package rs.ac.uns.ftn.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.exception.ErrorMessage;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.services.PatentService;

@Controller
@RequestMapping(value="/patent", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class PatentController {
	
	@Autowired
	private PatentService patentService;
	
	@RequestMapping(value="/save-new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> saveNewFile(@RequestBody ZahtevZaPriznanjePatenta zahtev) {
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
	public ResponseEntity<?> getPDF(@PathVariable String documentId) {
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
	 
}
