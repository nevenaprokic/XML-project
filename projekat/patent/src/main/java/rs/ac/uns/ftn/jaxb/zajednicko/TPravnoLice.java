
package rs.ac.uns.ftn.jaxb.zajednicko;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPravno_lice complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TPravno_lice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/zaj}TLice">
 *       &lt;sequence>
 *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPravno_lice", namespace = "http://www.ftn.uns.ac.rs/zaj", propOrder = {
    "naziv"
})
public class TPravnoLice
    extends TLice
{

    @XmlElement(name = "Naziv", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected String naziv;

    /**
     * Gets the value of the naziv property.
     *
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Naziv: ");
        buffer.append(naziv);

        buffer.append(super.toString());

        return buffer.toString();
    }
}
