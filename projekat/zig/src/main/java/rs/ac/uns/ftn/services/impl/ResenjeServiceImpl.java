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

import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.dataAccess.utils.QueryUtilsResenje;
import rs.ac.uns.ftn.jaxb.lists.ListaResenja;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;
import rs.ac.uns.ftn.jaxb.z1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.repository.ResenjeRepository;
import rs.ac.uns.ftn.services.ResenjeService;
import rs.ac.uns.ftn.services.ZigService;

@Service
public class ResenjeServiceImpl implements ResenjeService {

	@Autowired
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private ZigService zigService;

	@Override
	public ArrayList<Resenje> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewFile(Resenje resenje) {
		String documentId = generateDocumentId();
		ZahtevZaPriznanjeZiga zahtev = zigService.getZahtevZaPriznanjeZiga(resenje.getIdZiga().getIdZ());
		zahtev.setStatus(StatusZahteva.OBRADJEN);
		//save zahtev
		resenjeRepository.saveResenje(resenje, documentId);
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
