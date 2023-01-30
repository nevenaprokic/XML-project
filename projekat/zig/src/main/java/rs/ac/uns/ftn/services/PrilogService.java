package rs.ac.uns.ftn.services;

import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

public interface PrilogService {

	void extractPrilozi(ZahtevZaPriznanjeZiga zahtevDTO, String documentId);

	PrilogImage getPrilog(String documentId, String imgName);
}
