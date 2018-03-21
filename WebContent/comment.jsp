<%@ page language="java" contentType="text/html; charset=utf-8" import="rss.CommentServlet" import="rss.bookbean"
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
   <%String id=request.getParameter("id");
     String img=request.getParameter("img");
     CommentServlet x=new CommentServlet();%>
    <br> 
    <div class="img">
    <img src="<%=img%>">
    </div>
     <div class="RssInfo">
   <table border="1" width=500px cellspacing=0>
   <tr>
   <th>频道标题</th>
   <td><%=x.test1(id).getTitleInfo() %></td></tr>        
   <tr>
   <th>频道Link信息</th>
   <td><a href="<%=x.test1(id).getLinkInfo() %>"><%=x.test1(id).getLinkInfo() %></a></td></tr>
   <tr>
   <th>频道描述信息</th>
   <td><%=x.test1(id).getDescriptionInfo() %></td></tr>
   <tr>
   <th>频道使用的语言</th>
   <td><%=x.test1(id).getLanguageInfo() %></td></tr>
   <tr>
   <th>频道版权信息</th>
   <td><%=x.test1(id).getCopyrightInfo() %></td></tr>
   <tr>
   <th>频道最新发布时间</th>
   <td><%=x.test1(id).getPubDate() %></td></tr>
   </table>
   </div>     
   <div class="items">
       <%int a=x.test3(id); %>
       <a>一共有</a><%=a %><a>个摘要在我的频道中</a>
       <p>标题：</p>
       <ul>
       <%for(int i=0;i<a;i++){ %>
          <li>&emsp;&emsp;<%=x.test2(i,id).getItemTitle() %></li>
            <ul id="item">
            <li><%=x.test2(i,id).getItemDescription() %></li>
            <li><a href="<%=x.test2(i,id).getItemLink() %>"><%=x.test2(i,id).getItemLink() %></a></li>
            <li><%=x.test2(i,id).getItemPubDate() %></li>
            </ul><br><br>
       <%} %>
       </ul>        
   </div>

</body>
</html>