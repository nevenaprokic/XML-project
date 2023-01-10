package rs.ac.uns.ftn.jaxb.p1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "tip_dodatne_prijave")
@XmlEnum
public enum TipDodatnePrijave {

	@XmlEnumValue("dopunska")
    DOPUNSKA("dopunska"),
    @XmlEnumValue("izdvojena")
    IZDVOJENA("izdvojena");
    private final String value;

    TipDodatnePrijave(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipDodatnePrijave fromValue(String v) {
        for (TipDodatnePrijave c: TipDodatnePrijave.values()) {
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
