<%-- 
    Document   : DeleteStore
    Created on : Jan 6, 2015, 11:03:02 PM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page import="java.lang.Integer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Store</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h1>Store deleted!!!</h1>
        <%
          Integer id = (Integer)request.getSession().getAttribute("storeID");   
         out.print(id);
        %>   
    </body>
</html>
