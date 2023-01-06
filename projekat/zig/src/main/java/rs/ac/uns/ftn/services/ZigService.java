package rs.ac.uns.ftn.services;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

public interface ZigService {

	void saveNewFile(ZahtevZaPriznanjeZiga zahtev);
	
	ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id);
	
	String generateDocumentId();
}
