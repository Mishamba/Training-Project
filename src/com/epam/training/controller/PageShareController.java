package com.epam.training.controller;

import javax.annotation.security.PermitAll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageShareController {
	
	@PermitAll
	@GetMapping("/file-browser")
	public String sendFileBrowser() {
		return "WEB-INF/index.html";
	}
	
	@PreAuthorize("hasAuthority('get-users-browser-page')")
	@GetMapping("/users-browser")
	public String sendUsersBrowers() {
		return "WEB-INF/users.html";
	}
}
