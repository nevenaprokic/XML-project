
package rs.ac.uns.ftn.jaxb.z1;

import rs.ac.uns.ftn.jaxb.example4.Person;

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
 *         &lt;element name="podaci_o_podnosiocu_prijave" type="{http://ftn.uns.ac.rs/z1}TLice"/>
 *         &lt;element name="podaci_o_pravu_prvenstva_i_osnov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podaci_o_placenim_taksama" type="{http://ftn.uns.ac.rs/z1}TTakse"/>
 *         &lt;element name="podaci_o_prilozima_uz_zahtev" type="{http://ftn.uns.ac.rs/z1}TPrilozi"/>
 *         &lt;element name="podaci_o_zigu" type="{http://ftn.uns.ac.rs/z1}TZig"/>
 *         &lt;element name="podaci_o_punomocniku" type="{http://ftn.uns.ac.rs/z1}TLice"/>
 *         &lt;element name="podaci_o_zajednickom_predstavniku" type="{http://ftn.uns.ac.rs/z1}TLice"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podaciOPodnosiocuPrijave",
    "podaciOPravuPrvenstvaIOsnov",
    "podaciOPlacenimTaksama",
    "podaciOPrilozimaUzZahtev",
    "podaciOZigu",
    "podaciOPunomocniku",
    "podaciOZajednickomPredstavniku"
})
@XmlRootElement(name = "Zahtev_za_priznanje_ziga", namespace = "http://ftn.uns.ac.rs/z1")
public class ZahtevZaPriznanjeZiga {

    @XmlElement(name = "podaci_o_podnosiocu_prijave", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TLice podaciOPodnosiocuPrijave;
    @XmlElement(name = "podaci_o_pravu_prvenstva_i_osnov", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String podaciOPravuPrvenstvaIOsnov;
    @XmlElement(name = "podaci_o_placenim_taksama", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TTakse podaciOPlacenimTaksama;
    @XmlElement(name = "podaci_o_prilozima_uz_zahtev", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TPrilozi podaciOPrilozimaUzZahtev;
    @XmlElement(name = "podaci_o_zigu", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TZig podaciOZigu;
    @XmlElement(name = "podaci_o_punomocniku", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TLice podaciOPunomocniku;
    @XmlElement(name = "podaci_o_zajednickom_predstavniku", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected TLice podaciOZajednickomPredstavniku;

    /**
     * Gets the value of the podaciOPodnosiocuPrijave property.
     *
     * @return
     *     possible object is
     *     {@link TLice }
     *
     */
    public TLice getPodaciOPodnosiocuPrijave() {
        return podaciOPodnosiocuPrijave;
    }

    /**
     * Sets the value of the podaciOPodnosiocuPrijave property.
     *
     * @param value
     *     allowed object is
     *     {@link TLice }
     *
     */
    public void setPodaciOPodnosiocuPrijave(TLice value) {
        this.podaciOPodnosiocuPrijave = value;
    }

    /**
     * Gets the value of the podaciOPravuPrvenstvaIOsnov property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPodaciOPravuPrvenstvaIOsnov() {
        return podaciOPravuPrvenstvaIOsnov;
    }

    /**
     * Sets the value of the podaciOPravuPrvenstvaIOsnov property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPodaciOPravuPrvenstvaIOsnov(String value) {
        this.podaciOPravuPrvenstvaIOsnov = value;
    }

    /**
     * Gets the value of the podaciOPlacenimTaksama property.
     *
     * @return
     *     possible object is
     *     {@link TTakse }
     *
     */
    public TTakse getPodaciOPlacenimTaksama() {
        return podaciOPlacenimTaksama;
    }

    /**
     * Sets the value of the podaciOPlacenimTaksama property.
     *
     * @param value
     *     allowed object is
     *     {@link TTakse }
     *
     */
    public void setPodaciOPlacenimTaksama(TTakse value) {
        this.podaciOPlacenimTaksama = value;
    }

    /**
     * Gets the value of the podaciOPrilozimaUzZahtev property.
     *
     * @return
     *     possible object is
     *     {@link TPrilozi }
     *
     */
    public TPrilozi getPodaciOPrilozimaUzZahtev() {
        return podaciOPrilozimaUzZahtev;
    }

    /**
     * Sets the value of the podaciOPrilozimaUzZahtev property.
     *
     * @param value
     *     allowed object is
     *     {@link TPrilozi }
     *
     */
    public void setPodaciOPrilozimaUzZahtev(TPrilozi value) {
        this.podaciOPrilozimaUzZahtev = value;
    }

    /**
     * Gets the value of the podaciOZigu property.
     *
     * @return
     *     possible object is
     *     {@link TZig }
     *
     */
    public TZig getPodaciOZigu() {
        return podaciOZigu;
    }

    /**
     * Sets the value of the podaciOZigu property.
     *
     * @param value
     *     allowed object is
     *     {@link TZig }
     *
     */
    public void setPodaciOZigu(TZig value) {
        this.podaciOZigu = value;
    }

    /**
     * Gets the value of the podaciOPunomocniku property.
     *
     * @return
     *     possible object is
     *     {@link TLice }
     *
     */
    public TLice getPodaciOPunomocniku() {
        return podaciOPunomocniku;
    }

    /**
     * Sets the value of the podaciOPunomocniku property.
     *
     * @param value
     *     allowed object is
     *     {@link TLice }
     *
     */
    public void setPodaciOPunomocniku(TLice value) {
        this.podaciOPunomocniku = value;
    }

    /**
     * Gets the value of the podaciOZajednickomPredstavniku property.
     *
     * @return
     *     possible object is
     *     {@link TLice }
     *
     */
    public TLice getPodaciOZajednickomPredstavniku() {
        return podaciOZajednickomPredstavniku;
    }

    /**
     * Sets the value of the podaciOZajednickomPredstavniku property.
     *
     * @param value
     *     allowed object is
     *     {@link TLice }
     *
     */
    public void setPodaciOZajednickomPredstavniku(TLice value) {
        this.podaciOZajednickomPredstavniku = value;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("- Zig: ");

        buffer.append("\n");
        buffer.append("\t - Podnosilac zahteva: ");
        buffer.append(podaciOPodnosiocuPrijave);

        buffer.append("\n");
        buffer.append("\t - Punomocnik zahteva: ");
        buffer.append(podaciOPunomocniku);

        buffer.append("\n");
        buffer.append("\t - Zajednicki predstanik zahteva: ");
        buffer.append(podaciOZajednickomPredstavniku);

        buffer.append("\n");
        buffer.append("\t - Podaci o zigu: ");
        buffer.append(podaciOZigu);

        buffer.append("\n");
        buffer.append("\t - Podaci o pravu prvenstva i osnov: ");
        buffer.append(podaciOPravuPrvenstvaIOsnov);

        buffer.append("\n");
        buffer.append("\t - Podaci o taksama: ");
        buffer.append(podaciOPlacenimTaksama);

        buffer.append("\n");
        buffer.append("\t - Prilozeni podaci uz zahtev: ");
        buffer.append(podaciOPrilozimaUzZahtev);

        return buffer.toString();
    }
}
