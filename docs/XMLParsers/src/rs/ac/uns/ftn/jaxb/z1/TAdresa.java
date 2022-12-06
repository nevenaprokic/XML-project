
package rs.ac.uns.ftn.jaxb.z1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TAdresa complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TAdresa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ulica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="broj" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drzava" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postanski_broj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="11000"/>
 *               &lt;maxInclusive value="40000"/>
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
@XmlType(name = "TAdresa", namespace = "http://ftn.uns.ac.rs/z1", propOrder = {
    "ulica",
    "broj",
    "mesto",
    "drzava",
    "postanskiBroj"
})
public class TAdresa {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String ulica;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String mesto;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String drzava;
    @XmlElement(name = "postanski_broj", namespace = "http://ftn.uns.ac.rs/z1")
    protected int postanskiBroj;

    /**
     * Gets the value of the ulica property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Sets the value of the ulica property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUlica(String value) {
        this.ulica = value;
    }

    /**
     * Gets the value of the broj property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setBroj(BigInteger value) {
        this.broj = value;
    }

    /**
     * Gets the value of the mesto property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the drzava property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDrzava() {
        return drzava;
    }

    /**
     * Sets the value of the drzava property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDrzava(String value) {
        this.drzava = value;
    }

    /**
     * Gets the value of the postanskiBroj property.
     *
     */
    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    /**
     * Sets the value of the postanskiBroj property.
     *
     */
    public void setPostanskiBroj(int value) {
        this.postanskiBroj = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(ulica);
        buffer.append(", ");
        buffer.append(broj);
        buffer.append(", ");
        buffer.append(mesto);
        buffer.append(", ");
        buffer.append(postanskiBroj);
        buffer.append(", ");
        buffer.append(drzava);

        return buffer.toString();
    }
}
