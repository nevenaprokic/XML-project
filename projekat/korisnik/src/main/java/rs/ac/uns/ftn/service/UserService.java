package rs.ac.uns.ftn.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import rs.ac.uns.ftn.model.User;

public interface UserService extends UserDetailsService {
	
	User getUserBuEmail(String email);
	
	boolean saveNewUser(User user);

}
