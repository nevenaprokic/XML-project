package rs.ac.uns.ftn.jaxb.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.ac.uns.ftn.jaxb.resenje.Resenje;

@XmlRootElement
@XmlSeeAlso({ Resenje.class })
public class ListaResenja {

	private final List<Resenje> listaResenja;

	public ListaResenja() {
		this.listaResenja = new ArrayList<>();
	}

	public ListaResenja(List<Resenje> listaZahtevaZig) {
	        this.listaResenja = listaZahtevaZig;
	    }

	@XmlAnyElement
	public List<Resenje> getListaResenja() {
		return listaResenja;
	}
}
