package rs.ac.uns.ftn.jaxb.p1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		
})
@XmlRootElement(name = "Podaci_o_dodatnoj_prijavi", namespace = "http://www.ftn.uns.ac.rs/p1")
public class PodaciODodatnojPrijavi {
	@XmlElement(name = "Tip_prijave", namespace = "http://www.ftn.uns.ac.rs/p1")
    protected TipDodatnePrijave tipDodatnePrijave;
    @XmlElement(name = "Broj_prvobitne_prijave", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String brojPrvobitnePrijave;
    @XmlElement(name = "Datum_podnosenja_prvobitne_prijave", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected XMLGregorianCalendar datumPrvobitnePrijave;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
	
    
    
    public TipDodatnePrijave getTipDodatnePrijave() {
		return tipDodatnePrijave;
	}
	public void setTipDodatnePrijave(TipDodatnePrijave tipDodatnePrijave) {
		this.tipDodatnePrijave = tipDodatnePrijave;
	}
	public String getBrojPrvobitnePrijave() {
		return brojPrvobitnePrijave;
	}
	public void setBrojPrvobitnePrijave(String brojPrvobitnePrijave) {
		this.brojPrvobitnePrijave = brojPrvobitnePrijave;
	}
	public XMLGregorianCalendar getDatumPrvobitnePrijave() {
		return datumPrvobitnePrijave;
	}
	public void setDatumPrvobitnePrijave(XMLGregorianCalendar datumPrvobitnePrijave) {
		this.datumPrvobitnePrijave = datumPrvobitnePrijave;
	}
	public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}
	public void setOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
	}
    
	@Override
    public String toString() {
        return "Podaci o dodatnoj prijavi: " + "\n\t\t" +
                "tip_prijave: " + tipDodatnePrijave.toString() + "\n\t\t" +
                "broj_prijave: " + brojPrvobitnePrijave + "\n\t\t" +
                "datum_prijema_prijave: " + datumPrvobitnePrijave + '\n';
    }
    

}
