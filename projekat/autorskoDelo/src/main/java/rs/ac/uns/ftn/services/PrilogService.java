package rs.ac.uns.ftn.services;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;

public interface PrilogService {

	void extractPrilozi(ZahtevZaAutorskoDelo zahtevDTO, String documentId);

	PrilogImage getPrilog(String documentId, String imgName);
}
