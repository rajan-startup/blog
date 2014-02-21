package com.blog.model;

import java.util.ArrayList;
import java.util.List;

public class Index {

	private List<Bullet> bullets;
	
	public List<Bullet> getBullets() {
		
		if(bullets==null){
			bullets = new ArrayList<Bullet>();
		}
		return bullets;
	}
}
