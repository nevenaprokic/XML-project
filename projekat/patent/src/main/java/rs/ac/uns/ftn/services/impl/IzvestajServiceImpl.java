package rs.ac.uns.ftn.services.impl;

import java.math.BigInteger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;
import rs.ac.uns.ftn.services.IzvestajService;
import rs.ac.uns.ftn.services.PatentService;

@Service
public class IzvestajServiceImpl implements IzvestajService {

	@Autowired
	private PatentService patentService;

	@Override
	public Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException {
		Integer brojPodnetihZahteva = 0;
		Integer brojOdbijenihZahteva = 0;
		Integer brojPrihvacenihZahteva = 0;

		ListaZahtevaPatent zahtevi = patentService.findAll();
		for (ZahtevZaPriznanjePatenta zahtev : zahtevi.getListaZahtevaPatent()) {
			if (zahtev.getDatumPrijemaPrijave().toGregorianCalendar()
					.compareTo(izvestaj.getPocetniDatumIzvestaja().toGregorianCalendar()) >= 0
					&& izvestaj.getKrajnjiDatumIzvestaja().toGregorianCalendar()
							.compareTo(zahtev.getDatumPrijemaPrijave().toGregorianCalendar()) >= 0) {
				brojPodnetihZahteva += 1;
			}
			if(zahtev.getStatus() == StatusZahteva.ODBIJEN) {
				brojOdbijenihZahteva += 1;
			}
			if(zahtev.getStatus() == StatusZahteva.ODOBREN) {
				brojPrihvacenihZahteva += 1;
			}
		}

		izvestaj.setBrojOdbijenihZahteva(BigInteger.valueOf(brojOdbijenihZahteva.intValue()));
		izvestaj.setBrojPodnetihZahteva(BigInteger.valueOf(brojPodnetihZahteva));
		izvestaj.setBrojPrihvacenihZahteva(BigInteger.valueOf(brojPrihvacenihZahteva));
		return izvestaj;
	}

}
