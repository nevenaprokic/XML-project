package rs.ac.uns.ftn.services;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

public interface ZigService {

	void saveNewFile(ZahtevZaPriznanjeZiga zahtev);
	
	ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id);
	
	String generateDocumentId();
	
	void getPDF(String documentId) throws IOException, DocumentException;
}
