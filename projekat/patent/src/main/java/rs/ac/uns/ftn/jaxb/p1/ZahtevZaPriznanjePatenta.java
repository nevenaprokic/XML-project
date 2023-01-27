
package rs.ac.uns.ftn.jaxb.p1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;




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
 *         &lt;element name="Pronalazac" type="{http://www.ftn.uns.ac.rs/p1}TPronalazac"/>
 *         &lt;element name="Podnosilac_zahteva" type="{http://www.ftn.uns.ac.rs/p1}TPodnosilac_zahteva"/>
 *         &lt;element name="Punomocnik" type="{http://www.ftn.uns.ac.rs/p1}TPunomocnik"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/p1}Podaci_o_dostavljanju"/>
 *         &lt;element name="Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava" type="{http://www.ftn.uns.ac.rs/p1}TZahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava"/>
 *       &lt;/sequence>
 *       &lt;attribute name="broj_prijave" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[P][0-9]+"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
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
	"idPatenta",
    "primalacZahteva",
    "pronalazak",
    "pronalazac",
    "podnosilacZahteva",
    "punomocnik",
    "podaciODostavljanju",    
    "zahtevZaPriznanjePrvenstvaIzRanijihPrijava",
    "podaciODodatnojPrijavi"
})
@XmlRootElement(name = "Zahtev_za_priznanje_patenta", namespace = "http://www.ftn.uns.ac.rs/p1")
	public class ZahtevZaPriznanjePatenta {
	
	@XmlElement(namespace = "http://www.ftn.uns.ac.rs/p1")
    protected IdPatenta idPatenta;
	@XmlElement(name = "Primalac_zahteva", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPrimalacZahteva primalacZahteva;
    @XmlElement(name = "Pronalazak", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected Pronalazak pronalazak;
    @XmlElement(name = "Pronalazac", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPronalazac pronalazac;
    @XmlElement(name = "Podnosilac_zahteva", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TPodnosilacZahteva podnosilacZahteva;
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
    @XmlElement(name = "Podaci_o_dodatnoj_prijavi", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected PodaciODodatnojPrijavi podaciODodatnojPrijavi;
    @XmlAttribute(name = "status")
	protected StatusZahteva status;
    
    public IdPatenta getIdPatenta() {
		return idPatenta;
	}

	public void setIdPatenta(IdPatenta idPatenta) {
		this.idPatenta = idPatenta;
	}
    
    public StatusZahteva getStatus() {
		return status;
	}

	public void setStatus(StatusZahteva status) {
		this.status = status;
	}

	@XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the podnosilacZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TPodnosilacZahteva }
     *     
     */
    public TPodnosilacZahteva getPodnosilacZahteva() {
        return podnosilacZahteva;
    }

    /**
     * Sets the value of the podnosilacZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodnosilacZahteva }
     *     
     */
    public void setPodnosilacZahteva(TPodnosilacZahteva value) {
        this.podnosilacZahteva = value;
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
    public PodaciODodatnojPrijavi getODodatnojPrijavi() {
        return podaciODodatnojPrijavi;
    }

    /**
     * Sets the value of the tipPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodaciODodatnojPrijavi(PodaciODodatnojPrijavi value) {
        this.podaciODodatnojPrijavi = value;
    }

    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	@Override
    public String toString() {
        return "Zahtev za priznanje patenta" + '\n' + '\t' +
                "informacje: " + "\n\t\t" + " broj prjave: " + brojPrijave +   "\n\t\t" +
                " datum prijema: " +datumPrijemaPrijave +  "\n\t\t" +
                " priznati datum podnosenja prijave: " +priznatiDatumPodnosenja +  "\n\t\t" +
                primalacZahteva +  "\n\t" +
                pronalazak +  "\n\t" +
                podnosilacZahteva +  "\n\t" +
                pronalazac +  "\n\t" +
                punomocnik +  "\n\t" +
                podaciODostavljanju +  "\n\t" +
                zahtevZaPriznanjePrvenstvaIzRanijihPrijava + '\n';

    }
}
