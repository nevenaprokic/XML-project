
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Primalac_zahteva" type="{http://www.ftn.uns.ac.rs/p1}TPrimalac_zahteva"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Pronalazak"/>
 *         &lt;element name="Podnosilac_prijave" type="{http://www.ftn.uns.ac.rs/p1}TPodnosilacPrijave"/>
 *         &lt;element name="Pronalazac" type="{http://www.ftn.uns.ac.rs/p1}TPronalazac"/>
 *         &lt;element name="Punomocnik" type="{http://www.ftn.uns.ac.rs/p1}TPunomocnik"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Podaci_o_dostavljanju"/>
 *         &lt;element name="Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava" type="{http://www.ftn.uns.ac.rs/p1}TZahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava"/>
 *       &lt;/sequence>
 *       &lt;attribute name="broj_prijave" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum_prijema_prijave" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="priznati_datum_podnosenja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="tip_prijave" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="dopunska"/>
 *             &lt;enumeration value="izdvojena"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primalacZahteva",
    "pronalazak",
    "podnosilacPrijave",
    "pronalazac",
    "punomocnik",
    "podaciODostavljanju",
    "zahtevZaPriznanjePrvenstvaIzRanijihPrijava"
})
@XmlRootElement(name = "Zahtev_za_priznanje_patenta", namespace = "http://www.ftn.uns.ac.rs/p1")
public class ZahtevZaPriznanjePatenta {

    @XmlElement(name = "Primalac_zahteva", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPrimalacZahteva primalacZahteva;
    @XmlElement(name = "Pronalazak", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected Pronalazak pronalazak;
    @XmlElement(name = "Podnosilac_prijave", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPodnosilacPrijave podnosilacPrijave;
    @XmlElement(name = "Pronalazac", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPronalazac pronalazac;
    @XmlElement(name = "Punomocnik", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPunomocnik punomocnik;
    @XmlElement(name = "Podaci_o_dostavljanju", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected PodaciODostavljanju podaciODostavljanju;
    @XmlElement(name = "Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
    @XmlAttribute(name = "broj_prijave", required = true)
    protected String brojPrijave;
    @XmlAttribute(name = "datum_prijema_prijave", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPrijemaPrijave;
    @XmlAttribute(name = "priznati_datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar priznatiDatumPodnosenja;
    @XmlAttribute(name = "tip_prijave", required = true)
    protected String tipPrijave;

    /**
     * Gets the value of the primalacZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TPrimalacZahteva }
     *     
     */
    public TPrimalacZahteva getPrimalacZahteva() {
        return primalacZahteva;
    }

    /**
     * Sets the value of the primalacZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrimalacZahteva }
     *     
     */
    public void setPrimalacZahteva(TPrimalacZahteva value) {
        this.primalacZahteva = value;
    }

    /**
     * Gets the value of the pronalazak property.
     * 
     * @return
     *     possible object is
     *     {@link Pronalazak }
     *     
     */
    public Pronalazak getPronalazak() {
        return pronalazak;
    }

    /**
     * Sets the value of the pronalazak property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pronalazak }
     *     
     */
    public void setPronalazak(Pronalazak value) {
        this.pronalazak = value;
    }

    /**
     * Gets the value of the podnosilacPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link TPodnosilacPrijave }
     *     
     */
    public TPodnosilacPrijave getPodnosilacPrijave() {
        return podnosilacPrijave;
    }

    /**
     * Sets the value of the podnosilacPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodnosilacPrijave }
     *     
     */
    public void setPodnosilacPrijave(TPodnosilacPrijave value) {
        this.podnosilacPrijave = value;
    }

    /**
     * Gets the value of the pronalazac property.
     * 
     * @return
     *     possible object is
     *     {@link TPronalazac }
     *     
     */
    public TPronalazac getPronalazac() {
        return pronalazac;
    }

    /**
     * Sets the value of the pronalazac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPronalazac }
     *     
     */
    public void setPronalazac(TPronalazac value) {
        this.pronalazac = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link TPunomocnik }
     *     
     */
    public TPunomocnik getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPunomocnik }
     *     
     */
    public void setPunomocnik(TPunomocnik value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the podaciODostavljanju property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciODostavljanju }
     *     
     */
    public PodaciODostavljanju getPodaciODostavljanju() {
        return podaciODostavljanju;
    }

    /**
     * Sets the value of the podaciODostavljanju property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciODostavljanju }
     *     
     */
    public void setPodaciODostavljanju(PodaciODostavljanju value) {
        this.podaciODostavljanju = value;
    }

    /**
     * Gets the value of the zahtevZaPriznanjePrvenstvaIzRanijihPrijava property.
     * 
     * @return
     *     possible object is
     *     {@link TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava }
     *     
     */
    public TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava getZahtevZaPriznanjePrvenstvaIzRanijihPrijava() {
        return zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
    }

    /**
     * Sets the value of the zahtevZaPriznanjePrvenstvaIzRanijihPrijava property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava }
     *     
     */
    public void setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava value) {
        this.zahtevZaPriznanjePrvenstvaIzRanijihPrijava = value;
    }

    /**
     * Gets the value of the brojPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPrijave(String value) {
        this.brojPrijave = value;
    }

    /**
     * Gets the value of the datumPrijemaPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPrijemaPrijave() {
        return datumPrijemaPrijave;
    }

    /**
     * Sets the value of the datumPrijemaPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPrijemaPrijave(XMLGregorianCalendar value) {
        this.datumPrijemaPrijave = value;
    }

    /**
     * Gets the value of the priznatiDatumPodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPriznatiDatumPodnosenja() {
        return priznatiDatumPodnosenja;
    }

    /**
     * Sets the value of the priznatiDatumPodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPriznatiDatumPodnosenja(XMLGregorianCalendar value) {
        this.priznatiDatumPodnosenja = value;
    }

    /**
     * Gets the value of the tipPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPrijave() {
        return tipPrijave;
    }

    /**
     * Sets the value of the tipPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPrijave(String value) {
        this.tipPrijave = value;
    }

    @Override
    public String toString() {
        return "Zahtev za priznanje patenta" + '\n' + '\t' +
                "informacje: " + "\n\t\t" + " broj prjave: " + brojPrijave +   "\n\t\t" +
                " datum prijema: " +datumPrijemaPrijave +  "\n\t\t" +
                " priznati datum podnosenja prijave: " +priznatiDatumPodnosenja +  "\n\t\t" +
                " tip prjave: " + tipPrijave + "\n\t\n\t" +
                primalacZahteva +  "\n\t" +
                pronalazak +  "\n\t" +
                podnosilacPrijave +  "\n\t" +
                pronalazac +  "\n\t" +
                punomocnik +  "\n\t" +
                podaciODostavljanju +  "\n\t" +
                zahtevZaPriznanjePrvenstvaIzRanijihPrijava + '\n';

    }
}
