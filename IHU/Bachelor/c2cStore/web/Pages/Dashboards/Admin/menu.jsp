<%-- 
    Document   : menu
    Created on : Jan 4, 2015, 6:14:18 PM
    Author     : Denis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MENU</title>
    </head>
    <body>
          <label for="show-menu" class="show-menu">Show Menu</label>
	<input type="checkbox" id="show-menu" role="button">
		<ul id="menu">
		<li><a href="#">Home</a></li>
		
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
    </body>
</html>
