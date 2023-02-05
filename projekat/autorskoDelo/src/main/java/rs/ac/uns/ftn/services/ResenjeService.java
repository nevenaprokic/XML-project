package rs.ac.uns.ftn.services;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.lists.ListaResenja;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;

public interface ResenjeService {

	String saveNewFile(Resenje resenje, String user);

	String generateDocumentId();

	ListaResenja findAll() throws XMLDBException, JAXBException;

	File getPDF(Resenje resenje) throws IOException, DocumentException, ParserConfigurationException, JAXBException;

	Resenje getById(String documentId);

	String getPDF(String documentId) throws IOException, DocumentException;
}
