package rs.ac.uns.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@PreAuthorize("hasAnyAuthority('KORISNIK', 'SLUZBENIK')")
	@GetMapping(value="/authkorisnik-or-sluzbenik")
	public ResponseEntity<String> AuthKorisnikOrSluzbenik(){
		return ResponseEntity.ok("success");
	}
}
