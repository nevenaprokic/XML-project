
package rs.ac.uns.ftn.jaxb.p1;

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
 *         &lt;element name="Telefon" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Faks" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlRootElement(name = "Kontakt_podaci", namespace = "http://www.ftn.uns.ac.rs/p1")
public class KontaktPodaci {

    @XmlElement(name = "Telefon", namespace = "http://www.ftn.uns.ac.rs/p1")
    protected int telefon;
    @XmlElement(name = "Faks", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String faks;
    @XmlElement(name = "Email", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String email;

    /**
     * Gets the value of the telefon property.
     * 
     */
    public int getTelefon() {
        return telefon;
    }

    /**
     * Sets the value of the telefon property.
     * 
     */
    public void setTelefon(int value) {
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

    @Override
    public String toString() {
        return "Kontakt podaci: " + '\t' +
                "telefon: " + telefon + '\n' + '\t' +
                "faks: " + faks + '\n' + '\t' +
                "email: " + email + '\n'
                ;
    }
}
