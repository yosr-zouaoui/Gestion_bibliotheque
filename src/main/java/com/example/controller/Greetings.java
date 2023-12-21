package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Greetings {

	@GetMapping("/greetings")
	public String greetings()
	{
		return "greetings";
	}
}
