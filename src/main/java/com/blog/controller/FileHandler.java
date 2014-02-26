package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.core.io.FileSystemResource;

import com.blog.constant.BlogConstants;
import com.blog.entity.Topic;
import com.blog.model.Bullet;
import com.blog.model.Index;
import com.google.appengine.api.datastore.Text;

public class FileHandler {

	private static FileHandler handler = new FileHandler();
	
	public static FileHandler getInstance() {
		return handler;
	}

	public List<Topic> getAllTopics(String directory) {
		
		List<String> fileNames = getFileNameList(directory);
		
		if(fileNames!=null && !fileNames.isEmpty()){
			
			List<Topic> topics = new ArrayList<Topic>();
			
			for(String fileName : fileNames){
				File file = new File(fileName);
				String[] idStr = file.getName().split("-");
				String content = readFileText(fileName);
				
				Text text = new Text(content);
				
				try{
					Topic topic = new Topic(idStr[0].trim(),idStr[1].trim(),text);
					topics.add(topic);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
				
				
			}
			
			return topics;
		}
		
		return null;
	}

	private List<String> getFileNameList(String directory) {
		
		FileSystemResource resource = new FileSystemResource(directory);
		
		File dir = resource.getFile();
		
		if(dir!=null && dir.isDirectory()){
			File[] files = dir.listFiles();
			if(files!=null){
				
				List<String> fileNameList = new ArrayList<String>();
				
				for(File file: files){
					System.out.println(file.getName());
					if(file.isFile()){
						fileNameList.add(file.getAbsolutePath());
					}
				}
				return fileNameList;
			}
		}
		
		return null;
	}

	private String readFileText(String fileName) {
		
		FileSystemResource resource = new FileSystemResource(fileName);
		
		InputStream in = null;
		try {
			 in = resource.getInputStream();
			
			int content;
			StringBuilder str = new StringBuilder();
			while ((content = in.read()) != -1) {
				// convert to char and display it
				str.append((char)content);
			}
 
			return str.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}



	public Index getIndex(String topicDir) {
		
		List<String> fileNames = getFileNameList(topicDir);
		
		if(fileNames!=null && !fileNames.isEmpty()){
			
			Index index = new Index();
			
			for(String fileName : fileNames){
				File file = new File(fileName);
				String[] idStr = file.getName().split("-");
				
				try{
					if(idStr.length>=2){
						index.getBullets().add(new Bullet(idStr[0],idStr[1]));
					}
					
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
				
				
			}
			
			return index;
		}
		
		return null;
	}
	
	
	public Map<String,Set<String>> getMeta(String fileName) {
		
		Map<String,Set<String>> metaMap = new HashMap<String,Set<String>>();
		String content = readFileText(fileName);
		
		if(content!=null){
			
			String[] metas = content.split(BlogConstants.LINE_DELIMITER);
			
			if(metas!=null){
				
				for(String meta : metas){
					
					String[] topicMeta = meta.split(":");
					
					if(topicMeta!=null && topicMeta.length==2){
						
						StringTokenizer st = new StringTokenizer(topicMeta[1],";");
						
						Set<String> metaTagSet = new HashSet<String>();
						metaMap.put(topicMeta[0], metaTagSet);
						
						while(st.hasMoreElements()){
							metaTagSet.add(st.nextToken().trim());
						}
						
					}
					
				}
			}
		}
		
		return metaMap;
	}

	
	
	
	
	
	
	
	/************************** Helper method **************/
	
	public static void main(String[] args){
		
		FileHandler handler = new FileHandler();
//		handler.testBlogFiles();
//		handler.getMeta("C:/startup/code/2_9/blog/src/main/webapp/meta/meta.txt");
		String  str = "\trajan\t\n";
		System.out.println(str.replaceAll("\t|\n","RAJ"));
		
		
	}
	
	private void testBlogFiles() {
		
		FileHandler handler = new FileHandler();
		String dir = "src/main/webapp/doc/";
//		handler.getAllTopics(dir);
		
		String str = handler.readFileText(dir+"1-git.txt");
		
		String[] strs = str.split(BlogConstants.TOPIC_DELIMITER);
		for(String tag: strs){
			
			String[] tokens = tag.split("#@");
			System.out.println(tokens.length);
			
			
		}
		System.out.println(strs.length);
		
	}
	
	
}
