package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.PatentList;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;

public interface PatentService {

	void saveNewFile(ZahtevZaPriznanjePatenta zahtev) throws XMLDBException;
		
	ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;

	void saveFile(ZahtevZaPriznanjePatenta zahtevDTO, String documentId);
	
	ListaZahtevaPatent findAll() throws XMLDBException, JAXBException;

	//PatentList getAllPatents() throws XMLDBException, JAXBException;

}
