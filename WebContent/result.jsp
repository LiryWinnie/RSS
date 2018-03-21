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
  <%String isbn = request.getParameter("isbn"); 
    search r=new search();
    bookbean book=new bookbean();
    String url="https://api.douban.com/v2/book/isbn/"+isbn;
    if(isbn!=null)
       book=r.bookinfo(url);
  %>
 <center>
   <div class="search">
     <form action="result.jsp" method="post">
       <a>根据图书的isbn搜索：</a>
       <input type="text" name="isbn"/>
       <a href="result.jsp" onclick="link"><input type="submit" value="搜索"/></a>
     </form>
   </div>
   </center>
   <%if(book.getTitle()!=null){ %>
   <div class="book">
       <img src="<%=book.getImg2() %>"/>
       <p>图书名称：<%=book.getTitle() %><p>
       <p>图书作者：<%=book.getAuthor() %><p>
       <p>图书价格：<%=book.getPrice() %><p>
       <p>出版社：<%=book.getPress() %><p>
       <p>出版时间：<%=book.getPubdate() %><p>
       <p>ISBN：<%=book.getIsbn() %><p>
       <p><a href="comment.jsp?id=<%=book.getId()%>&img=<%=book.getImg2()%>">查看图书评论</a></p>
      <br><p>图书简介：<%=book.getSummary() %></p>
      <br><br>
   </div>
   <%} %>
</body>
</html>