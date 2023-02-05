package rs.ac.uns.ftn.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.lists.ListaResenja;

public interface ResenjeService {

	ArrayList<Resenje> getAll();

	String saveNewFile(Resenje resenje, String user);
	
	String generateDocumentId();
	
	ListaResenja findAll() throws XMLDBException, JAXBException;

	String getPDF(String documentId) throws IOException, DocumentException;
}
