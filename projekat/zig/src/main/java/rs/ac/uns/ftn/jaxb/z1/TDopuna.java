
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TDopuna complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TDopuna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Putanja_do_fajla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dostavljeno" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDopuna", namespace = "http://ftn.uns.ac.rs/zig", propOrder = {
        "putanjaDoFajla",
        "dostavljeno"
})
public class TDopuna {

    @XmlElement(name = "Putanja_do_fajla", namespace = "http://ftn.uns.ac.rs/zig")
    protected String putanjaDoFajla;
    @XmlElement(name = "Dostavljeno", namespace = "http://ftn.uns.ac.rs/zig")
    protected Boolean dostavljeno;

    /**
     * Gets the value of the putanjaDoFajla property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPutanjaDoFajla() {
        return putanjaDoFajla;
    }

    /**
     * Sets the value of the putanjaDoFajla property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPutanjaDoFajla(String value) {
        this.putanjaDoFajla = value;
    }

    /**
     * Gets the value of the dostavljeno property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isDostavljeno() {
        return dostavljeno;
    }

    /**
     * Sets the value of the dostavljeno property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setDostavljeno(Boolean value) {
        this.dostavljeno = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t\t - Putanja do fajla: ");
        buffer.append(putanjaDoFajla);
        buffer.append("\n\t\t\t - Da li je dostavljeno: ");
        if (dostavljeno) {
            buffer.append("da");
        } else {
            buffer.append("ne");
        }

        return buffer.toString();
    }
}
