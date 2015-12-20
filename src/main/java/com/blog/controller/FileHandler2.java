package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import org.datanucleus.util.StringUtils;
import org.springframework.core.io.FileSystemResource;

import com.blog.constant.BlogConstants;
import com.blog.entity.Topic;
import com.blog.model.Bullet;
import com.blog.model.Index;
import com.google.appengine.api.datastore.Text;

public class FileHandler2 {

	private static FileHandler2 handler = new FileHandler2();
	static final Map<String,List<String>> fileMap = new ConcurrentHashMap<String,List<String>>();
	
	public static FileHandler2 getInstance() {
		return handler;
	}

	public List<Topic> getAllTopics(String directory) {
		
		List<String> fileNames = getFileNameList(directory);
		
		if(fileNames!=null && !fileNames.isEmpty()){
			
			List<Topic> topics = new ArrayList<Topic>();
			
			for(String fileName : fileNames){
				updateTopics(directory, fileName,topics,false);
			}
			
			return topics;
		}
		
		return null;
	}

	
	public static List<Topic> getAllTopics(String directory,String id) {
		
		List<String> fileNames = getFileNameList(directory);
		
		List<Topic> topics = new ArrayList<Topic>();
		
		if(fileNames!=null){
			for(String fileName : fileNames){
				if(fileName.contains(id)){
					updateTopics(directory, fileName,topics,true);
				}
			}
		}
		
		return topics;
		
		
	}
	
	private static void updateTopics(String dir, String fileName, List<Topic> topics,boolean readContent) {
		
		if(!StringUtils.isEmpty(fileName) && topics!=null){

			File file = new File(fileName);
			String[] idStr = file.getName().split("-");
			String content = null;
			if(readContent){
				content = readFileText(dir, fileName);
			}
			 
			Text text = new Text(content);
			
			try{
				Topic topic = new Topic(idStr[0].trim(),idStr[1].trim(),text);
				topics.add(topic);
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		
		}
		
	}

	private static List<String> getFileNameList(String directory) {
		
		return fileMap.get(directory);
	}

	private static String readFileText(String dir, String fileName) {
		return WebClientHandler.getData(dir+"/"+fileName);
	}



	public Index getIndex(String type,String topicDir) {
		
		List<String> fileNames = getFileNameListFromUrl(topicDir);
		fileMap.put(topicDir, fileNames);
		
		if(fileNames!=null && !fileNames.isEmpty()){
			
			Index index = new Index();
			
			for(String fileName : fileNames){
				File file = new File(fileName);
				String[] idStr = file.getName().split("-");
				
				try{
					if(idStr.length>=2){
						index.getBullets().add(new Bullet(type,idStr[0],idStr[1]));
					}
					
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			return index;
		}
		return null;
	}
	
	private List<String> getFileNameListFromUrl(String topicDir) {
		String list = WebClientHandler.getData(topicDir+"/list");
		if(!StringUtils.isEmpty(list)){
			return Arrays.asList(list.split("\n"));
		}
		return null;
	}

	public Index getIndex1(String type,String topicDir) {
		
		List<String> fileNames = getFileNameList(topicDir);
		
		if(fileNames!=null && !fileNames.isEmpty()){
			
			Index index = new Index();
			
			for(String fileName : fileNames){
				File file = new File(fileName);
				String[] idStr = file.getName().split("-");
				
				try{
					if(idStr.length>=2){
						index.getBullets().add(new Bullet(type,idStr[0],idStr[1]));
					}
					
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			return index;
		}
		return null;
	}
	
	
	
	/************************** Helper method **************/
	
	public static void main(String[] args){
		
		FileHandler2 handler = new FileHandler2();
//		handler.testBlogFiles();
//		handler.getMeta("C:/startup/code/2_9/blog/src/main/webapp/meta/meta.txt");
		String  str = "\trajan\t\n";
		System.out.println(str.replaceAll("\t|\n","RAJ"));
		
		
	}
	
	
	
}
