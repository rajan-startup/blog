package com.blog.model;

public class Bullet{
	
	private String id;
	private String name;
	
	public Bullet(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Bullet() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
