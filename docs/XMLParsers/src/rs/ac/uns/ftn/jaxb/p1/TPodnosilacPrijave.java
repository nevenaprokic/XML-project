
package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPodnosilacPrijave complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPodnosilacPrijave">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/p1}TLice">
 *       &lt;attribute name="pronalazac" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilacPrijave", namespace = "http://www.ftn.uns.ac.rs/p1")
public class TPodnosilacPrijave
    extends TLice
{

    @XmlAttribute(name = "pronalazac", required = true)
    protected boolean pronalazac;

    /**
     * Gets the value of the pronalazac property.
     * 
     */
    public boolean isPronalazac() {
        return pronalazac;
    }

    /**
     * Sets the value of the pronalazac property.
     * 
     */
    public void setPronalazac(boolean value) {
        this.pronalazac = value;
    }

    @Override
    public String toString() {
        return "Podnosilac prijave: " + '\n' + "\t\t" +
                "pronalazac: " + pronalazac + '\n' +
                super.toString();

    }
}
