
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
 *         &lt;element name="primerak_znaka" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *         &lt;element name="spisak_robe_i_usluga" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *         &lt;choice>
 *           &lt;element name="punomocje" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *           &lt;element name="punomocje_ranije_prilozeno" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *           &lt;element name="punomocje_naknadno_dostavljeno" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *         &lt;/choice>
 *         &lt;element name="opsti_akt_o_kolektivnom_zigu_garancije" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *         &lt;element name="dokaz_o_pravu_prvenstva" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *         &lt;element name="dokaz_o_uplati_takse" type="{http://ftn.uns.ac.rs/zig}TDopuna"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrilozi", namespace = "http://ftn.uns.ac.rs/zig", propOrder = {
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

    @XmlElement(name = "primerak_znaka", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TDopuna primerakZnaka;
    @XmlElement(name = "spisak_robe_i_usluga", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TDopuna spisakRobeIUsluga;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/zig")
    protected TDopuna punomocje;
    @XmlElement(name = "punomocje_ranije_prilozeno", namespace = "http://ftn.uns.ac.rs/zig")
    protected TDopuna punomocjeRanijePrilozeno;
    @XmlElement(name = "punomocje_naknadno_dostavljeno", namespace = "http://ftn.uns.ac.rs/zig")
    protected TDopuna punomocjeNaknadnoDostavljeno;
    @XmlElement(name = "opsti_akt_o_kolektivnom_zigu_garancije", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TDopuna opstiAktOKolektivnomZiguGarancije;
    @XmlElement(name = "dokaz_o_pravu_prvenstva", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TDopuna dokazOPravuPrvenstva;
    @XmlElement(name = "dokaz_o_uplati_takse", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TDopuna dokazOUplatiTakse;

    /**
     * Gets the value of the primerakZnaka property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getPrimerakZnaka() {
        return primerakZnaka;
    }

    /**
     * Sets the value of the primerakZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setPrimerakZnaka(TDopuna value) {
        this.primerakZnaka = value;
    }

    /**
     * Gets the value of the spisakRobeIUsluga property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getSpisakRobeIUsluga() {
        return spisakRobeIUsluga;
    }

    /**
     * Sets the value of the spisakRobeIUsluga property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setSpisakRobeIUsluga(TDopuna value) {
        this.spisakRobeIUsluga = value;
    }

    /**
     * Gets the value of the punomocje property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getPunomocje() {
        return punomocje;
    }

    /**
     * Sets the value of the punomocje property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setPunomocje(TDopuna value) {
        this.punomocje = value;
    }

    /**
     * Gets the value of the punomocjeRanijePrilozeno property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getPunomocjeRanijePrilozeno() {
        return punomocjeRanijePrilozeno;
    }

    /**
     * Sets the value of the punomocjeRanijePrilozeno property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setPunomocjeRanijePrilozeno(TDopuna value) {
        this.punomocjeRanijePrilozeno = value;
    }

    /**
     * Gets the value of the punomocjeNaknadnoDostavljeno property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getPunomocjeNaknadnoDostavljeno() {
        return punomocjeNaknadnoDostavljeno;
    }

    /**
     * Sets the value of the punomocjeNaknadnoDostavljeno property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setPunomocjeNaknadnoDostavljeno(TDopuna value) {
        this.punomocjeNaknadnoDostavljeno = value;
    }

    /**
     * Gets the value of the opstiAktOKolektivnomZiguGarancije property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getOpstiAktOKolektivnomZiguGarancije() {
        return opstiAktOKolektivnomZiguGarancije;
    }

    /**
     * Sets the value of the opstiAktOKolektivnomZiguGarancije property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setOpstiAktOKolektivnomZiguGarancije(TDopuna value) {
        this.opstiAktOKolektivnomZiguGarancije = value;
    }

    /**
     * Gets the value of the dokazOPravuPrvenstva property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getDokazOPravuPrvenstva() {
        return dokazOPravuPrvenstva;
    }

    /**
     * Sets the value of the dokazOPravuPrvenstva property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setDokazOPravuPrvenstva(TDopuna value) {
        this.dokazOPravuPrvenstva = value;
    }

    /**
     * Gets the value of the dokazOUplatiTakse property.
     *
     * @return
     *     possible object is
     *     {@link TDopuna }
     *
     */
    public TDopuna getDokazOUplatiTakse() {
        return dokazOUplatiTakse;
    }

    /**
     * Sets the value of the dokazOUplatiTakse property.
     *
     * @param value
     *     allowed object is
     *     {@link TDopuna }
     *
     */
    public void setDokazOUplatiTakse(TDopuna value) {
        this.dokazOUplatiTakse = value;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Primerak znaka: ");
        buffer.append(primerakZnaka.toString());

        buffer.append("\n\t\t - Spisak robe i usluga: ");
        buffer.append(spisakRobeIUsluga.toString());

        if(punomocje != null){
            buffer.append("\n\t\t - Punomocje: ");
            buffer.append(punomocje.toString());
        }

        if(punomocjeRanijePrilozeno != null){
            buffer.append("\n\t\t - Punomocje koje je ranije prilozeno: ");
            buffer.append(punomocjeRanijePrilozeno.toString());
        }

        if(punomocjeNaknadnoDostavljeno != null){
            buffer.append("\n\t\t - Punomocje naknadno dostavljeno: ");
            buffer.append(punomocjeNaknadnoDostavljeno.toString());
        }

        buffer.append("\n\t\t - Opsti akt o kolektivnom zigu generacije: ");
        buffer.append(opstiAktOKolektivnomZiguGarancije.toString());

        buffer.append("\n\t\t - Dokaz o pravu privenstva: ");
        buffer.append(dokazOPravuPrvenstva.toString());

        buffer.append("\n\t\t - Dokaz o uplati takse: ");
        buffer.append(dokazOUplatiTakse.toString());

        return buffer.toString();
    }
}
