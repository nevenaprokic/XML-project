
package rs.ac.uns.ftn.jaxb.izvestaj;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


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
 *         &lt;element name="BrojPodnetihZahteva" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="BrojPrihvacenihZahteva" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="BrojOdbijenihZahteva" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *       &lt;/sequence>
 *       &lt;attribute name="pocetni_datum_izvestaja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="krajnji_datum_izvestaja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "brojPodnetihZahteva",
    "brojPrihvacenihZahteva",
    "brojOdbijenihZahteva"
})
@XmlRootElement(name = "Izvestaj", namespace = "http://ftn.uns.ac.rs/izvestaj")
public class Izvestaj {

    @XmlElement(name = "BrojPodnetihZahteva", namespace = "http://ftn.uns.ac.rs/izvestaj")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger brojPodnetihZahteva;
    @XmlElement(name = "BrojPrihvacenihZahteva", namespace = "http://ftn.uns.ac.rs/izvestaj")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger brojPrihvacenihZahteva;
    @XmlElement(name = "BrojOdbijenihZahteva", namespace = "http://ftn.uns.ac.rs/izvestaj")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger brojOdbijenihZahteva;
    @XmlAttribute(name = "pocetni_datum_izvestaja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pocetniDatumIzvestaja;
    @XmlAttribute(name = "krajnji_datum_izvestaja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar krajnjiDatumIzvestaja;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the brojPodnetihZahteva property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getBrojPodnetihZahteva() {
        return brojPodnetihZahteva;
    }

    /**
     * Sets the value of the brojPodnetihZahteva property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setBrojPodnetihZahteva(BigInteger value) {
        this.brojPodnetihZahteva = value;
    }

    /**
     * Gets the value of the brojPrihvacenihZahteva property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getBrojPrihvacenihZahteva() {
        return brojPrihvacenihZahteva;
    }

    /**
     * Sets the value of the brojPrihvacenihZahteva property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setBrojPrihvacenihZahteva(BigInteger value) {
        this.brojPrihvacenihZahteva = value;
    }

    /**
     * Gets the value of the brojOdbijenihZahteva property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getBrojOdbijenihZahteva() {
        return brojOdbijenihZahteva;
    }

    /**
     * Sets the value of the brojOdbijenihZahteva property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setBrojOdbijenihZahteva(BigInteger value) {
        this.brojOdbijenihZahteva = value;
    }

    /**
     * Gets the value of the pocetniDatumIzvestaja property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getPocetniDatumIzvestaja() {
        return pocetniDatumIzvestaja;
    }

    /**
     * Sets the value of the pocetniDatumIzvestaja property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setPocetniDatumIzvestaja(XMLGregorianCalendar value) {
        this.pocetniDatumIzvestaja = value;
    }

    /**
     * Gets the value of the krajnjiDatumIzvestaja property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getKrajnjiDatumIzvestaja() {
        return krajnjiDatumIzvestaja;
    }

    /**
     * Sets the value of the krajnjiDatumIzvestaja property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setKrajnjiDatumIzvestaja(XMLGregorianCalendar value) {
        this.krajnjiDatumIzvestaja = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     *
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     *
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
