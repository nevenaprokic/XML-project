package rs.ac.uns.ftn.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(value = "/autorsko-delo", produces = MediaType.APPLICATION_XML_VALUE)
public class AutorskoDeloController {
	
	@Autowired
	private AutorskoDeloService autorskoDeloService;
	
	@RequestMapping("/save-new")
	public void saveNewFile() {
		autorskoDeloService.saveNewFile();
	}

}
