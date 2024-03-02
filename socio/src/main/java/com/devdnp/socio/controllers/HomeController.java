package com.devdnp.socio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping
	public String homeControllerHandler() {
		
		return "This is Home Controller";
	}
}
