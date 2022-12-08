
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Datum_podnosenja" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Broj_prijave" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Dvoslovna_oznaka_drzave">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumPodnosenja",
    "brojPrijave",
    "dvoslovnaOznakaDrzave"
})
@XmlRootElement(name = "Ranija_prijava", namespace = "http://www.ftn.uns.ac.rs/p1")
public class RanijaPrijava {

    @XmlElement(name = "Datum_podnosenja", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenja;
    @XmlElement(name = "Broj_prijave", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String brojPrijave;
    @XmlElement(name = "Dvoslovna_oznaka_drzave", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String dvoslovnaOznakaDrzave;

    /**
     * Gets the value of the datumPodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodnosenja(XMLGregorianCalendar value) {
        this.datumPodnosenja = value;
    }

    /**
     * Gets the value of the brojPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPrijave(String value) {
        this.brojPrijave = value;
    }

    /**
     * Gets the value of the dvoslovnaOznakaDrzave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDvoslovnaOznakaDrzave() {
        return dvoslovnaOznakaDrzave;
    }

    /**
     * Sets the value of the dvoslovnaOznakaDrzave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDvoslovnaOznakaDrzave(String value) {
        this.dvoslovnaOznakaDrzave = value;
    }

    @Override
    public String toString() {
        return "Ranija prijava:"  + "\n\t\t" +
                "datum podnosenja prijave: " + datumPodnosenja + "\n\t\t" +
                "broj prijave: " + brojPrijave + "\n\t\t" +
                "dvoslovna oznaka drzave: " + dvoslovnaOznakaDrzave + '\n'
                ;
    }
}

