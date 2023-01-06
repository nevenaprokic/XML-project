
package rs.ac.uns.ftn.jaxb.z1;

import rs.ac.uns.ftn.jaxb.zajednicko.TLice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 *         &lt;element name="Podnosioc_prijave" type="{http://www.ftn.uns.ac.rs/zaj}TLice" maxOccurs="unbounded"/>
 *         &lt;element name="Punomocnik" type="{http://www.ftn.uns.ac.rs/zaj}TLice" minOccurs="0"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zig}Zajednicki_predstavnik" minOccurs="0"/>
 *         &lt;element name="Zig" type="{http://ftn.uns.ac.rs/zig}TZig"/>
 *         &lt;element name="Pravo_prvenstva_i_osnov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Placene_takse" type="{http://ftn.uns.ac.rs/zig}TTakse"/>
 *         &lt;element name="Prilozi_uz_zahtev" type="{http://ftn.uns.ac.rs/zig}TPrilozi"/>
 *       &lt;/sequence>
 *       &lt;attribute name="broj_prijave_ziga" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="\Ž\-[0-9]+\/[0-9]{2,2}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="datum_podnosenja_prijave" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "podnosiocPrijave",
        "punomocnik",
        "zajednickiPredstavnik",
        "zig",
        "pravoPrvenstvaIOsnov",
        "placeneTakse",
        "priloziUzZahtev"
})
@XmlRootElement(name = "Zahtev_za_priznanje_ziga", namespace = "http://ftn.uns.ac.rs/zig")
public class ZahtevZaPriznanjeZiga {

    @XmlElement(name = "Podnosioc_prijave", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected List<TLice> podnosiocPrijave;
    @XmlElement(name = "Punomocnik", namespace = "http://ftn.uns.ac.rs/zig")
    protected TLice punomocnik;
    @XmlElement(name = "Zajednicki_predstavnik", namespace = "http://ftn.uns.ac.rs/zig")
    protected ZajednickiPredstavnik zajednickiPredstavnik;
    @XmlElement(name = "Zig", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TZig zig;
    @XmlElement(name = "Pravo_prvenstva_i_osnov", namespace = "http://ftn.uns.ac.rs/zig")
    protected String pravoPrvenstvaIOsnov;
    @XmlElement(name = "Placene_takse", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TTakse placeneTakse;
    @XmlElement(name = "Prilozi_uz_zahtev", namespace = "http://ftn.uns.ac.rs/zig", required = true)
    protected TPrilozi priloziUzZahtev;
    @XmlAttribute(name = "broj_prijave_ziga", required = true)
    protected String brojPrijaveZiga;
    @XmlAttribute(name = "datum_podnosenja_prijave", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaPrijave;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the podnosiocPrijave property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the podnosiocPrijave property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPodnosiocPrijave().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TLice }
     */
    public List<TLice> getPodnosiocPrijave() {
        if (podnosiocPrijave == null) {
            podnosiocPrijave = new ArrayList<TLice>();
        }
        return this.podnosiocPrijave;
    }

    /**
     * Gets the value of the punomocnik property.
     *
     * @return possible object is
     * {@link TLice }
     */
    public TLice getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     *
     * @param value allowed object is
     *              {@link TLice }
     */
    public void setPunomocnik(TLice value) {
        this.punomocnik = value;
    }

    /**
     * Zajednicki predstavnik prijave priznanja ziga - podaci
     *
     * @return possible object is
     * {@link ZajednickiPredstavnik }
     */
    public ZajednickiPredstavnik getZajednickiPredstavnik() {
        return zajednickiPredstavnik;
    }

    /**
     * Sets the value of the zajednickiPredstavnik property.
     *
     * @param value allowed object is
     *              {@link ZajednickiPredstavnik }
     */
    public void setZajednickiPredstavnik(ZajednickiPredstavnik value) {
        this.zajednickiPredstavnik = value;
    }

    /**
     * Gets the value of the zig property.
     *
     * @return possible object is
     * {@link TZig }
     */
    public TZig getZig() {
        return zig;
    }

    /**
     * Sets the value of the zig property.
     *
     * @param value allowed object is
     *              {@link TZig }
     */
    public void setZig(TZig value) {
        this.zig = value;
    }

    /**
     * Gets the value of the pravoPrvenstvaIOsnov property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPravoPrvenstvaIOsnov() {
        return pravoPrvenstvaIOsnov;
    }

    /**
     * Sets the value of the pravoPrvenstvaIOsnov property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPravoPrvenstvaIOsnov(String value) {
        this.pravoPrvenstvaIOsnov = value;
    }

    /**
     * Gets the value of the placeneTakse property.
     *
     * @return possible object is
     * {@link TTakse }
     */
    public TTakse getPlaceneTakse() {
        return placeneTakse;
    }

    /**
     * Sets the value of the placeneTakse property.
     *
     * @param value allowed object is
     *              {@link TTakse }
     */
    public void setPlaceneTakse(TTakse value) {
        this.placeneTakse = value;
    }

    /**
     * Gets the value of the priloziUzZahtev property.
     *
     * @return possible object is
     * {@link TPrilozi }
     */
    public TPrilozi getPriloziUzZahtev() {
        return priloziUzZahtev;
    }

    /**
     * Sets the value of the priloziUzZahtev property.
     *
     * @param value allowed object is
     *              {@link TPrilozi }
     */
    public void setPriloziUzZahtev(TPrilozi value) {
        this.priloziUzZahtev = value;
    }

    /**
     * Gets the value of the brojPrijaveZiga property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBrojPrijaveZiga() {
        return brojPrijaveZiga;
    }

    /**
     * Sets the value of the brojPrijaveZiga property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBrojPrijaveZiga(String value) {
        this.brojPrijaveZiga = value;
    }

    /**
     * Gets the value of the datumPodnosenjaPrijave property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    /**
     * Sets the value of the datumPodnosenjaPrijave property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDatumPodnosenjaPrijave(XMLGregorianCalendar value) {
        this.datumPodnosenjaPrijave = value;
    }

    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("- Zig: ");

        buffer.append("\n");
        buffer.append("------------------------------------------------------------ \n");
        buffer.append("\t - Broj prijave zahteva: ");
        buffer.append(brojPrijaveZiga);
        buffer.append("\n");
        buffer.append("\t - Datum prijave zahteva: ");
        buffer.append(datumPodnosenjaPrijave);
        buffer.append("\n");
        buffer.append("------------------------------------------------------------ ");

        buffer.append("\n");
        buffer.append("\t - Podnosilac zahteva: ");
        buffer.append(podnosiocPrijave);

        buffer.append("\n");
        buffer.append("\t - Punomocnik zahteva: ");
        buffer.append(punomocnik);

        buffer.append("\n");
        buffer.append("\t - Zajednicki predstanik zahteva: ");
        buffer.append(zajednickiPredstavnik);

        buffer.append("\n");
        buffer.append("\t - Podaci o zigu: ");
        buffer.append(zig);

        buffer.append("\n");
        buffer.append("\t - Podaci o pravu prvenstva i osnov: ");
        buffer.append(pravoPrvenstvaIOsnov);

        buffer.append("\n");
        buffer.append("\t - Podaci o taksama: ");
        buffer.append(placeneTakse);

        buffer.append("\n");
        buffer.append("\t - Prilozeni podaci uz zahtev: ");
        buffer.append(priloziUzZahtev);

        return buffer.toString();
    }
}
