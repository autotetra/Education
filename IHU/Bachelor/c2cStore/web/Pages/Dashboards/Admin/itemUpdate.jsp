<%-- 
    Document   : itemUpdate
    Created on : Jan 6, 2015, 5:47:57 PM
    Author     : Denis
--%>
<%@page import="model.ItemViewModel"%>
<%@page import="hibernateModel.Store"%>
<%@page import="hibernateModel.Categories"%>
<%@page import="hibernateModel.Storeitems"%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item update</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h1>Item update</h1>
          <%
            ItemViewModel ivm = (ItemViewModel)request.getSession().getAttribute("itemforview");
            out.print("<title>"+ivm.getItem().getDescription()+"</title>");
            Integer id=ivm.getItem().getId();
            String  catName=ivm.getItem().getCategories().getCategoriename();
            Integer quantity=ivm.getItem().getQuantity();
            String itemPrice= ivm.getItem().getPrice();
            String itemDescription=ivm.getItem().getDescription();
            String  storeName=ivm.getStore().getStorename();
            String  owner=ivm.getUser().getName();
            String  desc=ivm.getStore().getStoreitemses().iterator().next().getDescription();
         %> 
          <form action="UpdateItem" method="post">
      <table>
    <tr>
    <td>Store owner</td>
    <td><input type="text" value="<% out.print(owner);%>" name="storeOwner" readonly="1"></td>
    </tr>
    <tr>
    <td>Item id:</td>    
    <td><input type="text" value="<% out.print(id);%>" name="itemId"> </td>
    </tr>
    <tr>
    <td>Price</td>
    <td><input type="text" value="<% out.print(itemPrice);%>" name="price" readonly="1"></td>
    </tr>
    <tr>
    <td>Description</td>
    <td><input type="text" value="<% out.print(itemDescription);%>" name="description"></td>
    </tr>
    <tr>
    <td>Category</td>
    <td><input type="text" value="<% out.print(catName);%>" name="catName"></td>
    </tr>
    <tr>
    <td>Store name</td>
    <td><input type="text" value="<% out.print(storeName);%>" name="storeName"></td>
    </tr>
    <tr>
    <td>Quantity:</td>
    <td><input type="text" value="<% out.print(quantity);%>" name="quantity" readonly="1"></td>
    </tr>
    
</table> 
               
    <input type="hidden" name="itemId" value="<% out.print(id);%>">
         <input type="submit" value="UDATE">
         </form>
         <a href="${pageContext.request.contextPath}/ListItemsAdmin">Back to items</a>
    </body>
</html>
