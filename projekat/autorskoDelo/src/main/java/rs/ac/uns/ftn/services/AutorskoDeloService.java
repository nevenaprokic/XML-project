package rs.ac.uns.ftn.services;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;

public interface AutorskoDeloService {
	
	void saveNewFile(ZahtevZaAutorskoDelo zahtev);
	
	ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id);
	
	String generateDocumentId();
}
