package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.dataAccess.PatentDataAccess;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

@Repository
public class PatentRepository {
	@Autowired
	private PatentDataAccess patentDataAccess;
	
	public void saveZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatenta zahtev, String documentId) {
		System.out.println(documentId);
		patentDataAccess.saveFile(documentId, zahtev);
	}
	
	public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatentaById(String id) {
		return patentDataAccess.getZahtevById(id);
	}
	
	public int getLenghtOfCollection() {
		return patentDataAccess.getLenghtOfCollection();
	}
	
	public Node getXMLZahtevZaPatentbyId(String id) {
		return patentDataAccess.getXMLZahtevById(id);
	}
	
}
