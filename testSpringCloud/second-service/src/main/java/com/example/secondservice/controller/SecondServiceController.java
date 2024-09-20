package com.example.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

	@GetMapping("/welcome")
	public String sample() {

		return "Welcome to the Second service";
	}


	@GetMapping("/message")
	public String message(@RequestHeader("second-request")String header){
		log.info(header);
		return "Hello World in Second Service.";
	}



	@GetMapping("/check")
	public String check(String header){
		log.info(header);
		return "hi there this is a message from second service";
	}



}