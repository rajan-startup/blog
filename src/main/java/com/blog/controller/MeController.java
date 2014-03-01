package com.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.entity.BlogDao;
import com.blog.model.SearchResult;


@RequestMapping("/me")
@Controller
public class MeController {
	private final BlogDao BlogDao;
	private static boolean inIt = false;
	private static final String metaFileName = "meta/blogMeta.txt";
	private static SearchResult searchResult;
	
	
	@Autowired
	public MeController(final BlogDao BlogDao) {
		this.BlogDao = BlogDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		
		model.addAttribute("type", "me");
		return "blog";
	}
	
	
	
}