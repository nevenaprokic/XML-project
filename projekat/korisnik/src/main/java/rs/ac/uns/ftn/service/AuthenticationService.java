package rs.ac.uns.ftn.service;

import rs.ac.uns.ftn.model.LoginData;
import rs.ac.uns.ftn.model.User;

public interface AuthenticationService {

	User createAuthenticationToken(LoginData logindata);

}
