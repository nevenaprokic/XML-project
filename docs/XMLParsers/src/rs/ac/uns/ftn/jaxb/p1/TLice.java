
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TLice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TLice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Adresa"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Kontakt_podaci"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLice", namespace = "http://www.ftn.uns.ac.rs/p1", propOrder = {
    "adresa",
    "kontaktPodaci"
})
@XmlSeeAlso({
    TPravnoLice.class,
    TFizickoLice.class
})
public abstract class TLice {

    @XmlElement(name = "Adresa", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected Adresa adresa;
    @XmlElement(name = "Kontakt_podaci", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected KontaktPodaci kontaktPodaci;

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
     * Gets the value of the kontaktPodaci property.
     * 
     * @return
     *     possible object is
     *     {@link KontaktPodaci }
     *     
     */
    public KontaktPodaci getKontaktPodaci() {
        return kontaktPodaci;
    }

    /**
     * Sets the value of the kontaktPodaci property.
     * 
     * @param value
     *     allowed object is
     *     {@link KontaktPodaci }
     *     
     */
    public void setKontaktPodaci(KontaktPodaci value) {
        this.kontaktPodaci = value;
    }

    @Override
    public String toString() {
        return  adresa + "\n\t\t" + kontaktPodaci;
    }

}
