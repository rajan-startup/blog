package com.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/community")
@Controller
public class CommunityController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		
		return "construction"; 
	}
	
}