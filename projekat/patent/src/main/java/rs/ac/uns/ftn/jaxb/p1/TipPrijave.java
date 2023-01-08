package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "tip_prijave")
@XmlEnum
public enum TipPrijave {

 	@XmlEnumValue("dopunska")
    DOPUNSKA("dopunska"),
    @XmlEnumValue("izdvojena")
    IZDVOJENA("izdvojena");
    private final String value;

 	TipPrijave(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipPrijave fromValue(String v) {
        for (TipPrijave c: TipPrijave.values()) {
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

