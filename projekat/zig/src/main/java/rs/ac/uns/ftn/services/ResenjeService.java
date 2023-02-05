package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.lists.ListaResenja;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;

public interface ResenjeService {

	String saveNewFile(Resenje resenje, String user);

	String generateDocumentId();

	ListaResenja findAll() throws XMLDBException, JAXBException;
	
	String getPDF(String documentId) throws IOException, DocumentException;
}
