package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.core.io.InputStreamResource;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

public interface ZigService {

	void saveNewFile(ZahtevZaPriznanjeZiga zahtev);
	
	ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;

	ListaZahtevaZiga findAll() throws XMLDBException, JAXBException;
	
	void saveFile(ZahtevZaPriznanjeZiga zahtev, String documentId);
	
	ListaZahtevaZiga findAllApproved() throws XMLDBException, JAXBException;

	InputStreamResource getMetadataAsRdf(String documentId) throws IOException;

	InputStreamResource getMetadataAsJson(String documentId) throws IOException;

	ListaZahtevaZiga searchMetadata(String request) throws IOException;

	ListaZahtevaZiga searchText(String txt);
}
