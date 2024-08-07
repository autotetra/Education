
<%@page import="hibernateModel.User"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODERATOR CONTROL PANEL</title>
    </head>
    <body>
        <div align="right">
    <%  
            User user=(User)request.getSession().getAttribute("user");  
            out.print("Welcome, "+user.getName());
             session.setAttribute("user", user);
            //String sessionUser= session.getAttribute(user);
    %>
    <br>
    <a href="${pageContext.request.contextPath}/Logout">Logout</a>
</div>
     
<h1 align="center">Moderator Panel</h1>
       
      

      <h1>User Mange</h1>
      <a href="${pageContext.request.contextPath}/ListUsers"><>List All Users</a> <br>
      <a href="${pageContext.request.contextPath}/CreateUser"><>Create User</a>  <br>      
      <h1>Category Mange</h1>
      <a href="${pageContext.request.contextPath}/ViewCategory"><>View All Categories</a> <br>
      <a href="${pageContext.request.contextPath}/CreateCategory"><>Create Category</a> </br>
      <a href="${pageContext.request.contextPath}/StoreList"><>List the Store</a>
      <!--h1>Order Mange</h1>
      <a href="${pageContext.request.contextPath}/SeeAllOrders"><>See All Orders</a-->
      <br>
    </body>
</html>



 
  
 
