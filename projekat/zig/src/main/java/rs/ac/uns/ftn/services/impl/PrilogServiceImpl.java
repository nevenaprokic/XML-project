package rs.ac.uns.ftn.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.jaxb.z1.TDopuna;
import rs.ac.uns.ftn.jaxb.z1.TPrilozi;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.PrilogMapper;
import rs.ac.uns.ftn.repository.PrilogRepository;
import rs.ac.uns.ftn.services.PrilogService;

@Service
public class PrilogServiceImpl implements PrilogService{
	
	@Autowired
	private PrilogRepository prilogRepository;

	@Override
	public void extractPrilozi(ZahtevZaPriznanjeZiga zahtev, String documentId) {
		TPrilozi prilozi = zahtev.getPriloziUzZahtev();
		if(prilozi == null) {
			return;
		}
		List<TDopuna> dopune = createListOfDopuna(prilozi);
		for (TDopuna dopuna : dopune) {
			if(dopuna!=null && dopuna.isDostavljeno()) {
				String path = savePrilog(dopuna, documentId);
				dopuna.setPutanjaDoFajla(path);
			}
		}
	}
	private List<TDopuna> createListOfDopuna(TPrilozi prilozi) {
		List<TDopuna> dopune = new ArrayList<TDopuna>();
		dopune.add(prilozi.getSpisakRobeIUsluga());
		dopune.add(prilozi.getPunomocje());
		dopune.add(prilozi.getPunomocjeRanijePrilozeno());
		dopune.add(prilozi.getPunomocjeNaknadnoDostavljeno());
		dopune.add(prilozi.getOpstiAktOKolektivnomZiguGarancije());
		dopune.add(prilozi.getDokazOPravuPrvenstva());
		dopune.add(prilozi.getDokazOUplatiTakse());
		dopune.add(prilozi.getPrimerakZnaka());
		return dopune;
	}
	private String savePrilog(TDopuna prilogDto, String documentId) {
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
