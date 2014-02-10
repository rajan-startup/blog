package com.blog.entity;

import java.util.List;


public interface BlogDao {

	Topic store(Topic topic);

	List<Topic> getAll();
	
	Topic getTopic(long id);
}