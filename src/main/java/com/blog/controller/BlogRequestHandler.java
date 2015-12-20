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
	
	
	public void initBlog(BlogDao dao,String directory,String id) {
		
		List<Topic> existTopics = dao.getTopic(id);
		
		if(existTopics!=null && !existTopics.isEmpty()){
			//TODO: Log already exist
		}else{
			List<Topic> topics = FileHandler.getInstance().getAllTopics(directory,id);
			if(topics!=null && !topics.isEmpty()){
				for(Topic topic : topics){
					dao.store(topic);
				}
			}
		}
		
	}
}
