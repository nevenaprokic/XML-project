
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPrimalac_zahteva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPrimalac_zahteva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Adresa"/>
 *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrimalac_zahteva", namespace = "http://www.ftn.uns.ac.rs/p1", propOrder = {

})
public class TPrimalacZahteva {

    @XmlElement(name = "Adresa", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected Adresa adresa;
    @XmlElement(name = "Naziv", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String naziv;

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
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    @Override
    public String toString() {
        return "Primalac zahteva: " + "\n\t\t" + adresa + '\n' + "\t\t" +
                "naziv ustanove: " + naziv + "\n\t\t" + '\n';
    }
}
