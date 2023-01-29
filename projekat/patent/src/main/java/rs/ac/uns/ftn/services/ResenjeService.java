package rs.ac.uns.ftn.services;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.lists.ListaResenja;

public interface ResenjeService {

	ArrayList<Resenje> getAll();

	void saveNewFile(Resenje resenje, String user);
	
	String generateDocumentId();
	
	ListaResenja findAll() throws XMLDBException, JAXBException;
}
