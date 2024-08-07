<%-- 
    Document   : StoreList
    Created on : Jan 5
    Author     : Sotsira
--%>
<%@ include file="/Pages/Dashboards/ModeratorDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Store"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODERATOR</title>
    </head>
    
    <body>        
        <br><br><br>
               
        <h1>List Stores</h1>
         <table border="1">
        <%  
               List<Store> StoreList = (List<Store>)request.getSession().getAttribute("StoreList");
               
                       out.print("<thead>");
                         out.print("<tr>");
                            out.print("<td style=\"border:  2px black ridge\"> Id </th>");
                            out.print("<th> Store Name </th>");
                            out.print("<th> Store Items </th>");
                            out.print("<th> Items Quantity </th>");
                            out.print("<th> Items Now </th>");
                            out.print("<th> Store Owner  </th>");
                            out.print("<th> City </th>");
                            out.print("<th> Update </th>");
                            out.print("<th> Delete </th>");
                         out.print("</tr>");
                       out.print("</thead>");
                       
               if(StoreList!=null){
                    for(int i = 0; i<StoreList.size();i++){
                        
                    out.print("<tbody>");                         
                         out.print("<td>"+StoreList.get(i).getId()+ "</td>");                         
                         out.print("<td>"+StoreList.get(i).getStorename() + "</td>");                        
                         out.print("<td>"+StoreList.get(i).getStoreitemses().iterator().next().getDescription() + "</td>");                        
                         out.print("<td>"+StoreList.get(i).getStoreitemses().toArray().length+ "</td>");                         
                         out.print("<td>"+StoreList.get(i).getStoreitemses().iterator().next().getDescription()+ "</td>");                         
                         out.print("<td>"+StoreList.get(i).getUser().getLastname().toUpperCase() + "</td>");                         
                         out.print("<td>"+StoreList.get(i).getUser().getCity() + "</td>");
                         
                         out.print("<td>");
                            out.print("<form action=\"StoreInfo\" method=\"post\">");
                            out.print(" <input type=\"hidden\" value="+StoreList.get(i).getId()+" name=\"storeId\" /> <input type=\"submit\" value=\"Update\">");
                            out.print("</form>");
                          out.print( "</td>");
                         out.print("<td>");
                         
                            out.print("<form action=\"DeleteStore\" method=\"post\">");
                            out.print(" <input type=\"hidden\" value="+StoreList.get(i).getId()+" name=\"storeId\" /> <input type=\"submit\" value=\"Delete\"  "
                                       + "onclick=\"return confirm('Delete this Store ! Are you shure  ?')\">");
                            out.print("</form>");
                         out.print( "</td>");
                         out.print("</tr>");
                         out.print("</tr>");                
                         
                          }       
                    }
               
             out.print("</tbody>");
           %>
        </table>
    </body>
</html>
