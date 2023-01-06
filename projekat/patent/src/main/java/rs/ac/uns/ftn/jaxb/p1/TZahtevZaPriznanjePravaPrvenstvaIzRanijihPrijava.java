
package rs.ac.uns.ftn.jaxb.p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for TZahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Ranija_prijava" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava", namespace = "http://www.ftn.uns.ac.rs/p1", propOrder = {
    "ranijaPrijava"
})
public class TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava {

    @XmlElement(name = "Ranija_prijava", namespace = "http://www.ftn.uns.ac.rs/p1")
    protected List<RanijaPrijava> ranijaPrijava;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the ranijaPrijava property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ranijaPrijava property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRanijaPrijava().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RanijaPrijava }
     * 
     * 
     */
    public List<RanijaPrijava> getRanijaPrijava() {
        if (ranijaPrijava == null) {
            ranijaPrijava = new ArrayList<RanijaPrijava>();
        }
        return this.ranijaPrijava;
    }

    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}


	@Override
    public String toString() {
        if(ranijaPrijava != null){
            StringBuilder ranije_prijave_str = new StringBuilder("Ranije prijave: " + "\n\t\t");
            for(RanijaPrijava prijava : ranijaPrijava){
                ranije_prijave_str.append("prijava: " + "\n\t\t\t" + prijava.toString());
            }
            return ranije_prijave_str.toString();
        }
        return  "";

    }

}
