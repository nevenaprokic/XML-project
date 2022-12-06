
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vrsta_ziga_na_osnovu_izgleda.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="vrsta_ziga_na_osnovu_izgleda">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="graficki"/>
 *     &lt;enumeration value="kombinovani"/>
 *     &lt;enumeration value="trodimenzionalni"/>
 *     &lt;enumeration value="drugo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "vrsta_ziga_na_osnovu_izgleda", namespace = "http://ftn.uns.ac.rs/z1")
@XmlEnum
public enum VrstaZigaNaOsnovuIzgleda {

    @XmlEnumValue("graficki")
    GRAFICKI("graficki"),
    @XmlEnumValue("kombinovani")
    KOMBINOVANI("kombinovani"),
    @XmlEnumValue("trodimenzionalni")
    TRODIMENZIONALNI("trodimenzionalni"),
    @XmlEnumValue("drugo")
    DRUGO("drugo");
    private final String value;

    VrstaZigaNaOsnovuIzgleda(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VrstaZigaNaOsnovuIzgleda fromValue(String v) {
        for (VrstaZigaNaOsnovuIzgleda c: VrstaZigaNaOsnovuIzgleda.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String toString() {
        return value;
    }

}
