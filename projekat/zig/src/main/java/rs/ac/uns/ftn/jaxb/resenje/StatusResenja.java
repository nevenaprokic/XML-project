
package main.java.rs.ac.uns.ftn.jaxb.resenje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for status_resenja.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="status_resenja">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="odobren"/>
 *     &lt;enumeration value="odbijen"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "status_resenja", namespace = "http://ftn.uns.ac.rs/resenje")
@XmlEnum
public enum StatusResenja {

    @XmlEnumValue("odobren")
    ODOBREN("odobren"),
    @XmlEnumValue("odbijen")
    ODBIJEN("odbijen");
    private final String value;

    StatusResenja(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusResenja fromValue(String v) {
        for (StatusResenja c: StatusResenja.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
