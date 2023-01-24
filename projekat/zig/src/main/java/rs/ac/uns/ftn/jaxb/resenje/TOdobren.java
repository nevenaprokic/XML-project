
package main.java.rs.ac.uns.ftn.jaxb.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TOdobren complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TOdobren">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ftn.uns.ac.rs/resenje}TResenje">
 *       &lt;sequence>
 *         &lt;element name="Sifra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOdobren", namespace = "http://ftn.uns.ac.rs/resenje", propOrder = {
    "sifra"
})
public class TOdobren
    extends TResenje
{

    @XmlElement(name = "Sifra", namespace = "http://ftn.uns.ac.rs/resenje", required = true)
    protected String sifra;

    /**
     * Gets the value of the sifra property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Sets the value of the sifra property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSifra(String value) {
        this.sifra = value;
    }

}
