package rs.ac.uns.ftn.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.services.MetadataService;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/autorsko-delo", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	@Autowired
	private MetadataService metadataService;
	
	
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
	
	@GetMapping(value = "/get-rdf/{documentId}")
    public ResponseEntity<?> downloadRdf(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

       try {
    	   InputStreamResource rdf = metadataService.getAsRdf(documentId);
           HttpHeaders headers = getDownloadFileHeaders("zahtevZaAutorskoDelo" + documentId + ".rdf");
           return new ResponseEntity<>(rdf, headers, HttpStatus.OK);
       } catch (Exception e) {
    	   e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
	}
    
	@GetMapping(value = "/get-json/{documentId}")
    public ResponseEntity<?> downloadZalbaCutanjeJson(@PathVariable String documentId) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        try {
        	InputStreamResource json = metadataService.getAsJson(documentId);
        	HttpHeaders headers = getDownloadFileHeaders("zahtevZaAutorskoDelo" + documentId + ".json");
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	private HttpHeaders getDownloadFileHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml; charset=utf-8");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName );
		return headers;
	}
}
