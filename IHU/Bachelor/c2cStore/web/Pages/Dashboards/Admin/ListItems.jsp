<%-- 
    Document   : ListItems
    Created on : Jan 3, 2015, 11:31:36 PM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List items</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h3>Here you can delete or modify store items</h3>
        
         <table border="1">
            <%  
               List<Storeitems> listItems = null;
               listItems=(List<Storeitems>)request.getSession().getAttribute("itemList");
                  out.print("<thead>");
                        out.print("<tr>");
                                    
                                    out.print("<th>ID </th>");
                                    out.print("<th>Description </th>");
                                    out.print("<th>Price </th>");
                                    out.print("<th>ITEMS </th>");
                                    out.print("<th>ITEMS quantity</th>");
                                    out.print("<th>Quantity  </th>");
                                    out.print("<th>Category </th>");
                                    out.print("<th>Store name </th>");
                                    out.print("<th>Owner</th>");
                                    out.print("<th>Update : </th>");
                                    out.print("<th>Delete : </th>");
                        out.print("</tr>");
                  out.print("</thead>");
               if(listItems!=null){
                    for(int i = 0; i<listItems.size();i++){

                     out.print("<tbody>");
                         out.print("<td>"+listItems.get(i).getId()+ "</td>");

                         
                         out.print("<td>"+listItems.get(i).getDescription()+ "</td>");

                         out.print("<td>"+listItems.get(i).getPrice()+ " $</td>");
                         
                         out.print("<td>"+listItems.get(i).getStore().getStoreitemses().iterator().next().getDescription()+ " $</td>");
                       
                         out.print("<td>"+listItems.get(i).getStore().getStoreitemses().toArray().length+ " </td>");

                         out.print("<td>"+listItems.get(i).getQuantity()+ "</td>");
                         
                         out.print("<td>"+listItems.get(i).getCategories().getCategoriename()+ "</td>");
                         
                         out.print("<td>"+listItems.get(i).getStore().getStorename()+ "</td>");
                         
                         out.print("<td>"+listItems.get(i).getStore().getUser().getName()+ "</td>");

                        out.print("<td>");
                                out.print("<form action=\"itemInfoAdmin\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+listItems.get(i).getId()+" name=\"itemId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td>");
                                out.print("<form action=\"DeleteItemAdmin\" method=\"post\">");
      /*inline Javascript */    out.print(" <input type=\"hidden\" value="+listItems.get(i).getId()+" name=\"itemId\" /> <input type=\"submit\" value=\"Delete\"  onclick=\"return confirm('Are you sure you want to delete this item???')\">");
                                out.print("</form>");
                         out.print( "</td>");
                        out.print("</tr>");
                    }
               }
               
 out.print("</tbody>");
           %>
        </table>
    </body>
</html>
