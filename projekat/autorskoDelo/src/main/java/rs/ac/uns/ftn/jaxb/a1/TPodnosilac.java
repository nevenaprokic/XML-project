//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:31:28 PM CET 
//


package rs.ac.uns.ftn.jaxb.a1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;


/**
 * <p>Java class for TPodnosilac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPodnosilac"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="Autor" type="{http://ftn.uns.ac.rs/a1}TAutor" minOccurs="0"/&gt;
 *         &lt;element name="Punomocnik" type="{http://www.ftn.uns.ac.rs/zaj}TFizicko_lice" minOccurs="0"/&gt;
 *         &lt;element name="Pravno_lice" type="{http://www.ftn.uns.ac.rs/zaj}TPravno_lice" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPodnosilac", propOrder = {
    "autor",
    "punomocnik",
    "pravnoLice"
})
public class TPodnosilac {

    @XmlElement(name = "Autor", namespace = "http://ftn.uns.ac.rs/a1" )
    protected TAutor autor;
    @XmlElement(name = "Punomocnik", namespace = "http://www.ftn.uns.ac.rs/zaj" )
    protected TFizickoLice punomocnik;
    @XmlElement(name = "Pravno_lice", namespace = "http://www.ftn.uns.ac.rs/zaj")
    protected TPravnoLice pravnoLice;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    /**
     * Gets the value of the autor property.
     * 
     * @return
     *     possible object is
     *     {@link TAutor }
     *     
     */
    public TAutor getAutor() {
        return autor;
    }

    /**
     * Sets the value of the autor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAutor }
     *     
     */
    public void setAutor(TAutor value) {
        this.autor = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link TFizickoLice }
     *     
     */
    public TFizickoLice getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFizickoLice }
     *     
     */
    public void setPunomocnik(TFizickoLice value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the pravnoLice property.
     * 
     * @return
     *     possible object is
     *     {@link TPravnoLice }
     *     
     */
    public TPravnoLice getPravnoLice() {
        return pravnoLice;
    }

    /**
     * Sets the value of the pravnoLice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPravnoLice }
     *     
     */
    public void setPravnoLice(TPravnoLice value) {
        this.pravnoLice = value;
    }
    
    
    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	@Override
    public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t\t");
		if(pravnoLice!=null) {
			buffer.append(pravnoLice);
		}
		else if(punomocnik!=null) {
			buffer.append(punomocnik);
		}
		else if(autor!=null){
			buffer.append(autor);
		}
		else {
			buffer.append("Adresa: Urosa Predica 7, Novi Sad");
			buffer.append("\n\t\t");
			buffer.append("Kontakt");
			buffer.append("\n\t\t\t");
			buffer.append("Telefon: 065/222-3332");
			buffer.append("\n\t\t\t");
			buffer.append("Email: pera@gmail.com");
			buffer.append("\n\t\t");
			buffer.append("Naziv: Pera organization");
		}
		return buffer.toString();
    }
}
