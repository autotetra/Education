<%-- 
    Document   : StoreUpdated
    Created on : Jan 9, 2015, 7:12:55 AM
    Author     : Sotsira
--%>
<%@page import="hibernateModel.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            
            Store item=(Store)request.getSession().getAttribute("userById");   
           String name= item.getStorename(); 
           Integer surname =item.getId();
           String username= item.getUser().getName();             
           String email= item.getStoreitemses().iterator().next().getDescription();             
           //Integer id=user.getId();
         //  String idString=Integer.toString(id);
         
         %> 
         
         <form>
             <input type="text" value="<% out.print(name);%>"
         </form>
         
      <div align="right">
          <a href="${pageContext.request.contextPath}/Logout">Logout</a>
      </div>
    </body>
</html>
