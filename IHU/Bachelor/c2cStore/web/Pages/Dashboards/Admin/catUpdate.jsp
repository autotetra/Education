<%-- 
    Document   : catUpdate
    Created on : Dec 27, 2014, 3:42:23 AM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hibernateModel.Categories"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category update</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h1>Category update</h1>
          <%
            
            Categories cat=(Categories)request.getSession().getAttribute("catById");   
            String catName= cat.getCategoriename();
            
          // Integer id=cat.getId();
           //String idString=Integer.toString(id);
         
         %> 
         <form action="UpdateCat" method="post">
         Category name:<input type="text" value="<% out.print(catName);%>" name="catName">
          <input type="hidden" name="catId" value="<% out.print(cat.getId()); %>">
         <input type="submit" value="Update">
         </form>
         <a href="${pageContext.request.contextPath}/ListCatAdmin">Back to category</a>
    </body>
</html>
