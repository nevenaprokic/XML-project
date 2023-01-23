//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:31:28 PM CET 
//


package rs.ac.uns.ftn.jaxb.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for TAutori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAutori"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Autor" type="{http://ftn.uns.ac.rs/a1}TAutor" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutori", propOrder = {
    "autor"
})
public class TAutori {

    @XmlElement(name = "Autor", required = true)
    protected List<TAutor> autor;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    /**
     * Gets the value of the autor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TAutor }
     * 
     * 
     */
    public List<TAutor> getAutor() {
        if (autor == null) {
            autor = new ArrayList<TAutor>();
        }
        return this.autor;
    }

    
    public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		if(autor!=null) {
			for (TAutor tAutor : autor) {
	        	buffer.append("\n\t\t Autor br." + (i+1));
				buffer.append(tAutor);
				i++;
				
			}
		}
        

        return buffer.toString();
	}

}
