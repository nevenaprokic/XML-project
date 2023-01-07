package rs.ac.uns.ftn.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "status",
        "message",
})
@XmlRootElement(name = "ErrorMessage", namespace = "http://ftn.uns.ac.rs/error")
public class ErrorMessage {
	@XmlElement(namespace = "http://ftn.uns.ac.rs/error", required = true)
    protected int status;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/error", required = true)
    protected String message;
    
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorMessage(int i, String message) {
		super();
		this.status = i;
		this.message = message;
	}
	public ErrorMessage() {
		super();
	}
    
}
