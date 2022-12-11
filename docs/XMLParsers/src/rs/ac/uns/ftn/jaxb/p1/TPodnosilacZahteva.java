
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPodnosilac_zahteva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPodnosilac_zahteva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lice" type="{http://www.ftn.uns.ac.rs/p1}TLice"/>
 *         &lt;element name="pronalazac" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac_zahteva", namespace = "http://www.ftn.uns.ac.rs/p1", propOrder = {
    "lice",
    "pronalazac"
})
public class TPodnosilacZahteva {

    @XmlElement(name = "Lice", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TLice lice;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/p1")
    protected boolean pronalazac;

    /**
     * Gets the value of the lice property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setLice(TLice value) {
        this.lice = value;
    }

    /**
     * Gets the value of the pronalazac property.
     * 
     */
    public boolean isPronalazac() {
        return pronalazac;
    }

    /**
     * Sets the value of the pronalazac property.
     * 
     */
    public void setPronalazac(boolean value) {
        this.pronalazac = value;
    }

    @Override
    public String toString() {
        return "Podnosilac prijave: " + '\n' + "\t\t" +
                "pronalazac: " + pronalazac + "\n\t\t" +
                lice.toString();

    }
}
