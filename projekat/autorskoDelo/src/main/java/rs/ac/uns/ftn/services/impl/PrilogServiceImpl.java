package rs.ac.uns.ftn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.jaxb.a1.TPrilog;
import rs.ac.uns.ftn.jaxb.a1.TPrilozi;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.mapper.PrilogMapper;
import rs.ac.uns.ftn.repository.PrilogRepository;
import rs.ac.uns.ftn.services.PrilogService;

@Service
public class PrilogServiceImpl implements PrilogService{
	
	@Autowired
	private PrilogRepository prilogRepository;

	@Override
	public void extractPrilozi(ZahtevZaAutorskoDelo zahtev, String documentId) {
		TPrilozi prilozi = zahtev.getPrilozi();
		if(prilozi == null) {
			return;
		}
		if(prilozi.getPrisutanOpis()!=null) {
			String path = savePrilog(prilozi.getPrisutanOpis(), documentId);
			prilozi.getPrisutanOpis().setPutanjaDoFajla(path);
		}
		if(prilozi.getPrisutanPrimer()!=null) {
			String path = savePrilog(prilozi.getPrisutanPrimer(), documentId);
			prilozi.getPrisutanPrimer().setPutanjaDoFajla(path);
		}
	}
	
	private String savePrilog(TPrilog prilogDto, String documentId) {
		PrilogImage prilog = PrilogMapper.mapFromDTO(prilogDto.getPutanjaDoFajla());
		String prilogId = documentId + "-" + prilog.getNazivPriloga();
		prilogRepository.savePrilog(prilog, prilogId);
		
		return prilog.getNazivPriloga();
	}
	
	

	@Override
	public PrilogImage getPrilog(String documentId, String imgName) {
		return prilogRepository.getById(documentId + "-" +imgName);
	}
	
	
}
