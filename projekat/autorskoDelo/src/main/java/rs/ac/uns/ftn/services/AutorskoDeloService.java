package rs.ac.uns.ftn.services;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;

public interface AutorskoDeloService {
	
	String saveNewFile(ZahtevZaAutorskoDelo zahtev);
	
	ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;
}
