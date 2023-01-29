package rs.ac.uns.ftn.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.ftn.dataAccess.utils.QueryUtilsResenje;
import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.lists.ListaResenja;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.jaxb.resenje.StatusResenja;
import rs.ac.uns.ftn.jaxb.resenje.TOdobren;
import rs.ac.uns.ftn.jaxb.resenje.TSluzbenik;
import rs.ac.uns.ftn.jaxb.a1.StatusZahteva;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.repository.ResenjeRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.services.ResenjeService;

@Service
public class ResenjeServiceImpl implements ResenjeService {

	@Autowired
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;

	@Override
	public ArrayList<Resenje> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewFile(Resenje resenje, String user) {
		TSluzbenik sluzbenik = getSluzbenik(user);
		resenje.setSluzbenik(sluzbenik);
		
		String documentId = generateDocumentId();
		ZahtevZaAutorskoDelo zahtev = autorskoDeloService.getZahtevZaAutorskoDeloById(resenje.getIdAutorskogDela().getIdA());
		
		if(zahtev.getStatus() != StatusZahteva.NEOBRADJEN) {
			throw new BadRequestException(ErrorMessageConstants.DOCUMENT_ALREADY_HAS_RESENJE);
		}
		
		if(resenje.getStatus() == StatusResenja.ODOBREN) {
			zahtev.setStatus(StatusZahteva.ODOBREN);
			if(resenje.getDodatak() instanceof TOdobren) {
				zahtev.setBrojPrijave(((TOdobren) resenje.getDodatak()).getSifra());
			}
		}
		if(resenje.getStatus() == StatusResenja.ODBIJEN) {
			zahtev.setStatus(StatusZahteva.ODBIJEN);
		}
		zahtev.setIdAutorskogDela(resenje.getIdAutorskogDela());
		autorskoDeloService.saveFile(zahtev, resenje.getIdAutorskogDela().getIdA());
		resenjeRepository.saveResenje(resenje, documentId);
	}
	
	private TSluzbenik getSluzbenik(String user) {
		String[] tokens = user.split(",");
		String name = tokens[2];
		String surname = tokens[1];
		
		TSluzbenik sluzbenik = new TSluzbenik();
		sluzbenik.setIme(name);
		sluzbenik.setPrezime(surname);
		return sluzbenik;
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = resenjeRepository.getLenghtOfCollection();
		return "R" + String.valueOf(curretnNumber + 1); 
	}

	@Override
	public ListaResenja findAll() throws XMLDBException, JAXBException {
		ResourceSet result = resenjeRepository.getByXQuery(QueryUtilsResenje.FIND_ALL);
		return resourceSetToList(result);
	}
	
	private ListaResenja resourceSetToList(ResourceSet result) throws XMLDBException, JAXBException {
		List<Resenje> zahteviList= new ArrayList<>();
		ResourceIterator i = result.getIterator();

        while(i.hasMoreResources()) {
    		XMLResource xmlResource = (XMLResource) i.nextResource();
    		Resenje resenje = JaxbMapper.unmarshalResenjeFromNode(xmlResource.getContentAsDOM());
            zahteviList.add(resenje);
        }
		return new ListaResenja(zahteviList);
	}
}
