
package rs.ac.uns.ftn.jaxb.z1;

import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;

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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zaj}Kontakt_podaci"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "kontaktPodaci"
})
@XmlRootElement(name = "Zajednicki_predstavnik", namespace = "http://ftn.uns.ac.rs/zig")
public class ZajednickiPredstavnik {

    @XmlElement(name = "Kontakt_podaci", namespace = "http://www.ftn.uns.ac.rs/zaj", required = true)
    protected KontaktPodaci kontaktPodaci;

    /**
     * Gets the value of the kontaktPodaci property.
     *
     * @return possible object is
     * {@link KontaktPodaci }
     */
    public KontaktPodaci getKontaktPodaci() {
        return kontaktPodaci;
    }

    /**
     * Sets the value of the kontaktPodaci property.
     *
     * @param value allowed object is
     *              {@link KontaktPodaci }
     */
    public void setKontaktPodaci(KontaktPodaci value) {
        this.kontaktPodaci = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Kontakt: ");
        buffer.append(kontaktPodaci);

        return buffer.toString();
    }
}
