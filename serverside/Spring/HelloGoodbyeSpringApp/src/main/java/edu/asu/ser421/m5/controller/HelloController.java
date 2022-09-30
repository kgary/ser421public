package edu.asu.ser421.m5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String getHello(Model model) {
		model.addAttribute("hello", "Hello SER421 from thymeleaf");
		return "hello421"; // make sure it does not match the mapping URL
	}
	
	@GetMapping("/goodbye")
	public String getGoodbye(Model model) {
		model.addAttribute("msg", "Goodbye SER421 from Mustache");
		return "goodbye421";  // make sure it does not match the mapping URL
	}
}
