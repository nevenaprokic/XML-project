package rs.ac.uns.ftn.service.impl;

import lombok.AllArgsConstructor;
import rs.ac.uns.ftn.model.LoginData;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.service.AuthenticationService;
import rs.ac.uns.ftn.utils.TokenUtils;
import rs.ac.uns.ftn.utils.UserToken;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
    private TokenUtils tokenUtils;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public User createAuthenticationToken(LoginData loginData) {
    	System.out.println(loginData.getUsername() + " " + loginData.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateTokenForUsername(user.getUsername());
            user.setJwt(jwt);
            
            return user;
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
