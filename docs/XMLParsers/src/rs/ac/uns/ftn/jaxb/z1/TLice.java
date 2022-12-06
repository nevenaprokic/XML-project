
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="adresa" type="{http://ftn.uns.ac.rs/z1}TAdresa"/>
 *         &lt;element name="telefon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLice", namespace = "http://ftn.uns.ac.rs/z1", propOrder = {
    "ime",
    "prezime",
    "adresa",
    "telefon",
    "email",
    "faks"
})
public class TLice {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String ime;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String prezime;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TAdresa adresa;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String telefon;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String email;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String faks;

    /**
     * Gets the value of the ime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the adresa property.
     *
     * @return
     *     possible object is
     *     {@link TAdresa }
     *
     */
    public TAdresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     *
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *
     */
    public void setAdresa(TAdresa value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the telefon property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Sets the value of the telefon property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTelefon(String value) {
        this.telefon = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the faks property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFaks() {
        return faks;
    }

    /**
     * Sets the value of the faks property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFaks(String value) {
        this.faks = value;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Kredencijali: ");
        buffer.append(ime);
        buffer.append(" ");
        buffer.append(prezime);

        buffer.append("\n\t\t - Adresa: ");
        buffer.append(adresa);

        buffer.append("\n\t\t - Kontakt: ");
        buffer.append(telefon);
        buffer.append("\n\t\t\t\t\t");
        buffer.append(email);
        buffer.append("\n\t\t\t\t\t");
        buffer.append(faks);

        return buffer.toString();
    }
}
