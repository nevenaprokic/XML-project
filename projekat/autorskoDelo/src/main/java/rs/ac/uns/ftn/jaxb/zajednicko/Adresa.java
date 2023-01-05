
package rs.ac.uns.ftn.jaxb.zajednicko;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
 *         &lt;element name="Drzava" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Grad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ulica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Broj" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Postanski_broj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="11000"/>
 *               &lt;maxInclusive value="40000"/>
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
@XmlRootElement(name = "Adresa", namespace = "http://www.ftn.uns.ac.rs/zaj")
public class Adresa {

    @XmlElement(name = "Drzava", namespace = "http://www.ftn.uns.ac.rs/zaj", required = false, defaultValue = "")
    protected String drzava;
    @XmlElement(name = "Grad", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String grad;
    @XmlElement(name = "Ulica", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String ulica;
    @XmlElement(name = "Broj", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true, nillable = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;
    @XmlElement(name = "Postanski_broj", namespace = "http://www.ftn.uns.ac.rs/zaj", required = false)
    protected Integer postanskiBroj;

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
    @JsonIgnore
    public void setDrzava(String value) {
        this.drzava = value;
    }

    /**
     * Gets the value of the grad property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGrad(String value) {
        this.grad = value;
    }

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
     * Gets the value of the postanskiBroj property.
     *
     */
    @JsonIgnore
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
        if(broj!=null) {
        	buffer.append(" ");
        	buffer.append(broj);
        }
        buffer.append(", ");
        buffer.append(grad);
        
        if(postanskiBroj!=null) {
        	buffer.append(", ");
        	buffer.append(postanskiBroj);
        }
        if(drzava!=null && drzava!="") {
        	buffer.append(", ");
        	buffer.append(drzava);
        }
        

        return buffer.toString();
    }
}
