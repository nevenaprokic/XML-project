
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


import rs.ac.uns.ftn.jaxb.zajednicko.*;

/**
 * <p>Java class for TPronalazac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPronalazac">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/p1}TFizicko_lice">
 *       &lt;attribute name="anoniman" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPronalazac", namespace = "http://www.ftn.uns.ac.rs/p1")
public class TPronalazac
    extends TFizickoLice
{

    @XmlAttribute(name = "anoniman", required = true)
    protected boolean anoniman;

    /**
     * Gets the value of the anoniman property.
     * 
     */
    public boolean isAnoniman() {
        return anoniman;
    }

    /**
     * Sets the value of the anoniman property.
     * 
     */
    public void setAnoniman(boolean value) {
        this.anoniman = value;
    }

    @Override
    public String toString() {
        String isAnoniman = anoniman? "da" : "ne";
        return "Pronalazac: " + "\n\t\t" +
                "anoniman: " + isAnoniman + "\n\t\t" + super.toString();

    }
}
