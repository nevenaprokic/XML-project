
package rs.ac.uns.ftn.jaxb.z1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTakse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TTakse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Osnovna_taksa" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Graficko_resenje" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Za_klasu" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Ukupan_iznos_takse" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTakse", namespace = "http://ftn.uns.ac.rs/zig", propOrder = {
        "osnovnaTaksa",
        "grafickoResenje",
        "zaKlasu",
        "ukupanIznosTakse"
})
public class TTakse {

    @XmlElement(name = "Osnovna_taksa", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger osnovnaTaksa;
    @XmlElement(name = "Graficko_resenje", namespace = "http://ftn.uns.ac.rs/zig")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger grafickoResenje;
    @XmlElement(name = "Za_klasu", namespace = "http://ftn.uns.ac.rs/zig")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zaKlasu;
    @XmlElement(name = "Ukupan_iznos_takse", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger ukupanIznosTakse;

    /**
     * Gets the value of the osnovnaTaksa property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getOsnovnaTaksa() {
        return osnovnaTaksa;
    }

    /**
     * Sets the value of the osnovnaTaksa property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setOsnovnaTaksa(BigInteger value) {
        this.osnovnaTaksa = value;
    }

    /**
     * Gets the value of the grafickoResenje property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getGrafickoResenje() {
        return grafickoResenje;
    }

    /**
     * Sets the value of the grafickoResenje property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setGrafickoResenje(BigInteger value) {
        this.grafickoResenje = value;
    }

    /**
     * Gets the value of the zaKlasu property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getZaKlasu() {
        return zaKlasu;
    }

    /**
     * Sets the value of the zaKlasu property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setZaKlasu(BigInteger value) {
        this.zaKlasu = value;
    }

    /**
     * Gets the value of the ukupanIznosTakse property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getUkupanIznosTakse() {
        return ukupanIznosTakse;
    }

    /**
     * Sets the value of the ukupanIznosTakse property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setUkupanIznosTakse(BigInteger value) {
        this.ukupanIznosTakse = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Osnovna taksa: ");
        buffer.append(osnovnaTaksa);

        buffer.append("\n\t\t - Graficko resenje: ");
        buffer.append(grafickoResenje);

        buffer.append("\n\t\t - Taksa za klasu: ");
        buffer.append(zaKlasu);

        buffer.append("\n\t\t - Ukupno: ");
        buffer.append(ukupanIznosTakse);

        return buffer.toString();
    }
}
