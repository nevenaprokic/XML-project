
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPunomocnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPunomocnik">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/p1}TLice">
 *       &lt;attribute name="za_prijem_pismena" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="za_zastupanje" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPunomocnik", namespace = "http://www.ftn.uns.ac.rs/p1")
public class TPunomocnik
    extends TLice
{

    @Override
    public String toString() {
        return "Punomocnik: " + '\n' + '\t' +
                "za prijem pismena: " + zaPrijemPismena +'\n' + '\t' +
                "za zastupanje: " + zaZastupanje + "\n\t\t" + super.toString();
    }

    @XmlAttribute(name = "za_prijem_pismena", required = true)
    protected boolean zaPrijemPismena;
    @XmlAttribute(name = "za_zastupanje", required = true)
    protected boolean zaZastupanje;

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

}
