
package rs.ac.uns.ftn.jaxb.p1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.zajednicko.*;
/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Adresa"/>
 *         &lt;element name="Nacin_dostavljanja">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="elektronski"/>
 *               &lt;enumeration value="papirna_forma"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Podaci_o_dostavljanju", namespace = "http://www.ftn.uns.ac.rs/p1")
public class PodaciODostavljanju {

    @XmlElement(name = "Adresa", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected Adresa adresa;
    @XmlElement(name = "Nacin_dostavljanja", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected NacinDostavljanja nacinDostavljanja;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link Adresa }
     *     
     */
    public Adresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adresa }
     *     
     */
    public void setAdresa(Adresa value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the nacinDostavljanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public NacinDostavljanja getNacinDostavljanja() {
        return nacinDostavljanja;
    }

    /**
     * Sets the value of the nacinDostavljanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacinDostavljanja(NacinDostavljanja value) {
        this.nacinDostavljanja = value;
    }

    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	@Override
    public String toString() {
        return "Podaci o dostavljanju: " + "\n\t\t" +
                "adresa: " + adresa + "\n\t\t" +
                "nacin dostavljanja: " + nacinDostavljanja + '\n';
    }
}
