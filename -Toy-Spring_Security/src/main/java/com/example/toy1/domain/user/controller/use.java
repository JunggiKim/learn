package com.example.toy1.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class use {

	@PostMapping("/sucess")
	public String sucess() {

		return "index";
	}

	@GetMapping("/sucess")
	public String sucess2() {
		return "index";
	}

}
