package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceSet;

import rs.ac.uns.ftn.dataAccess.AutorskoDeloDataAccess;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;

@Repository
public class AutorskoDeloRepository {

	@Autowired
	private AutorskoDeloDataAccess autorskoDeloDataAccess;
	
	public void saveAutorskoDelo(ZahtevZaAutorskoDelo zahtev, String documentId) {
		autorskoDeloDataAccess.saveFile(documentId, zahtev);
	}

	public ZahtevZaAutorskoDelo getZahtevZaAutorskoDelobyId(String id) {
		return autorskoDeloDataAccess.getZahtevById(id);
	}
	
	public int getLenghtOfCollection() {
		return autorskoDeloDataAccess.getLenghtOfCollection();
	}
	
	public Node getXMLZahtevZaAutorskoDelobyId(String id) {
		return autorskoDeloDataAccess.getXMLZahtevById(id);
	}

	public ResourceSet getByXQuery(String xquery) {
		return autorskoDeloDataAccess.getByXQuery(xquery);
	}
	
	public ResourceSet getAllApproved() {
		return autorskoDeloDataAccess.getAllApproved();
	}
}
