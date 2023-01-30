package rs.ac.uns.ftn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.dataAccess.PrilogDataAccess;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;

@Repository
public class PrilogRepository {
	@Autowired
	private PrilogDataAccess prilogDataAccess;
	
	public void savePrilog(PrilogImage prilog, String documentId) {
		prilogDataAccess.savePrilog(documentId, prilog);
	}

	public PrilogImage getById(String id) {
		return prilogDataAccess.getById(id);
	}
}
