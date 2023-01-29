package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;

public interface IzvestajService {

	Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException;
	
	void getPDF(Izvestaj izvestaj) throws IOException, DocumentException, ParserConfigurationException, JAXBException;
}
