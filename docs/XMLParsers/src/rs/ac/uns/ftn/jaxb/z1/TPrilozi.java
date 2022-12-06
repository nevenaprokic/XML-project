
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPrilozi complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TPrilozi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primerak_znaka" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spisak_robe_i_usluga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="punomocje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="punomocje_ranije_prilozeno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="punomocje_naknadno_dostavljeno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opsti_akt_o_kolektivnom_zigu_garancije" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dokaz_o_pravu_prvenstva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dokaz_o_uplati_takse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrilozi", namespace = "http://ftn.uns.ac.rs/z1", propOrder = {
    "primerakZnaka",
    "spisakRobeIUsluga",
    "punomocje",
    "punomocjeRanijePrilozeno",
    "punomocjeNaknadnoDostavljeno",
    "opstiAktOKolektivnomZiguGarancije",
    "dokazOPravuPrvenstva",
    "dokazOUplatiTakse"
})
public class TPrilozi {

    @XmlElement(name = "primerak_znaka", namespace = "http://ftn.uns.ac.rs/z1")
    protected String primerakZnaka;
    @XmlElement(name = "spisak_robe_i_usluga", namespace = "http://ftn.uns.ac.rs/z1")
    protected String spisakRobeIUsluga;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/z1")
    protected String punomocje;
    @XmlElement(name = "punomocje_ranije_prilozeno", namespace = "http://ftn.uns.ac.rs/z1")
    protected String punomocjeRanijePrilozeno;
    @XmlElement(name = "punomocje_naknadno_dostavljeno", namespace = "http://ftn.uns.ac.rs/z1")
    protected String punomocjeNaknadnoDostavljeno;
    @XmlElement(name = "opsti_akt_o_kolektivnom_zigu_garancije", namespace = "http://ftn.uns.ac.rs/z1")
    protected String opstiAktOKolektivnomZiguGarancije;
    @XmlElement(name = "dokaz_o_pravu_prvenstva", namespace = "http://ftn.uns.ac.rs/z1")
    protected String dokazOPravuPrvenstva;
    @XmlElement(name = "dokaz_o_uplati_takse", namespace = "http://ftn.uns.ac.rs/z1")
    protected String dokazOUplatiTakse;

    /**
     * Gets the value of the primerakZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrimerakZnaka() {
        return primerakZnaka;
    }

    /**
     * Sets the value of the primerakZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrimerakZnaka(String value) {
        this.primerakZnaka = value;
    }

    /**
     * Gets the value of the spisakRobeIUsluga property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSpisakRobeIUsluga() {
        return spisakRobeIUsluga;
    }

    /**
     * Sets the value of the spisakRobeIUsluga property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSpisakRobeIUsluga(String value) {
        this.spisakRobeIUsluga = value;
    }

    /**
     * Gets the value of the punomocje property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPunomocje() {
        return punomocje;
    }

    /**
     * Sets the value of the punomocje property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPunomocje(String value) {
        this.punomocje = value;
    }

    /**
     * Gets the value of the punomocjeRanijePrilozeno property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPunomocjeRanijePrilozeno() {
        return punomocjeRanijePrilozeno;
    }

    /**
     * Sets the value of the punomocjeRanijePrilozeno property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPunomocjeRanijePrilozeno(String value) {
        this.punomocjeRanijePrilozeno = value;
    }

    /**
     * Gets the value of the punomocjeNaknadnoDostavljeno property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPunomocjeNaknadnoDostavljeno() {
        return punomocjeNaknadnoDostavljeno;
    }

    /**
     * Sets the value of the punomocjeNaknadnoDostavljeno property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPunomocjeNaknadnoDostavljeno(String value) {
        this.punomocjeNaknadnoDostavljeno = value;
    }

    /**
     * Gets the value of the opstiAktOKolektivnomZiguGarancije property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOpstiAktOKolektivnomZiguGarancije() {
        return opstiAktOKolektivnomZiguGarancije;
    }

    /**
     * Sets the value of the opstiAktOKolektivnomZiguGarancije property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOpstiAktOKolektivnomZiguGarancije(String value) {
        this.opstiAktOKolektivnomZiguGarancije = value;
    }

    /**
     * Gets the value of the dokazOPravuPrvenstva property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDokazOPravuPrvenstva() {
        return dokazOPravuPrvenstva;
    }

    /**
     * Sets the value of the dokazOPravuPrvenstva property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDokazOPravuPrvenstva(String value) {
        this.dokazOPravuPrvenstva = value;
    }

    /**
     * Gets the value of the dokazOUplatiTakse property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDokazOUplatiTakse() {
        return dokazOUplatiTakse;
    }

    /**
     * Sets the value of the dokazOUplatiTakse property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDokazOUplatiTakse(String value) {
        this.dokazOUplatiTakse = value;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Primerak znaka: ");
        buffer.append(primerakZnaka);

        buffer.append("\n\t\t - Spisak robe i usluga: ");
        buffer.append(spisakRobeIUsluga);

        buffer.append("\n\t\t - Punomocje: ");
        buffer.append(punomocje);

        buffer.append("\n\t\t - Punomocje koje je ranije prilozeno: ");
        buffer.append(punomocjeRanijePrilozeno);

        buffer.append("\n\t\t - Punomocje naknadno dostavljeno: ");
        buffer.append(punomocjeNaknadnoDostavljeno);

        buffer.append("\n\t\t - Opsti akt o kolektivnom zigu generacije: ");
        buffer.append(opstiAktOKolektivnomZiguGarancije);

        buffer.append("\n\t\t - Dokaz o pravu privenstva: ");
        buffer.append(dokazOPravuPrvenstva);

        buffer.append("\n\t\t - Dokaz o uplati takse: ");
        buffer.append(dokazOUplatiTakse);

        return buffer.toString();
    }
}
