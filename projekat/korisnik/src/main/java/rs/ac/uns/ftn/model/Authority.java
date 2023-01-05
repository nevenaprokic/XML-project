package rs.ac.uns.ftn.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
