package rs.ac.uns.ftn.jaxb.a1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "idA" })
@XmlRootElement(name = "idAutorskogDela", namespace = "http://ftn.uns.ac.rs/a1")
public class IdAutorskogDela {
	@XmlElement(namespace = "http://ftn.uns.ac.rs/a1", required = true)
	protected String idA;
	@XmlAnyAttribute
	private Map<QName, String> otherAttributes = new HashMap<QName, String>();

	/**
	 * Gets the value of the idZ property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdA() {
		return idA;
	}

	/**
	 * Sets the value of the idZ property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setIdA(String value) {
		this.idA = value;
	}

	/**
	 * Gets a map that contains attributes that aren't bound to any typed property
	 * on this class.
	 * 
	 * <p>
	 * the map is keyed by the name of the attribute and the value is the string
	 * value of the attribute.
	 * 
	 * the map returned by this method is live, and you can add new attribute by
	 * updating the map directly. Because of this design, there's no setter.
	 * 
	 * 
	 * @return always non-null
	 */
	public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

}
