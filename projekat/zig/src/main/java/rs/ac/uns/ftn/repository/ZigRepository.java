package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceSet;

import rs.ac.uns.ftn.dataAccess.ZigDataAccess;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

@Repository
public class ZigRepository {
	@Autowired
	private ZigDataAccess zigDataAccess;
	
	public void saveZahtevZaPriznanjeZiga(ZahtevZaPriznanjeZiga zahtev, String documentId) {
		zigDataAccess.saveFile(documentId, zahtev);
	}
	
	public ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZigaById(String id) {
		return zigDataAccess.getZahtevById(id);
	}
	
	public int getLenghtOfCollection() {
		return zigDataAccess.getLenghtOfCollection();
	}
	
	public Node getXMLZahtevZaZigbyId(String id) {
		return zigDataAccess.getXMLZahtevById(id);
	}
	
	public ResourceSet getByXQuery(String xquery) {
		return zigDataAccess.getByXQuery(xquery);
	}
	public ResourceSet getAllApproved() {
		return zigDataAccess.getAllApproved();
	}
	
}
