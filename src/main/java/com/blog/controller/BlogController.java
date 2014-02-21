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


@RequestMapping("/blog")
@Controller
public class BlogController {
	private final BlogDao BlogDao;
	
	private static final String DELIMITER = "$";
	private static final String TOPIC_DIR = "doc";
	
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
		
		Index index = FileHandler.getInstance().getIndex(TOPIC_DIR);
		
		/*List<Topic> topics = BlogDao.getAll();
		model.addAttribute("topics", topics);*/
		model.addAttribute("index", index);
		return "blog";
	}
	
	

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable final Long id) {
		System.out.println("I am in get method");
		List<Topic> topics = BlogDao.getTopic(id);
		model.addAttribute("topics", topics);
		return "blog";
	}
	
	
	
	
	@RequestMapping(value = "/postTopics", method = RequestMethod.POST)
	public View sign(@RequestParam("content") final String content,@RequestParam("id") final long id,@RequestParam("title") final String title) {
		StringTokenizer st = new StringTokenizer(content, DELIMITER);
		
		Topic topic = new Topic(id,title,new Text(content));
		BlogDao.store(topic);

		return new RedirectView("/blog/", true, true, false);
	}
	
	private void init() {
		
		List<Topic> topics = FileHandler.getInstance().getAllTopics(TOPIC_DIR);
		
		if(topics!=null && !topics.isEmpty()){
			for(Topic topic : topics){
				BlogDao.store(topic);
			}
		}
		
		
	}
	
	private void initTest() {
		
//		Topic topic = BlogDao.getTopic(id);
		List<Topic> topic = BlogDao.getAll();
		if(topic==null || topic.isEmpty()){
			
			StringBuilder content = new StringBuilder();
			content.append("H1"+BlogConstants.TOPIC_TAG_DELIMITER+"Header"+BlogConstants.TOPIC_DELIMITER);
			content.append("H4"+BlogConstants.TOPIC_TAG_DELIMITER+"Test"+BlogConstants.TOPIC_DELIMITER);
			content.append("IMG"+BlogConstants.TOPIC_TAG_DELIMITER+"http://www.w3schools.com/tags/smiley.gif"+BlogConstants.TOPIC_DELIMITER);
			
			Topic newTopic = new Topic(1,"git",new Text(content.toString()));
			
			List<Topic> topics = FileHandler.getInstance().getAllTopics("doc");
			
			BlogDao.store(newTopic);
			
		}
		
		
		
		
	}
	
}