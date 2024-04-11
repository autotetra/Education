<%-- 
    Document   : AddItem
    Created on : 5 Δεκ 2014, 11:35:06 πμ
    Author     : Admin
--%>

<%@page import="hibernateModel.Categories"%>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Items</title>
    </head>
    <body>
        <h1><tr>
                <%  
                    Store store=(Store)request.getSession().getAttribute("store");//.getAttribute("bean");  
                    out.print(store.getStorename());
                    List<Categories> cats = (List<Categories>)request.getSession().getAttribute("categories");
                %>
            </tr>
        </h1>
        <table>
            <form action="AddItem" method="post">
            <tr>
                <td align="right">Item Description :</td>
                <td><input type="text" name="desc" placeholder="5-50 Characters" required></td>
            </tr>
            <tr>
                <td align="right">Item Category :</td>
                <td> <select name="category">
                        <%
                            for(int i=0;i<cats.size();i++){
                            
                                out.print("<option value=\""+cats.get(i).getCategoriename()+"\">"+cats.get(i).getCategoriename()+"</option>");
                                
                            }
                        %>
                            
                            
                      </select></td>
            </tr>
            <tr>
                <td align="right">Quantity :</td>
                <td><input type="text" name="qua" required></td>
            </tr>
            <tr>
                <td align="right">Price($) :</td>
                <td><input type="text" name="price" required></td>
            </tr>
            <tr>
                <td align="right">    <input type="submit" value="Add Item"> </form></td>  
            </tr>
        </table>
    </body>
</html>
