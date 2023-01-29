package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.model.LoginData;
import rs.ac.uns.ftn.model.User;
import rs.ac.uns.ftn.service.UserService;

@Controller
@RequestMapping(value="/user", consumes=MediaType.APPLICATION_XML_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity addNewUser(@RequestBody User user) {
			userService.saveNewUser(user);
			return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> getSluzbenik() {
			String user =  userService.getLoggedSluzbenik();
			return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('SLUZBENIK')")
	@GetMapping(value="/authsluzbenik")
	public ResponseEntity<String> AuthSluzbenik(){
		return ResponseEntity.ok("success");
	}
	
	@PreAuthorize("hasAuthority('KORISNIK')")
	@GetMapping(value="/authkorisnik")
	public ResponseEntity<String> AuthKorisnik(){
		return ResponseEntity.ok("success");
	}
}
