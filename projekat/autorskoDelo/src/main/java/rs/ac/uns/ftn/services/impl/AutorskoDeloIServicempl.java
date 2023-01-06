package rs.ac.uns.ftn.services.impl;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.mapper.AutorskoDeloMapper;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.repository.AutorskoDeloRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorskoDeloIServicempl implements AutorskoDeloService{

	@Autowired
	private AutorskoDeloRepository autorskoDeloRepository;
	
	@Override
	public void saveNewFile(ZahtevZaAutorskoDelo zahtevDTO) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		ZahtevZaAutorskoDelo zahtev = AutorskoDeloMapper.mapFromDTO(zahtevDTO, documentId);
		autorskoDeloRepository.saveAutorskoDelo(zahtev, documentId);
	}

	@Override
	public ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id) {
		return autorskoDeloRepository.getZahtevZaAutorskoDelobyId(id);
	}

	@Override
	public String generateDocumentId() {
		int curretnNumber = autorskoDeloRepository.getLenghtOfCollection();
		return "A" + String.valueOf(curretnNumber + 1); 
	}

}
