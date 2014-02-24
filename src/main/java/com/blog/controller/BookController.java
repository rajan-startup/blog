package com.blog.controller;


import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.blog.constant.BlogConstants;
import com.blog.entity.BlogDao;
import com.blog.entity.Topic;
import com.blog.model.Index;
import com.google.appengine.api.datastore.Text;


@RequestMapping("/book")
@Controller
public class BookController {
	private final BlogDao BlogDao;
	private static boolean inIt = false;
	
	

	@Autowired
	public BookController(final BlogDao BlogDao) {
		this.BlogDao = BlogDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		
		if(!inIt){
			BlogRequestHandler.getInstance().initBlog(BlogDao,BlogConstants.BOOK_TOPIC_DIR);
			inIt = true;
		}
		
		Index index = FileHandler.getInstance().getIndex(BlogConstants.BOOK_TOPIC_DIR);
		model.addAttribute("index", index);
		model.addAttribute("type", "book");
		return "blog";
	}
	
	

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable final String id) {
		List<Topic> topics = BlogDao.getTopic(id);
		model.addAttribute("topics", topics);
		model.addAttribute("type", "book");
		return "blog";
	}
	
	
	
}