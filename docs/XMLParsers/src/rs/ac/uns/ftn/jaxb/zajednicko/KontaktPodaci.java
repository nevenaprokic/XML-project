
package rs.ac.uns.ftn.jaxb.zajednicko;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Telefon">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0][6][0-9]/[0-9]{3,3}-[0-9]{3,4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Faks">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{3,4}/[0-9]{5,7}"/>
 *               &lt;minLength value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[^@]+@[^\.]+\..+"/>
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
@XmlRootElement(name = "Kontakt_podaci", namespace = "http://www.ftn.uns.ac.rs/zaj")
public class KontaktPodaci {

    @XmlElement(name = "Telefon", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String telefon;
    @XmlElement(name = "Faks", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String faks;
    @XmlElement(name = "Email", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String email;

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

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t\t - Telefon: ");
        buffer.append(telefon);
        buffer.append("\n\t\t\t - Email: ");
        buffer.append(email);
        buffer.append("\n\t\t\t - Faks: ");
        buffer.append(faks);

        return buffer.toString();
    }
}
