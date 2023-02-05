package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.springframework.core.io.InputStreamResource;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;

public interface PatentService {

	void saveNewFile(ZahtevZaPriznanjePatenta zahtev) throws XMLDBException;
		
	ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id);
	
	String generateDocumentId();
	
	String getPDF(String documentId) throws IOException, DocumentException, FOPException, TransformerException;
	
	String getHTML(String documentId) throws IOException, DocumentException, FOPException, TransformerException;
	
	void saveFile(ZahtevZaPriznanjePatenta zahtevDTO, String documentId);
	
	ListaZahtevaPatent findAll() throws XMLDBException, JAXBException;

	InputStreamResource getMetadataAsRdf(String documentId) throws IOException;

	InputStreamResource getMetadataAsJson(String documentId) throws IOException;

	ListaZahtevaPatent searchText(String txt, String status) throws XMLDBException, JAXBException;

	ListaZahtevaPatent searchMetadata(String request, String status) throws IOException;

	//PatentList getAllPatents() throws XMLDBException, JAXBException;

}
