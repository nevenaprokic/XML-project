
package rs.ac.uns.ftn.jaxb.p1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;all>
 *         &lt;element name="Naziv_na_srpskom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Naziv_na_engleskom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Pronalazak", namespace = "http://www.ftn.uns.ac.rs/p1")
public class Pronalazak {

    @XmlElement(name = "Naziv_na_srpskom", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String nazivNaSrpskom;
    @XmlElement(name = "Naziv_na_engleskom", namespace = "http://www.ftn.uns.ac.rs/p1", required = true)
    protected String nazivNaEngleskom;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    /**
     * Gets the value of the nazivNaSrpskom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivNaSrpskom() {
        return nazivNaSrpskom;
    }

    /**
     * Sets the value of the nazivNaSrpskom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivNaSrpskom(String value) {
        this.nazivNaSrpskom = value;
    }

    /**
     * Gets the value of the nazivNaEngleskom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivNaEngleskom() {
        return nazivNaEngleskom;
    }

    /**
     * Sets the value of the nazivNaEngleskom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivNaEngleskom(String value) {
        this.nazivNaEngleskom = value;
    }

    
    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	@Override
    public String toString() {
        return "Pronalazak: " + "\n\t\t" +
                "naziv na srpskom: " + nazivNaSrpskom + "\n\t\t" +
                "naziv na engleskom: " + nazivNaEngleskom + '\n'
                ;
    }
}
