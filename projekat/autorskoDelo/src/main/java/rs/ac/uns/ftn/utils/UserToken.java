package rs.ac.uns.ftn.utils;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "jwt"
})
@XmlRootElement(name = "token", namespace = "http://ftn.uns.ac.rs/token")
public class UserToken {
    @XmlElement(namespace = "http://ftn.uns.ac.rs/token", required = true)
    public String jwt;

    public UserToken() {
    }

    public UserToken(String jwt) {
        this.jwt = jwt;
    }
}



