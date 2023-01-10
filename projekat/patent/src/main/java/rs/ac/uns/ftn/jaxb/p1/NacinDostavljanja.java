package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Nacin_dostavljanja")
@XmlEnum
public enum NacinDostavljanja {
	
	@XmlEnumValue("elektronski")
    ELEKTRONSKI("elektronski"),
    @XmlEnumValue("papirna_forma")
    PAPIRNA_FORMA("papirna_forma");
    private final String value;

    NacinDostavljanja(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NacinDostavljanja fromValue(String v) {
        for (NacinDostavljanja c: NacinDostavljanja.values()) {
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
