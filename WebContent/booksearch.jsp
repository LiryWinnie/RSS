<%@ page language="java" contentType="text/html; charset=utf-8" import="rss.FeedServlet" import="rss.bookbean"
    import="rss.search" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>RSS of Winnie</title>
<link href="Style.css" rel="stylesheet">
</head>
<body>
   <%@ include file="News.jsp" %>
 <center>
   <div class="search">
     <form action="booklist.jsp" method="post">
       <a>根据图书的名称搜索：</a>
       <input type="text" name="q"/>
       <a href="booklist.jsp" onclick="link"><input type="submit" value="搜索"/></a>
     </form>
   </div>
   </center>
</body>
</html>