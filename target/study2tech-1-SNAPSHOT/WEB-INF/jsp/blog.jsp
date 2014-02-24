<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.blog.entity.Topic"%>
<%@page import="com.blog.model.Index"%>
<%@page import="com.blog.model.Bullet"%>
<%@page import="com.blog.model.SearchResult"%>
<%@page import="com.blog.constant.BlogConstants"%>
<%@page import="com.google.appengine.api.datastore.Text"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
		<title>Technical Blog</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,600,700" rel="stylesheet" />
		<script src="../../js/jquery.min.js"></script>
		<script src="../../js/config.js"></script>
		<script src="../../js/skel.min.js"></script>
		<noscript>
			<link rel="stylesheet" href="../../css/skel-noscript.css" />
			<link rel="stylesheet" href="../../css/style.css" />
			<link rel="stylesheet" href="../../css/style-desktop.css" />
			<link rel="stylesheet" href="../../css/menu.css" />
		</noscript>
		<link href="http://alexgorbatchev.com/pub/sh/current/styles/form.css" rel="stylesheet" type="text/css"/>
		<link href="http://alexgorbatchev.com/pub/sh/current/styles/shCore.css" rel="stylesheet" type="text/css"/>
		<link href="http://alexgorbatchev.com/pub/sh/current/styles/shThemeDefault.css" rel="stylesheet" type="text/css"/>
		<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shCore.js" type="text/javascript"></script>
		<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shBrushXml.js" type="text/javascript"></script>
		<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shBrushJava.js" type="text/javascript"></script>
		<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shBrushJScript.js" type="text/javascript"></script>
		<script language="javascript" type="text/javascript">
		SyntaxHighlighter.all();
		</script>
		
		<style type="text/css">
		
			ul#menu, ul#menu ul.sub-menu {
			   margin-left: auto;
				margin-right: auto;
				width: 1200px;
			}
			ul#menu li, ul#menu ul.sub-menu li {
			    list-style-type: none;
			    display: inline-block;
			}
			ul#menu li a{
			    text-decoration: none;
			    color: #fff;
			    padding: 0px;
				width: 125px;
			    display:inline-block;
			}
			ul#menu li ul.sub-menu li a {
			    text-decoration: none;
			    color: #fff;
			    background: #666;
			    padding: 3px;
			    display:inline-block;
				 width: 200px;
			}
			ul#menu li {
			    position: relative;
			}
			ul#menu li ul.sub-menu {
			    display:none;
			    position: absolute;
			    top: 50px;
			    left: 30;
			    width: 100px;
			}
			ul#menu li:hover ul.sub-menu {
			    display:block;
			}
		
			.wrapper
			{
				padding: 8em 0 8em 0;
				text-align: center;
			}
			
			.wrapper-first
			{
				padding-top: 4em;
			}
			
			#apple {
			   height: 28px;
			   padding: 40px 160px;
			   left: 100px;
			   padding-left:40cm;
			   position:fixed;
			}
			
			#apple #search {
			
			}
			
			#apple #search input[type="text"] {
			    background: url(/images/search-white.png) no-repeat 10px 6px #444;
			    border: 0 none;
			    font: bold 12px Arial,Helvetica,Sans-serif;
			    color: #d7d7d7;
			    width:150px;
			    padding: 6px 15px 6px 35px;
			    -webkit-border-radius: 20px;
			    -moz-border-radius: 20px;
			    border-radius: 20px;
			    text-shadow: 0 2px 2px rgba(0, 0, 0, 0.3); 
			    -webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 3px rgba(0, 0, 0, 0.2) inset;
			    -moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 3px rgba(0, 0, 0, 0.2) inset;
			    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 3px rgba(0, 0, 0, 0.2) inset;
			    -webkit-transition: all 0.7s ease 0s;
			    -moz-transition: all 0.7s ease 0s;
			    -o-transition: all 0.7s ease 0s;
			    transition: all 0.7s ease 0s;
			    }
			
			#apple #search input[type="text"]:focus {
			    background: url(/images/search-dark.png) no-repeat 10px 6px #fcfcfc;
			    color: #6a6f75;
			    width: 200px;
			    -webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 0 rgba(0, 0, 0, 0.9) inset;
			    -moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 0 rgba(0, 0, 0, 0.9) inset;
			    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 1px 0 rgba(0, 0, 0, 0.9) inset;
			    text-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
			    }
		
		</style>
	
