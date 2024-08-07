<%-- 
    Document   : CreateStore
    Created on : 2 Δεκ 2014, 10:46:55 πμ
    Author     : Admin
--%>

<%@page import="hibernateModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Store</title>
    </head>
    <body>
         <%  
            User user=(User)request.getSession().getAttribute("user");//.getAttribute("bean");  
            out.print("Dear, "+user.getName());
            
        %>
        <h1>You have no Store, fill the form bellow to create one.</h1>
        <table>
            <form action="CreateStore" method="post">
            <tr>
                <td align="right">Store name :</td>
                <td><input type="text" name="storename"></td>
            </tr>
            <tr>
                <td align="right">Logo :</td>
                <td><input type="text" name="picpath"></td>
            </tr>
            <tr>
                <td align="right">    <input type="submit" value="Create Store"> </form></td>  
            </tr>
        </table>
    </body>
</html>
