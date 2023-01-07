package rs.ac.uns.ftn.services;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

public interface PatentService {

	void saveNewFile(ZahtevZaPriznanjePatenta zahtev);
		
	ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;
}
