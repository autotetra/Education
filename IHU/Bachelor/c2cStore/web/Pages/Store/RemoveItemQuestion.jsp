<%-- 
    Document   : CreateStore
    Created on : 2 Δεκ 2014, 10:46:55 πμ
    Author     : Admin
--%>

<%@page import="hibernateModel.Categories"%>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page import="hibernateModel.Store"%>
<%@page import="hibernateModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>C2C store - Item Edit</title>
    </head>
    <body>
         <h1>
            <%  
               Store store=(Store)request.getSession().getAttribute("store");//.getAttribute("bean");  
               out.print(store.getStorename());
           %>
           <br> ARE YOU SURE YOU WANT TO REMOVE THE FOLLOWING ITEM?
       </h1>
       <table style="border: 4px blue ridge">
            <%  
               List<Storeitems> si = null;
               si=(List<Storeitems>)request.getSession().getAttribute("items");//.getAttribute("bean");
               out.print("Item : </br>");
               int i = Integer.parseInt((String)request.getSession().getAttribute("itemlistiteration"));
                        
                        out.print("<form action=\"RemoveItem\" method=\"post\">");
                        out.print("<tr style=\"border: 4px blue ridge\">");

                         out.print("<td style=\"border: 4px blue ridge\">ItemID : </td>");
                         out.print("<td style=\"border: 4px green inset\">"+si.get(i).getId()+ "</td>");
                         out.print("<input type=\"hidden\" value="+si.get(i).getId()+" name=\"remitemid\" />"); 
                         
                         out.print("<td style=\"border: 4px blue ridge\">Description : </td>");
                         out.print("<td style=\"border: 4px green inset\">"+si.get(i).getDescription()+ "</td>");

                         out.print("<td style=\"border: 4px blue ridge\">Price : </td>");
                         out.print("<td style=\"border: 4px green inset\">"+si.get(i).getPrice()+"</td>");

                         out.print("<td style=\"border: 4px blue ridge\">Quantity : </td>");
                         out.print("<td style=\"border: 4px green inset\">"+si.get(i).getQuantity()+"</td>");
                         
                         //out.print("<td> <select name=\"category\">");
                        // for(int j=0;j<cats.size();j++){
                            
                             //   out.print("<option value=\""+cats.get(j).getCategoriename()+"\">"+cats.get(j).getCategoriename()+"</option>");
                       // }
                         //out.print("<td style=\"border: 4px blue ridge\">Categorie : </td>");
                         //out.print("<td style=\"border: 4px green inset\"><input type=\"text\" name=\"newCat\" value="+si.get(i).getCategories().getCategoriename()+ "></td>");
                         
                        out.print("</tr>");
             %>
           <tr>
               <td><input type="submit" value="Yes"></form></td>
               <td><form action="CancelRemove" method="post"><input type="submit" value="No"></form></td>
               
           </tr>
        </table>
        
    </body>
</html>
