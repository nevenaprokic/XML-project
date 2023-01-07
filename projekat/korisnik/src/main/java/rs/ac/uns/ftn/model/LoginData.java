package rs.ac.uns.ftn.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "username",
        "password"
})
@XmlRootElement(name = "token", namespace = "http://ftn.uns.ac.rs/login")
public class LoginData {
    @XmlElement(namespace = "http://ftn.uns.ac.rs/login", required = true)
    public String username;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/login", required = true)
    public String password;

    public LoginData() {
    }

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    
    public void setPassword(String password) {
    	this.username = username;
    }
    
    
    public String getUsername() {
    	return this.username;
    }
    
    
    public String getPassword() {
    	return this.username;
    }
}