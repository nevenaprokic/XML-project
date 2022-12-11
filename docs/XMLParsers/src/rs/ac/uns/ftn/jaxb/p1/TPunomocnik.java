
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.ac.uns.ftn.jaxb.zajednicko.*;


/**
 * <p>Java class for TPunomocnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPunomocnik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lice" type="{http://www.ftn.uns.ac.rs/p1}TLice"/>
 *         &lt;element name="za_zastupanje" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="za_prijem_pismena" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPunomocnik", namespace = "http://www.ftn.uns.ac.rs/p1", propOrder = {
    "lice",
    "zaZastupanje",
    "zaPrijemPismena"
})
public class TPunomocnik {

    @XmlElement(name = "Lice", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected TLice lice;
    @XmlElement(name = "za_zastupanje", namespace = "http://www.ftn.uns.ac.rs/p1")
    protected boolean zaZastupanje;
    @XmlElement(name = "za_prijem_pismena", namespace = "http://www.ftn.uns.ac.rs/p1")
    protected boolean zaPrijemPismena;

    /**
     * Gets the value of the lice property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setLice(TLice value) {
        this.lice = value;
    }

    /**
     * Gets the value of the zaZastupanje property.
     * 
     */
    public boolean isZaZastupanje() {
        return zaZastupanje;
    }

    /**
     * Sets the value of the zaZastupanje property.
     * 
     */
    public void setZaZastupanje(boolean value) {
        this.zaZastupanje = value;
    }

    /**
     * Gets the value of the zaPrijemPismena property.
     * 
     */
    public boolean isZaPrijemPismena() {
        return zaPrijemPismena;
    }

    /**
     * Sets the value of the zaPrijemPismena property.
     * 
     */
    public void setZaPrijemPismena(boolean value) {
        this.zaPrijemPismena = value;
    }

    @Override
    public String toString() {
        String isPrijemPismena = zaPrijemPismena? "da" :" ne";
        String isZastupanje = zaZastupanje? "da" :" ne";

        return "Punomocnik: " + "\n\t\t" +
                "za prijem pismena: " + isPrijemPismena +"\n\t\t" +
                "za zastupanje: " + isZastupanje + "\n\t\t" + lice.toString();
    }

}
