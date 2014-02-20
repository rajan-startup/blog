<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.blog.entity.Topic"%>
<%@page import="com.blog.constant.BlogConstants"%>
<%@page import="com.google.appengine.api.datastore.Text"%>

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
				width: 100px;
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
		
		</style>
	
</head>
<body>
			<nav id="nav">
				<ul class="container" id="menu">
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
					<li><a href="/blog/">Git</a></li>
					<li><a href="/blog/">Spring</a></li>
				</ul>
			</nav>

		<!-- Home -->
			<div class="wrapper wrapper-style1 wrapper-first">
				<article class="container" id="top">
					<div >
						
						<div >
							
							
							<%if (request.getAttribute("topics") != null) { 
		List<Topic> leagues = (List<Topic>) request.getAttribute("topics"); %>
		
		
			<% for (Iterator it = leagues.iterator(); it.hasNext(); ) {
				Topic topic  = (Topic) it.next();			
			%>
				
				<header>
					<h1><strong><%=topic.getTitle() %></strong></h1>
				</header>
				<p style="font-family:arial;color:black;font-size:20px;">
				
			    <% if(topic.getContent()!=null){

						String content = topic.getContent().getValue();
						
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
							<%}else if(token0.equalsIgnoreCase("b")){%>
								<%=token1 %><br/>
							<%}else if(token0.equalsIgnoreCase("i")){%>
								<i><%=token1 %></i>
							<%}else if(token0.equalsIgnoreCase("p")){%>
								<p><%=token1 %></p>
							<%}else if(token0.equalsIgnoreCase("a")){%>
								<a href="<%=token1 %>">
								<% if(tokens.length>2){%> <%=tokens[2] %><%} %></a>
							<%}else if(token0.equalsIgnoreCase("img")){%>
								<img src="<%=token1 %>">
							<%}else{%>
								<%=token1 %>
							<%}%>
						<%}else{%>
							<%=token0 %>
						<%}%>
							
							
						<%}
					 
					}
					%>
			  <%}
	}%>
							
							</p>
							<a href="#work" class="button button-big">Download Sample Project to start hands on it.</a>
						</div>
					</div>
				</article>
			</div>

			
</body>
</html>
