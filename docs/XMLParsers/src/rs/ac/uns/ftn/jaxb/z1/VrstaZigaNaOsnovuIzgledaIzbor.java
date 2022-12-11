
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vrsta_ziga_na_osnovu_izgleda_izbor.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="vrsta_ziga_na_osnovu_izgleda_izbor">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="verbalni zig"/>
 *     &lt;enumeration value="graficki"/>
 *     &lt;enumeration value="kombinovani"/>
 *     &lt;enumeration value="trodimenzionalni"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "vrsta_ziga_na_osnovu_izgleda_izbor", namespace = "http://ftn.uns.ac.rs/zig")
@XmlEnum
public enum VrstaZigaNaOsnovuIzgledaIzbor {

    @XmlEnumValue("verbalni zig")
    VERBALNI_ZIG("verbalni zig"),
    @XmlEnumValue("graficki")
    GRAFICKI("graficki"),
    @XmlEnumValue("kombinovani")
    KOMBINOVANI("kombinovani"),
    @XmlEnumValue("trodimenzionalni")
    TRODIMENZIONALNI("trodimenzionalni");
    private final String value;

    VrstaZigaNaOsnovuIzgledaIzbor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VrstaZigaNaOsnovuIzgledaIzbor fromValue(String v) {
        for (VrstaZigaNaOsnovuIzgledaIzbor c: VrstaZigaNaOsnovuIzgledaIzbor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
