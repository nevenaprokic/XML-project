package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import org.xmldb.api.base.XMLDBException;

public interface ZigService {

	void saveNewFile(ZahtevZaPriznanjeZiga zahtev);
	
	ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;

	ListaZahtevaZiga findAll() throws XMLDBException, JAXBException;
}
