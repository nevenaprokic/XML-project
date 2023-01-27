package rs.ac.uns.ftn.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

@XmlRootElement
@XmlSeeAlso({ ZahtevZaPriznanjePatenta.class })
public class ListaZahtevaPatent {

		private final List<ZahtevZaPriznanjePatenta> listaZahtevaZaPatent;

		public ListaZahtevaPatent() {
			this.listaZahtevaZaPatent = new ArrayList<>();
		}

		public ListaZahtevaPatent(List<ZahtevZaPriznanjePatenta> listaZahtevaZaPatent) {
		        this.listaZahtevaZaPatent = listaZahtevaZaPatent;
		    }

		@XmlAnyElement
		public List<ZahtevZaPriznanjePatenta> getListaZahtevaPatent() {
			return listaZahtevaZaPatent;
		}

}
