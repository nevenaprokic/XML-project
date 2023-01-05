package rs.ac.uns.ftn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.jaxb.Jaxb;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.repository.UserRepository;
import rs.ac.uns.ftn.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private Jaxb jaxb;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public User getUserBuEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Override
	public boolean saveNewUser(User user) {
//        if (getUserBuEmail(user.getEmail()) != null) {
//        	return false;
//        }
//    	user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if (jaxb.validate(user.getClass(), user)) {
           userRepository.saveNewUser(user);
           return true;
//        }
//        return false;		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserBuEmail(username);
	}
	
	

}
