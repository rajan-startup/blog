package com.blog.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.blog.constant.BlogConstants;
import com.blog.entity.BlogDao;
import com.blog.entity.Topic;


@RequestMapping("/blog/")
@Controller
public class BlogController {
	private final BlogDao BlogDao;
	
	private static final String DELIMITER = "$";
	
	private static boolean inIt = false;
	

	@Autowired
	public BlogController(final BlogDao BlogDao) {
		this.BlogDao = BlogDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		System.out.println("I am in get method");
		
		if(!inIt){
			init();
			inIt = true;
		}
		
		List<Topic> topics = BlogDao.getAll();
		model.addAttribute("topics", topics);
		return "blog";
	}
	
	

	@RequestMapping(value = "id", method = RequestMethod.GET)
	public String read(Model model,@RequestParam("id") final Long id) {
		System.out.println("I am in get method");
		Topic topic = BlogDao.getTopic(id);
		model.addAttribute("topic", topic);
		return "blog";
	}

	@RequestMapping(value = "/postTopics", method = RequestMethod.POST)
	public View sign(@RequestParam("content") final String content,@RequestParam("id") final long id) {
		StringTokenizer st = new StringTokenizer(content, DELIMITER);
		
		List<String> list = new ArrayList<String>();
		while(st.hasMoreElements()){
			list.add(st.nextToken());
		}
		
		Topic topic = new Topic(id,list);
		BlogDao.store(topic);

		return new RedirectView("/blog/", true, true, false);
	}
	
	private void init() {
		
		long id = 1;
//		Topic topic = BlogDao.getTopic(id);
		List<Topic> topic = BlogDao.getAll();
		if(topic==null || topic.isEmpty()){
			
			List<String> content = new ArrayList<String>();
			content.add("H1"+BlogConstants.TOPIC_TAG_DELIMITER+"Header"+BlogConstants.TOPIC_DELIMITER);
			content.add("H4"+BlogConstants.TOPIC_TAG_DELIMITER+"Test"+BlogConstants.TOPIC_DELIMITER);
			content.add("IMG"+BlogConstants.TOPIC_TAG_DELIMITER+"http://www.w3schools.com/tags/smiley.gif"+BlogConstants.TOPIC_DELIMITER);
			
			Topic newTopic = new Topic(1,content);
			BlogDao.store(newTopic);
			
		}
		
		
		
		
	}
	
}