</head>
<body>
			<nav id="nav">
			
			<% 
				String type = request.getAttribute("type")!=null ? request.getAttribute("type").toString() : null;
			
				if(type!= null && type.equalsIgnoreCase(BlogConstants.BLOG_TOPIC_DIR)) {%>
				
				<ul class="container" id="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/blog/">Hadoop</a>
							<ul class="sub-menu">
				            <li>
				                <a href="#">Map Reduce</a>
				            </li>
				            <li>
				                <a href="#">HIVE</a>
				            </li>
				            <li>
				                <a href="#">PIG</a>
				            </li>
				            <li>
				                <a href="#">Example</a>
				            </li>
				        </ul>
					</li>
					<li><a href="/blog/">OSGi</a></li>
					<li><a href="/blog/1@git@">Git</a></li>
					<li><a href="/blog/">Spring</a></li>
				</ul>
				
				<%}else if(type!= null && type.equalsIgnoreCase(BlogConstants.BOOK_TOPIC_DIR)){%>
				
				<ul class="container" id="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/book/1@Core Java@">Core Java</a>
							<ul class="sub-menu">
				            <li>
				                <a href="#">Collection</a>
				            </li>
				            <li>
				                <a href="#">Thread</a>
				            </li>
				            <li>
				                <a href="#">Miscellaneous</a>
				            </li>
				           
				        </ul>
					</li>
					<li><a href="/blog/">Data Structure</a></li>
					<li><a href="/blog/">Logical</a></li>
					<li><a href="/blog/">Design Pattern</a></li>
				</ul>
				
				
				<%}else if(type!= null && type.equalsIgnoreCase(BlogConstants.SCHOOL_DIR)){%>
				
				<ul class="container" id="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/school/1@USC@">USC</a>
							<ul class="sub-menu">
				            <li>
				                <a href="#">Computer Science</a>
				            </li>
				            <li>
				                <a href="#">Software Engineering</a>
				            </li>
				            <li>
				                <a href="#">Computer Engineering</a>
				            </li>
				           
				        </ul>
					</li>
					<li><a href="/blog/">UC-Irvine</a></li>
					<li><a href="/blog/">SJSU</a></li>
					<li><a href="/blog/">UCT</a></li>
				</ul>
				
				
				<%}%> 
			
				
			</nav>

			<br/><br/>
			<div id="apple">
			<form action="/blog/search" method="get" id="search">
  						<input name="key" type="text" size="40" placeholder="Search..." />
			    </form>
			</div>
			
		<!-- Home -->
			<div class="wrapper wrapper-style1 wrapper-first">
				<article class="container" id="top">
					<div >

						
						<div >
		
		<%--  <form:form action="/search/" method="post" modelAttribute="search">
					<form:input  path="key" />
					<input type="submit" value=" Search" />
		</form:form>  --%>

				<%-- <form action="/blog/search" method="post">
					<div>
						<textarea name="key" rows="3" cols="60"></textarea>
					</div>
					<div>
						<input type="submit" value="Search" />
					</div>
				</form> --%>
				
				
				
				<br/>
			
			<%if(request.getAttribute("searchResult") != null){
				
				String result = (String) request.getAttribute("searchResult");
				
				if(request.getAttribute("index") != null && !((Index) request.getAttribute("index")).getBullets().isEmpty()){%>
					
					<p style="font-family:arial;color:black;font-size:30px;"> Search result for:<%=result %> </p>
					
				<%} else{%>
					
					<p style="font-family:arial;color:red;font-size:30px;"> No result found for:<%=result %> </p>
				<%}
			
			
			}%>
				
			<%if(request.getAttribute("index") != null) {
			
				Index index = (Index) request.getAttribute("index");
				if(index.getBullets()!=null && !index.getBullets().isEmpty()){%>
					<div id="list8">
					<ul>
					<%
					Bullet bullet = null;
					for(int i =0; i<index.getBullets().size();i++){
						bullet = index.getBullets().get(i);
					%>
						<li><a href="/blog/id/<%=bullet.getId() %>">[<%= i+1 %>] <%=bullet.getName() %></a></li>
				  	<%}%>
					</ul>
					</div>
			<%}%>
		 <%}else if (request.getAttribute("topics") != null) { 
			 
			List<Topic> leagues = (List<Topic>) request.getAttribute("topics"); %>
		
		
			<% for (Iterator it = leagues.iterator(); it.hasNext(); ) {
				Topic topic  = (Topic) it.next();			
			%>

				<header>
					<h1><strong><%=topic.getTitle() %></strong></h1>
				</header>
				
				
				<p style="font-family:arial;color:black;font-size:10px;">
				
			    <% if(topic.getContent()!=null){
			    	
			    	String text = topic.getContent().getValue();
			    	
			    	String[] contents = text.split(BlogConstants.CODE_DELIMITER);
			    	
			    	for(String content : contents){
			    		
			    		if(content.trim().startsWith(BlogConstants.CODE_SNIPPET_START)){
			    			
			    			String[] codeSnippet = content.trim().split(BlogConstants.CODE_SNIPPET_START);%>
  			    		
			    			<pre class="brush: java;">
			    			
			    			<%for(int i=0;i<codeSnippet.length;i++){ %>
			    				<%=codeSnippet[i] %>
			    			<%} %>
			    			</pre>	
			    			
			    		<%}else{


							String[] lines = content.split(BlogConstants.TOPIC_DELIMITER);
							
							for(String line : lines){%>
								
								
							<%String[] tokens = line.split(BlogConstants.TOPIC_TAG_DELIMITER);

							String token0 = tokens[0];
							
							if(tokens.length>1){
								
								String token1 = tokens[1];
								
								if(token0.equalsIgnoreCase("H1")){%>
									<h1><%=token1 %></h1>
								<%}else if(token0.equalsIgnoreCase("H2")){%>
									<h2><%=token1 %></h2>
								<%}else if(token0.equalsIgnoreCase("H3")){%>
									<h3><%=token1 %></h3>
								<%}else if(token0.equalsIgnoreCase("H4")){%>
									<h4><%=token1 %></h4>
								<%}else if(token0.equalsIgnoreCase("DOWNLOAD_CODE")){%>
									<a href="<%=token1 %>" class="button button-big">
									<% if(tokens.length>2){%> <%=tokens[2] %><%} %></a>
								<%}else if(token0.equalsIgnoreCase("b")){%>
									<div style="font-family:arial;color:black;font-size:20px;"> <%=token1 %><br/></div>
								<%}else if(token0.equalsIgnoreCase("i")){%>
									<i><%=token1 %></i>
								<%}else if(token0.equalsIgnoreCase("p")){%>
									<p style="font-family:arial;color:black;font-size:20px;"><%=token1 %></p>
								<%}else if(token0.equalsIgnoreCase("a")){%>
									<a href="<%=token1 %>">
									<% if(tokens.length>2){%> <%=tokens[2] %><%} %></a>
								<%}else if(token0.equalsIgnoreCase("img")){%>
									<img src="<%=token1 %>">
								<%}else{%>
									<p style="font-family:arial;color:black;font-size:20px;"><%=token1 %></p>
								<%}%>
							<%}else{%>
								<%=token0 %>
							<%}%>
								
								
							<%}
						 
						
			    		}
			    		
			    	}
			    	
			    	
			    }
					%>
			  <%}%>
			</p>
	<%}%>
							
							
						</div>
					</div>
				</article>
			</div>

			
</body>
</html>
