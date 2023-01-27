
package rs.ac.uns.ftn.jaxb.resenje;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.p1.IdPatenta;

import java.util.HashMap;
import java.util.Map;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zig}idZiga"/>
 *         &lt;element name="Dodatak" type="{http://ftn.uns.ac.rs/resenje}TResenje"/>
 *         &lt;element name="Sluzbenik" type="{http://ftn.uns.ac.rs/resenje}TSluzbenik"/>
 *       &lt;/sequence>
 *       &lt;attribute name="status" use="required" type="{http://ftn.uns.ac.rs/resenje}status_resenja" />
 *       &lt;attribute name="datum_resenja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;anyAttribute processContents='lax'/>
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
    "dodatak",
    "sluzbenik"
})
@XmlRootElement(name = "Resenje", namespace = "http://ftn.uns.ac.rs/resenje")
public class Resenje {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected IdPatenta idPatenta;
    @XmlElement(name = "Dodatak", namespace = "http://ftn.uns.ac.rs/resenje", required = true)
    protected TResenje dodatak;
    @XmlElement(name = "Sluzbenik", namespace = "http://ftn.uns.ac.rs/resenje", required = true)
    protected TSluzbenik sluzbenik;
    @XmlAttribute(name = "status", required = true)
    protected StatusResenja status;
    @XmlAttribute(name = "datum_resenja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumResenja;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the idPatenta property.
     * 
     * @return
     *     possible object is
     *     {@link IdPatenta }
     *     
     */
    public IdPatenta getIdPatenta() {
        return idPatenta;
    }

    /**
     * Sets the value of the idPatenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdPatenta }
     *     
     */
    public void setIdPatenta(IdPatenta value) {
        this.idPatenta = value;
    }

    /**
     * Gets the value of the dodatak property.
     * 
     * @return
     *     possible object is
     *     {@link TResenje }
     *     
     */
    public TResenje getDodatak() {
        return dodatak;
    }

    /**
     * Sets the value of the dodatak property.
     * 
     * @param value
     *     allowed object is
     *     {@link TResenje }
     *     
     */
    public void setDodatak(TResenje value) {
        this.dodatak = value;
    }

    /**
     * Gets the value of the sluzbenik property.
     * 
     * @return
     *     possible object is
     *     {@link TSluzbenik }
     *     
     */
    public TSluzbenik getSluzbenik() {
        return sluzbenik;
    }

    /**
     * Sets the value of the sluzbenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSluzbenik }
     *     
     */
    public void setSluzbenik(TSluzbenik value) {
        this.sluzbenik = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusResenja }
     *     
     */
    public StatusResenja getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusResenja }
     *     
     */
    public void setStatus(StatusResenja value) {
        this.status = value;
    }

    /**
     * Gets the value of the datumResenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumResenja() {
        return datumResenja;
    }

    /**
     * Sets the value of the datumResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumResenja(XMLGregorianCalendar value) {
        this.datumResenja = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}