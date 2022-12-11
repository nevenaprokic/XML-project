
package rs.ac.uns.ftn.jaxb.zajednicko;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TFizicko_lice complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TFizicko_lice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/zaj}TLice">
 *       &lt;sequence>
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFizicko_lice", namespace = "http://www.ftn.uns.ac.rs/zaj", propOrder = {
    "ime",
    "prezime"
})
public class TFizickoLice
    extends TLice
{

    @XmlElement(name = "Ime", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String prezime;

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

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Ime: ");
        buffer.append(ime);

        buffer.append("\n\t\t - Prezime: ");
        buffer.append(prezime);

        buffer.append(super.toString());

        return buffer.toString();
    }
}
