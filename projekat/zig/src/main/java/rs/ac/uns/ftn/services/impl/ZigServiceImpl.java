package rs.ac.uns.ftn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.ZigMapper;
import rs.ac.uns.ftn.repository.ZigRepository;
import rs.ac.uns.ftn.services.ZigService;

@Service
public class ZigServiceImpl implements ZigService {
	
	@Autowired
	ZigRepository ZigRepository;

	@Override
	public void saveNewFile(ZahtevZaPriznanjeZiga zahtevDTO) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		ZahtevZaPriznanjeZiga zahtev = ZigMapper.mapFromDTO(zahtevDTO, documentId);
		ZigRepository.saveZahtevZaPriznanjeZiga(zahtev, documentId);
		
	}

	@Override
	public ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id) {
		return ZigRepository.getZahtevZaPriznanjeZigaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = ZigRepository.getLenghtOfCollection();
		return "Z" + String.valueOf(curretnNumber + 1); 
	}

}
