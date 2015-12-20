package com.blog.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

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
import com.blog.model.Bullet;
import com.blog.model.Index;
import com.blog.model.SearchResult;
import com.google.appengine.api.datastore.Text;


@RequestMapping("/blog")
@Controller
public class BlogController {
	private final BlogDao BlogDao;
	public static boolean inIt = false;
	private static final String metaFileName = "meta/blogMeta.txt";
	private static SearchResult searchResult;
	private static Set<String> noiseTokens = getNoiseTokens();
	
	
	
	@Autowired
	public BlogController(final BlogDao BlogDao) {
		this.BlogDao = BlogDao;
	}

	private static Set<String> getNoiseTokens() {

		String noise = "the;a;an;how;to;make;what;is;are;were;?;!;.;where;which";
		
		String[] tokens = noise.split(";");
		
		Set<String> noiseTokens = new HashSet<String>(tokens.length);
		
		Collections.addAll(noiseTokens, tokens);
		
		return noiseTokens;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		
		Index index = FileHandler.getInstance().getIndex(BlogConstants.BLOG,BlogConstants.BLOG);
		model.addAttribute("index", index);
		model.addAttribute("type", BlogConstants.BLOG);
		return "blog";
	}
	
	

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable final String id) {
		BlogRequestHandler.getInstance().initBlog(BlogDao,BlogConstants.BLOG,id);
		List<Topic> topics = BlogDao.getTopic(id);
		model.addAttribute("topics", topics);
		model.addAttribute("type", BlogConstants.BLOG);
		return "blog";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model,@RequestParam("key") final String content, HttpServletRequest request) {
		
		SearchResult meta = getMetaData();
		if(content!=null && !content.trim().isEmpty() && meta!=null && meta.getMeta()!=null && meta.getMeta().keySet()!=null){
			
			Index index = new Index();
			Set<Bullet> bullets = new HashSet<Bullet>();
			
			for(String key : meta.getMeta().keySet()){
				
				String[] tokens = content.split(" |,");
				
				for(String token: tokens){
					
					if(!token.trim().isEmpty()){

						//[1] Check metadata
						Set<String> valueSet = meta.getMeta().get(key);
						
						if(valueSet.contains(token.toLowerCase().trim())){
							
							String[] ids = key.split(BlogConstants.FILE_NAME_KEY_VALUE_SEPARATOR);
							
							if(ids!=null && ids.length==2)
							
								bullets.add(new Bullet(BlogConstants.BLOG,ids[0],ids[1]));
							
						}else{
							//If meta doesn't have then check full content
							
							
							
						}
						
					}
				}
				
			}
			
			if(bullets!=null && !bullets.isEmpty()){
				index.getBullets().addAll(new ArrayList<Bullet>(bullets));
			}else{
				
			}
			
			
			model.addAttribute("index", index);
			model.addAttribute("searchResult", content);

		}
		
		model.addAttribute("type", BlogConstants.BLOG); 
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
					
					index.getBullets().add(new Bullet(BlogConstants.BLOG,ids[0],ids[1]));
					
				}
				
			}
			
			model.addAttribute("index", index);
			model.addAttribute("searchResult", "content");

		}
		
		return "blog";
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
		
		List<Topic> topics = FileHandler.getInstance().getAllTopics(BlogConstants.BLOG);
		
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