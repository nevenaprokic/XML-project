//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.08 at 12:49:13 AM CET 
//


package rs.ac.uns.ftn.a1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPrilog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPrilog"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="Prisutan_opis" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Prisutan_primer" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrilog", propOrder = {

})
public class TPrilog {

    @XmlElement(name = "Prisutan_opis")
    protected boolean prisutanOpis;
    @XmlElement(name = "Prisutan_primer")
    protected boolean prisutanPrimer;

    /**
     * Gets the value of the prisutanOpis property.
     * 
     */
    public boolean isPrisutanOpis() {
        return prisutanOpis;
    }

    /**
     * Sets the value of the prisutanOpis property.
     * 
     */
    public void setPrisutanOpis(boolean value) {
        this.prisutanOpis = value;
    }

    /**
     * Gets the value of the prisutanPrimer property.
     * 
     */
    public boolean isPrisutanPrimer() {
        return prisutanPrimer;
    }

    /**
     * Sets the value of the prisutanPrimer property.
     * 
     */
    public void setPrisutanPrimer(boolean value) {
        this.prisutanPrimer = value;
    }

}
