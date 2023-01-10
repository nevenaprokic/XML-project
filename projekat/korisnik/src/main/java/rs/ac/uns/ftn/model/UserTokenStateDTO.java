package rs.ac.uns.ftn.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "jwt"
})
@XmlRootElement(name = "token", namespace = "http://ftn.uns.ac.rs/token")
public class UserTokenStateDTO {
    @XmlElement(namespace = "http://ftn.uns.ac.rs/token", required = true)
    public String jwt;

    public UserTokenStateDTO() {
    }

    public UserTokenStateDTO(String jwt) {
        this.jwt = jwt;
    }
}
