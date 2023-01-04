package rs.ac.uns.ftn.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/autorsko-delo")
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	@PostMapping("/save-new")
	public void saveNewFile() {
		System.out.println("AAAAA");
		autorskoDeloService.saveNewFile();
	}

}
