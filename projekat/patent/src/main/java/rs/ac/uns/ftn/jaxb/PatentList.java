package rs.ac.uns.ftn.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

@XmlRootElement
@XmlSeeAlso({ZahtevZaPriznanjePatenta.class})
public class PatentList {
    private final List<ZahtevZaPriznanjePatenta> zahtevList;

    public PatentList() {
        this.zahtevList = new ArrayList<>();
    }

    public PatentList(List<ZahtevZaPriznanjePatenta> zahtevList) {
        this.zahtevList = zahtevList;
    }

    @XmlAnyElement
    public List<ZahtevZaPriznanjePatenta> getZahtevList() {
        return zahtevList;
    }
}