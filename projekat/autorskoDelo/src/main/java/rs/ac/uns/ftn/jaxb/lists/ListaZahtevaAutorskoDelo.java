package rs.ac.uns.ftn.jaxb.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;


@XmlRootElement
@XmlSeeAlso({ZahtevZaAutorskoDelo.class})
public class ListaZahtevaAutorskoDelo {
    private final List<ZahtevZaAutorskoDelo> listaZahtevaZaAutorskoDelo;

    public ListaZahtevaAutorskoDelo() {
        this.listaZahtevaZaAutorskoDelo = new ArrayList<>();
    }

    public ListaZahtevaAutorskoDelo(List<ZahtevZaAutorskoDelo> listaZahtevaZaAutorskoDelo) {
        this.listaZahtevaZaAutorskoDelo = listaZahtevaZaAutorskoDelo;
    }

    @XmlAnyElement
    public List<ZahtevZaAutorskoDelo> getListaZahtevaZaAutorskoDelo() {
        return listaZahtevaZaAutorskoDelo;
    }
}
