package com.blog.controller;

import java.util.List;

import com.blog.constant.BlogConstants;
import com.blog.entity.BlogDao;
import com.blog.entity.Topic;

public class BlogRequestHandler {

	private static final BlogRequestHandler handler = new BlogRequestHandler();
	
	public static BlogRequestHandler getInstance(){
		return handler;
	}
	
	
	public void initBlog(BlogDao dao,String directory) {
		
		List<Topic> topics = FileHandler.getInstance().getAllTopics(directory);
		
		if(topics!=null && !topics.isEmpty()){
			for(Topic topic : topics){
				
				List<Topic> existTopics = dao.getTopic(topic.getId());
				
				if(existTopics!=null && !existTopics.isEmpty()){
					//TODO: Log already exist
				}else{
					dao.store(topic);
				}
				
				
			}
		}
		
	}
}
