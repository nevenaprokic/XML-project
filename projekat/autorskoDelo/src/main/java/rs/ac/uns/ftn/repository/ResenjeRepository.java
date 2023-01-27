package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import rs.ac.uns.ftn.dataAccess.ResenjeDataAccess;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;

@Repository
public class ResenjeRepository {
	@Autowired
	private ResenjeDataAccess resenjeDataAccess;
	
	public void saveResenje(Resenje resenje, String documentId) {
		resenjeDataAccess.saveFile(documentId, resenje);
	}
	
	public int getLenghtOfCollection() {
		return resenjeDataAccess.getLenghtOfCollection();
	}
	
	public ResourceSet getByXQuery(String xquery) {
		return resenjeDataAccess.getByXQuery(xquery);
	}
}
