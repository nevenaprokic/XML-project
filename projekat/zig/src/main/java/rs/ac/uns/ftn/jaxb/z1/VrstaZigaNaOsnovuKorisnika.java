
package rs.ac.uns.ftn.jaxb.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Vrsta_ziga_na_osnovu_korisnika.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Vrsta_ziga_na_osnovu_korisnika">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="individualni zig"/>
 *     &lt;enumeration value="kolektivni zig"/>
 *     &lt;enumeration value="zig garancije"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "Vrsta_ziga_na_osnovu_korisnika", namespace = "http://ftn.uns.ac.rs/zig")
@XmlEnum
public enum VrstaZigaNaOsnovuKorisnika {

    @XmlEnumValue("individualni zig")
    INDIVIDUALNI_ZIG("individualni zig"),
    @XmlEnumValue("kolektivni zig")
    KOLEKTIVNI_ZIG("kolektivni zig"),
    @XmlEnumValue("zig garancije")
    ZIG_GARANCIJE("zig garancije");
    private final String value;

    VrstaZigaNaOsnovuKorisnika(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VrstaZigaNaOsnovuKorisnika fromValue(String v) {
        for (VrstaZigaNaOsnovuKorisnika c : VrstaZigaNaOsnovuKorisnika.values()) {
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
