<%-- 
    Document   : ADMIN PANEL
    Created on : 12 Νοε 2014, 12:09:20 μμ
    Author     : Admin
--%>
 <!--<%@ include file="/Pages/Dashboards/Admin/menu.jsp" %> -->
<%@page import="hibernateModel.User"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMINISTRATION PANEL</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
       <link rel="stylesheet" href="myStyle.css">
        <link rel="stylesheet" href="Menu.css">
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
    
<h4 align="center">Custumer-to-custumer store!!!</h4>
<h1 align="center">ADMIN PANEL</h1>
   <h3>Kotagpas inconporation!!!</h3>
     
      <br>
      <label for="show-menu" class="show-menu">Show Menu</label>
	<input type="checkbox" id="show-menu" role="button">
		<ul id="menu">
		<li><a href="${pageContext.request.contextPath}">Home</a></li>
		
		<li>
			<a href="#">Manage users</a>
			<ul class="hidden">
				<li><a href="${pageContext.request.contextPath}/createUserAdmin">Add user</a></li>
				<li><a href="${pageContext.request.contextPath}/ListUsersAdmin">List All users</a></li>
			</ul>
		<li>
			<a href="#">Manage moderators</a>
			<ul class="hidden">
				<li><a href="${pageContext.request.contextPath}/ListModeratorsAdmin">List all moderators</a></li>
				
			</ul>
		</li>

	<li>
			<a href="#">Manage category</a>
			<ul class="hidden">
				<li><a href="${pageContext.request.contextPath}/ListCatAdmin">List all category</a></li>
				<li><a href="${pageContext.request.contextPath}/CreateCategoryAdmin">Add category</a></li>
			</ul>
        </li>

		<li>
			<a href="#">Manage store</a>
			<ul class="hidden">
				<li><a href="${pageContext.request.contextPath}/ListStoreAdmin">List store</a></li>
			</ul>
		</li>
		<li>
			<a href="#">Manage items</a>
			<ul class="hidden">
				<li><a href="${pageContext.request.contextPath}/ListItemsAdmin">List items</a></li>
				
			</ul>
		</li>
               </ul>
                <br>	
                <br>	
    </body>
</html>



 
  
 
