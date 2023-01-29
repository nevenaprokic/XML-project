package rs.ac.uns.ftn.jaxb.prilog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prilog_Image")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrilogImage {
	@XmlElement(name = "Naziv_priloga", nillable=true, required = false)
    protected String nazivPriloga;
	
	@XmlElement(name = "Sadrzaj_priloga", nillable=true, required = false)
    protected String sadrzajPriloga;
	
	public PrilogImage() {
	}

	public String getNazivPriloga() {
		return nazivPriloga;
	}
	
	public String getSadrzajPriloga() {
		return sadrzajPriloga;
	}

	public void setNazivPriloga(String nazivPriloga) {
		this.nazivPriloga = nazivPriloga;
	}

	public void setSadrzajPriloga(String sadrzajPriloga) {
		this.sadrzajPriloga = sadrzajPriloga;
	}
	
	
}
