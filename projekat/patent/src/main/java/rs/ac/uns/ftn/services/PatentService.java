package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.PatentList;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

public interface PatentService {

	void saveNewFile(ZahtevZaPriznanjePatenta zahtev) throws XMLDBException;
		
	ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;
	
	PatentList getAllPatents() throws XMLDBException, JAXBException;
}
