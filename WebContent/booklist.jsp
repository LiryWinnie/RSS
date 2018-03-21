<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.List" import="java.util.ArrayList"
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
   <%String q=request.getParameter("q");
     List<String> m=new ArrayList<String>();
     List<String> n=new ArrayList<String>();
     search sear=new search();
     m=sear.sea(q,"title");
     n=sear.sea(q,"isbn");%>
 <center>
   <div class="search">
     <form action="booklist.jsp" method="post">
       <a>根据图书的名称搜索：</a>
       <input type="text" name="q"/>
       <a href="booklist.jsp" onclick="link"><input type="submit" value="搜索"/></a>
     </form>
   </div>
   <%if(m.size()!=0){ %>
   <div class="book">
       <table border="1" cellspacing="0" align="center">
           <tr><th>图书名称</th><th>isbn</th></tr>
           <%
           System.out.print(m.size());
             for(int i=0;i<m.size();i++){
            	 
           %>
           <tr><td><%=m.get(i) %></td> <td><a href="result.jsp?isbn=<%=n.get(i) %>"><%=n.get(i) %></a></td> </tr>
           <%} %>
       </table>
   </div>
   <%} %>
   </center>
</body>
</html>