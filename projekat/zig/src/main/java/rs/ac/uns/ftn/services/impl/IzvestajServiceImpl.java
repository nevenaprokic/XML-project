package rs.ac.uns.ftn.services.impl;

import java.math.BigInteger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaZiga;
import rs.ac.uns.ftn.jaxb.z1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.services.IzvestajService;
import rs.ac.uns.ftn.services.ResenjeService;
import rs.ac.uns.ftn.services.ZigService;

@Service
public class IzvestajServiceImpl implements IzvestajService {

	@Autowired
	private ZigService zigService;

	@Override
	public Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException {
		Integer brojPodnetihZahteva = 0;
		Integer brojOdbijenihZahteva = 0;
		Integer brojPrihvacenihZahteva = 0;

		ListaZahtevaZiga zahtevi = zigService.findAll();
		for (ZahtevZaPriznanjeZiga zahtev : zahtevi.getListaZahtevaZig()) {
			if (zahtev.getDatumPodnosenjaPrijave().toGregorianCalendar()
					.compareTo(izvestaj.getPocetniDatumIzvestaja().toGregorianCalendar()) >= 0
					&& izvestaj.getKrajnjiDatumIzvestaja().toGregorianCalendar()
							.compareTo(zahtev.getDatumPodnosenjaPrijave().toGregorianCalendar()) >= 0) {
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
