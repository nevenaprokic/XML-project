package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;

public interface AutorskoDeloService {
	
	String saveNewFile(ZahtevZaAutorskoDelo zahtev);
	
	ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;

	ListaZahtevaAutorskoDelo findAll() throws XMLDBException, JAXBException;
}
