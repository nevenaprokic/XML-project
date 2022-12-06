
package rs.ac.uns.ftn.jaxb.z1;

import rs.ac.uns.ftn.jaxb.example4.Person;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZig complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TZig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vrsta_ziga_na_osnovu_korisnika" type="{http://ftn.uns.ac.rs/z1}vrsta_ziga_na_osnovu_korisnika"/>
 *         &lt;element name="vrsta_ziga_na_osnovu_izgleda" type="{http://ftn.uns.ac.rs/z1}vrsta_ziga_na_osnovu_izgleda"/>
 *         &lt;element name="izgled_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podaci_o_boji_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prevod_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transliteracija_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opis_znaka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podaci_o_brojevima_klasa_robe_i_usluga">
 *           &lt;simpleType>
 *             &lt;list>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                   &lt;maxInclusive value="45"/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *             &lt;/list>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZig", namespace = "http://ftn.uns.ac.rs/z1", propOrder = {
    "vrstaZigaNaOsnovuKorisnika",
    "vrstaZigaNaOsnovuIzgleda",
    "izgledZnaka",
    "podaciOBojiZnaka",
    "prevodZnaka",
    "transliteracijaZnaka",
    "opisZnaka",
    "podaciOBrojevimaKlasaRobeIUsluga"
})
public class TZig {

    @XmlElement(name = "vrsta_ziga_na_osnovu_korisnika", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    @XmlSchemaType(name = "string")
    protected VrstaZigaNaOsnovuKorisnika vrstaZigaNaOsnovuKorisnika;
    @XmlElement(name = "vrsta_ziga_na_osnovu_izgleda", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    @XmlSchemaType(name = "string")
    protected VrstaZigaNaOsnovuIzgleda vrstaZigaNaOsnovuIzgleda;
    @XmlElement(name = "izgled_znaka", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String izgledZnaka;
    @XmlElement(name = "podaci_o_boji_znaka", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String podaciOBojiZnaka;
    @XmlElement(name = "prevod_znaka", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String prevodZnaka;
    @XmlElement(name = "transliteracija_znaka", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String transliteracijaZnaka;
    @XmlElement(name = "opis_znaka", namespace = "http://ftn.uns.ac.rs/z1", required = true)
    protected String opisZnaka;
    @XmlList
    @XmlElement(name = "podaci_o_brojevima_klasa_robe_i_usluga", namespace = "http://ftn.uns.ac.rs/z1", type = Integer.class)
    protected List<Integer> podaciOBrojevimaKlasaRobeIUsluga;

    /**
     * Gets the value of the vrstaZigaNaOsnovuKorisnika property.
     *
     * @return
     *     possible object is
     *     {@link VrstaZigaNaOsnovuKorisnika }
     *
     */
    public VrstaZigaNaOsnovuKorisnika getVrstaZigaNaOsnovuKorisnika() {
        return vrstaZigaNaOsnovuKorisnika;
    }

    /**
     * Sets the value of the vrstaZigaNaOsnovuKorisnika property.
     *
     * @param value
     *     allowed object is
     *     {@link VrstaZigaNaOsnovuKorisnika }
     *
     */
    public void setVrstaZigaNaOsnovuKorisnika(VrstaZigaNaOsnovuKorisnika value) {
        this.vrstaZigaNaOsnovuKorisnika = value;
    }

    /**
     * Gets the value of the vrstaZigaNaOsnovuIzgleda property.
     *
     * @return
     *     possible object is
     *     {@link VrstaZigaNaOsnovuIzgleda }
     *
     */
    public VrstaZigaNaOsnovuIzgleda getVrstaZigaNaOsnovuIzgleda() {
        return vrstaZigaNaOsnovuIzgleda;
    }

    /**
     * Sets the value of the vrstaZigaNaOsnovuIzgleda property.
     *
     * @param value
     *     allowed object is
     *     {@link VrstaZigaNaOsnovuIzgleda }
     *
     */
    public void setVrstaZigaNaOsnovuIzgleda(VrstaZigaNaOsnovuIzgleda value) {
        this.vrstaZigaNaOsnovuIzgleda = value;
    }

    /**
     * Gets the value of the izgledZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIzgledZnaka() {
        return izgledZnaka;
    }

    /**
     * Sets the value of the izgledZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIzgledZnaka(String value) {
        this.izgledZnaka = value;
    }

    /**
     * Gets the value of the podaciOBojiZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPodaciOBojiZnaka() {
        return podaciOBojiZnaka;
    }

    /**
     * Sets the value of the podaciOBojiZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPodaciOBojiZnaka(String value) {
        this.podaciOBojiZnaka = value;
    }

    /**
     * Gets the value of the prevodZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrevodZnaka() {
        return prevodZnaka;
    }

    /**
     * Sets the value of the prevodZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrevodZnaka(String value) {
        this.prevodZnaka = value;
    }

    /**
     * Gets the value of the transliteracijaZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTransliteracijaZnaka() {
        return transliteracijaZnaka;
    }

    /**
     * Sets the value of the transliteracijaZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTransliteracijaZnaka(String value) {
        this.transliteracijaZnaka = value;
    }

    /**
     * Gets the value of the opisZnaka property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOpisZnaka() {
        return opisZnaka;
    }

    /**
     * Sets the value of the opisZnaka property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOpisZnaka(String value) {
        this.opisZnaka = value;
    }

    /**
     * Gets the value of the podaciOBrojevimaKlasaRobeIUsluga property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the podaciOBrojevimaKlasaRobeIUsluga property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPodaciOBrojevimaKlasaRobeIUsluga().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     *
     *
     */
    public List<Integer> getPodaciOBrojevimaKlasaRobeIUsluga() {
        if (podaciOBrojevimaKlasaRobeIUsluga == null) {
            podaciOBrojevimaKlasaRobeIUsluga = new ArrayList<Integer>();
        }
        return this.podaciOBrojevimaKlasaRobeIUsluga;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n\t\t - Vrsta ziga na osnovu korisnika: ");
        buffer.append(vrstaZigaNaOsnovuKorisnika);

        buffer.append("\n\t\t - Vrsta ziga na osnovu izgleda: ");
        buffer.append(vrstaZigaNaOsnovuIzgleda);

        buffer.append("\n\t\t - Izgled znaka: ");
        buffer.append(izgledZnaka);

        buffer.append("\n\t\t - Podaci o boji znaka: ");
        buffer.append(podaciOBojiZnaka);

        buffer.append("\n\t\t - Prevod znaka: ");
        buffer.append(prevodZnaka);

        buffer.append("\n\t\t - Transliteracija znaka: ");
        buffer.append(transliteracijaZnaka);

        buffer.append("\n\t\t - Opis znaka: ");
        buffer.append(opisZnaka);

        buffer.append("\n\t\t - Podaci o brojevima klasa robe i usluga: ");
        if(podaciOBrojevimaKlasaRobeIUsluga.size() > 0)
            for(int number : podaciOBrojevimaKlasaRobeIUsluga) {
                buffer.append(number);
                buffer.append(", ");
            }


        return buffer.toString();
    }
}
