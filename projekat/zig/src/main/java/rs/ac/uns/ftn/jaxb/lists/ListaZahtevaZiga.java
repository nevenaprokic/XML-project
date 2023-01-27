package rs.ac.uns.ftn.jaxb.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

@XmlRootElement
@XmlSeeAlso({ ZahtevZaPriznanjeZiga.class })
public class ListaZahtevaZiga {

	private final List<ZahtevZaPriznanjeZiga> listaZahtevaZaZig;

	public ListaZahtevaZiga() {
		this.listaZahtevaZaZig = new ArrayList<>();
	}

	public ListaZahtevaZiga(List<ZahtevZaPriznanjeZiga> listaZahtevaZig) {
	        this.listaZahtevaZaZig = listaZahtevaZig;
	    }

	@XmlAnyElement
	public List<ZahtevZaPriznanjeZiga> getListaZahtevaZig() {
		return listaZahtevaZaZig;
	}
}
