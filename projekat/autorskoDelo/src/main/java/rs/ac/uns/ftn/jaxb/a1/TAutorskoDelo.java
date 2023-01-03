//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:31:28 PM CET 
//


package rs.ac.uns.ftn.jaxb.a1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TAutorsko_delo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAutorsko_delo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ftn.uns.ac.rs/a1}TOsnovni_podaci_o_delu"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Podaci_o_originalu" type="{http://ftn.uns.ac.rs/a1}TOsnovni_podaci_o_delu" minOccurs="0"/&gt;
 *         &lt;element name="Radni_odnos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nacin_koriscenja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="vrsta" type="{http://ftn.uns.ac.rs/a1}vrsta_autorskog_dela" /&gt;
 *       &lt;attribute name="forma_zapisa" type="{http://ftn.uns.ac.rs/a1}forma_zapisa_autorskog_dela" /&gt;
 *       &lt;attribute name="prerada" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutorsko_delo", propOrder = {
    "podaciOOriginalu",
    "radniOdnos",
    "nacinKoriscenja"
})
public class TAutorskoDelo
    extends TOsnovniPodaciODelu
{

    @XmlElement(name = "Podaci_o_originalu", namespace = "http://ftn.uns.ac.rs/a1", required = false)
    protected TOsnovniPodaciODelu podaciOOriginalu;
    @XmlElement(name = "Radni_odnos", namespace = "http://ftn.uns.ac.rs/a1", required = false)
    protected String radniOdnos;
    @XmlElement(name = "Nacin_koriscenja", namespace = "http://ftn.uns.ac.rs/a1", required = false)
    protected String nacinKoriscenja;
    @XmlAttribute(name = "vrsta")
    protected VrstaAutorskogDela vrsta;
    @XmlAttribute(name = "forma_zapisa")
    protected FormaZapisaAutorskogDela formaZapisa;
    @XmlAttribute(name = "prerada")
    protected Boolean prerada;

    /**
     * Gets the value of the podaciOOriginalu property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TOsnovniPodaciODelu }{@code >}
     *     
     */
    public TOsnovniPodaciODelu getPodaciOOriginalu() {
        return podaciOOriginalu;
    }

    /**
     * Sets the value of the podaciOOriginalu property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TOsnovniPodaciODelu }{@code >}
     *     
     */
    public void setPodaciOOriginalu(TOsnovniPodaciODelu value) {
        this.podaciOOriginalu = value;
    }

    /**
     * Gets the value of the radniOdnos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getRadniOdnos() {
        return radniOdnos;
    }

    /**
     * Sets the value of the radniOdnos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRadniOdnos(String value) {
        this.radniOdnos = value;
    }

    /**
     * Gets the value of the nacinKoriscenja property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getNacinKoriscenja() {
        return nacinKoriscenja;
    }

    /**
     * Sets the value of the nacinKoriscenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNacinKoriscenja(String value) {
        this.nacinKoriscenja = value;
    }

    /**
     * Gets the value of the vrsta property.
     * 
     * @return
     *     possible object is
     *     {@link VrstaAutorskogDela }
     *     
     */
    public VrstaAutorskogDela getVrsta() {
        return vrsta;
    }

    /**
     * Sets the value of the vrsta property.
     * 
     * @param value
     *     allowed object is
     *     {@link VrstaAutorskogDela }
     *     
     */
    public void setVrsta(VrstaAutorskogDela value) {
        this.vrsta = value;
    }

    /**
     * Gets the value of the formaZapisa property.
     * 
     * @return
     *     possible object is
     *     {@link FormaZapisaAutorskogDela }
     *     
     */
    public FormaZapisaAutorskogDela getFormaZapisa() {
        return formaZapisa;
    }

    /**
     * Sets the value of the formaZapisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaZapisaAutorskogDela }
     *     
     */
    public void setFormaZapisa(FormaZapisaAutorskogDela value) {
        this.formaZapisa = value;
    }

    /**
     * Gets the value of the prerada property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrerada() {
        return prerada;
    }

    /**
     * Sets the value of the prerada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrerada(Boolean value) {
        this.prerada = value;
    }

    @Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(super.toString());
        
        if(radniOdnos!=null) {
        	buffer.append("\n\t\t Radni odnos: ");
        	buffer.append(radniOdnos);
        }
        
        if(nacinKoriscenja!=null) {
        	buffer.append("\n\t\t Nacin koriscenja: ");
        	buffer.append(nacinKoriscenja);
        }
        
        if(vrsta!=null) {
        	buffer.append("\n\t\t Vrsta:");
        	buffer.append(vrsta);
        }
        
        if(formaZapisa!=null) {
        	buffer.append("\n\t\t Forma zapisa:");
        	buffer.append(formaZapisa);
        }
        
        if(prerada!=null) {
        	if(prerada) {
        		buffer.append("\n");
        		buffer.append("\n\t\t Podaci o originalu:");
        		buffer.append(podaciOOriginalu);
        	}
        }
        
        
        return buffer.toString();
		
	}
}
