package com.blog.controller;

import java.util.List;

import com.blog.entity.Topic;

public class BlogWebDao {

	public static List<Topic> getTopic(String type, String id) {
		
		return FileHandler2.getAllTopics(type,id);
	}

}
