package rs.ac.uns.ftn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.repository.PatentRepository;
import rs.ac.uns.ftn.services.PatentService;

@Service
public class PatentServiceImpl implements PatentService {

	@Autowired
	private PatentRepository patentRepository;
	
	public void saveNewFile(ZahtevZaPriznanjePatenta zahtev) {
		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, generateDocumentId());
	}

	@Override
	public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id) {
		return patentRepository.getZahtevZaPriznanjePatentaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = patentRepository.getLenghtOfCollection();
		return "P" + String.valueOf(curretnNumber + 1); 
	}
}
