package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.InputStreamResource;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

public interface ZigService {

	void saveNewFile(ZahtevZaPriznanjeZiga zahtev);
	
	ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException, JAXBException, ParserConfigurationException;

	ListaZahtevaZiga findAll() throws XMLDBException, JAXBException;
	
	void saveFile(ZahtevZaPriznanjeZiga zahtev, String documentId);
	
	ListaZahtevaZiga findAllApproved() throws XMLDBException, JAXBException;

	InputStreamResource getMetadataAsRdf(String documentId) throws IOException;

	InputStreamResource getMetadataAsJson(String documentId) throws IOException;

	ListaZahtevaZiga searchMetadata(String request, String status) throws IOException;

	ListaZahtevaZiga searchText(String txt, String status) throws XMLDBException, JAXBException;
}
