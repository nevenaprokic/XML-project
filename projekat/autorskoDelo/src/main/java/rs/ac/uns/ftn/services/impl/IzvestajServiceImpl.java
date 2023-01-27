package rs.ac.uns.ftn.services.impl;

import java.math.BigInteger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.jaxb.lists.ListaZahtevaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.a1.StatusZahteva;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.services.IzvestajService;
import rs.ac.uns.ftn.services.ResenjeService;

@Service
public class IzvestajServiceImpl implements IzvestajService {

	@Autowired
	private AutorskoDeloService autorskoDeloService;

	@Override
	public Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException {
		Integer brojPodnetihZahteva = 0;
		Integer brojOdbijenihZahteva = 0;
		Integer brojPrihvacenihZahteva = 0;

		ListaZahtevaAutorskoDelo zahtevi = autorskoDeloService.findAll();
		for (ZahtevZaAutorskoDelo zahtev : zahtevi.getListaZahtevaZaAutorskoDelo()) {
			if (zahtev.getDatumPodnosenja().toGregorianCalendar()
					.compareTo(izvestaj.getPocetniDatumIzvestaja().toGregorianCalendar()) >= 0
					&& izvestaj.getKrajnjiDatumIzvestaja().toGregorianCalendar()
							.compareTo(zahtev.getDatumPodnosenja().toGregorianCalendar()) >= 0) {
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
