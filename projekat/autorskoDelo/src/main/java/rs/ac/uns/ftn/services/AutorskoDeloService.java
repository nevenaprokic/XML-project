package rs.ac.uns.ftn.services;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.core.io.InputStreamResource;
import org.xmldb.api.base.XMLDBException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;

public interface AutorskoDeloService {
	
	String saveNewFile(ZahtevZaAutorskoDelo zahtev);
	
	ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;

	ListaZahtevaAutorskoDelo findAll() throws XMLDBException, JAXBException;
	
	void saveFile(ZahtevZaAutorskoDelo zahtevDTO, String documentId);

	ListaZahtevaAutorskoDelo searchText(String txt) throws XMLDBException, JAXBException;

	ListaZahtevaAutorskoDelo searchMetadata(String params) throws IOException;

	InputStreamResource getMetadataAsRdf(String documentId) throws IOException;

	InputStreamResource getMetadataAsJson(String documentId) throws IOException;
	
	ListaZahtevaAutorskoDelo findAllApproved() throws XMLDBException, JAXBException;

	PrilogImage getPrilog(String documentId, String imgName);
}
