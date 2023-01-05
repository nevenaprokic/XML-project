package rs.ac.uns.ftn.services;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

public interface PatentService {

	void saveNewFile(ZahtevZaPriznanjePatenta zahtev);
		
	ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id);
	
	String generateDocumentId();
}
