package com.blog.controller;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.blog.constant.BlogConstants;
import com.blog.entity.BlogDao;
import com.blog.entity.Topic;
import com.blog.model.Bullet;
import com.blog.model.Index;
import com.blog.model.SearchResult;
import com.blog.model.SearchText;
import com.google.appengine.api.datastore.Text;


@RequestMapping("/blog")
@Controller
public class BlogController {
	private final BlogDao BlogDao;
	private static boolean inIt = false;
	private static final String metaFileName = "meta/blogMeta.txt";
	private static SearchResult searchResult;
	
	
	@Autowired
	public BlogController(final BlogDao BlogDao) {
		this.BlogDao = BlogDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		
		if(!inIt){
			BlogRequestHandler.getInstance().initBlog(BlogDao,BlogConstants.BLOG_TOPIC_DIR);
			inIt = true;
		}
		
		Index index = FileHandler.getInstance().getIndex(BlogConstants.BLOG_TOPIC_DIR);
		model.addAttribute("index", index);
		model.addAttribute("type", "blog");
		return "blog";
	}
	
	

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable final String id) {
		List<Topic> topics = BlogDao.getTopic(id);
		model.addAttribute("topics", topics);
		model.addAttribute("type", "blog");
		return "blog";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model,@RequestParam("key") final String content, HttpServletRequest request) {
		
		SearchResult meta = getMetaData();
		if(content!=null && !content.trim().isEmpty() && meta!=null && meta.getMeta()!=null && meta.getMeta().keySet()!=null){
			
			Index index = new Index();
			
			for(String key : meta.getMeta().keySet()){
				
				if(meta.getMeta().get(key).contains(content)){
					
					String[] ids = key.split(BlogConstants.FILE_NAME_KEY_VALUE_SEPARATOR);
					
					if(ids!=null && ids.length==2)
					
					index.getBullets().add(new Bullet(ids[0],ids[1]));
					
				}
				
			}
			
			model.addAttribute("index", index);
			model.addAttribute("searchResult", content);

		}
		
		model.addAttribute("type", "blog"); 
		return "blog";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String postTest(Model model,@RequestParam("key") final String content, HttpServletRequest request) {
		
		SearchResult meta = getMetaData();
		if(content!=null && !content.trim().isEmpty() && meta!=null && meta.getMeta()!=null && meta.getMeta().keySet()!=null){
			
			Index index = new Index();
			
			for(String key : meta.getMeta().keySet()){
				
				if(meta.getMeta().get(key).contains(content)){
					
					String[] ids = key.split(BlogConstants.FILE_NAME_KEY_VALUE_SEPARATOR);
					
					if(ids!=null && ids.length==2)
					
					index.getBullets().add(new Bullet(ids[0],ids[1]));
					
				}
				
			}
			
			model.addAttribute("index", index);
			model.addAttribute("searchResult", "content");

		}
		
		return "blog";
	}
	
	
	@RequestMapping(value = "/search1", method = RequestMethod.POST)
	public View search(@ModelAttribute(value="search1") final SearchText search,BindingResult result, HttpServletRequest request) {
		
		System.out.println(search!=null ? search.getKey() : "NO KEY");

		return new RedirectView("/blog/", true, true, false);
	}
	
	@RequestMapping(value = "/postTopics", method = RequestMethod.POST)
	public View sign(@RequestParam("content") final String content,@RequestParam("id") final String id,@RequestParam("title") final String title) {
		StringTokenizer st = new StringTokenizer(content, BlogConstants.DELIMITER);
		
		Topic topic = new Topic(id,title,new Text(content));
		BlogDao.store(topic);

		return new RedirectView("/blog/", true, true, false);
	}
	
	
	@RequestMapping(value = "/initDB", method = RequestMethod.GET)
	public void init() {
		
		List<Topic> topics = FileHandler.getInstance().getAllTopics(BlogConstants.BLOG_TOPIC_DIR);
		
		if(topics!=null && !topics.isEmpty()){
			for(Topic topic : topics){
				BlogDao.store(topic);
			}
		}
		
	}
	
	private SearchResult getMetaData(){
		
		Map<String,Set<String>> meta = FileHandler.getInstance().getMeta(metaFileName);
		if(searchResult==null){
			searchResult = new SearchResult();
			searchResult.setMeta(meta);
		}
		
		return searchResult;
		
		
	}
	
}