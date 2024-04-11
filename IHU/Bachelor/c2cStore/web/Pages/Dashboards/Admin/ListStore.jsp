<%-- 
    Document   : ListStore
    Created on : Jan 3, 2015, 3:40:33 PM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Store"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Stores</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h1>List Stores</h1>
         <table border="1">
        <%  
               List<Store> storeList = (List<Store>)request.getSession().getAttribute("storeList");
              
                   out.print("<thead>");
                             out.print("<tr>");
                                    out.print("<th>ID  </th>");
                                    out.print("<th>Store name  </th>");
                                    out.print("<th>Store items  </th>");
                                    out.print("<th>items quantity  </th>");
                                    out.print("<th>items tora  </th>");
                                    out.print("<th>Store owner  </th>");
                                    out.print("<th>City  </th>");
                                    out.print("<th>Update  </th>");
                                    out.print("<th>Delete  </th>");
                               out.print("</tr>");
                    out.print("</thead>");
               if(storeList!=null){
                    for(int i = 0; i<storeList.size();i++){
                            
            out.print("<tbody>");
                         
                         out.print("<td>"+storeList.get(i).getId()+ "</td>");

                         
                         out.print("<td>"+storeList.get(i).getStorename() + "</td>");
                        
                         out.print("<td>"+storeList.get(i).getStoreitemses().iterator().next().getDescription() + "</td>");
                        
                         out.print("<td>"+storeList.get(i).getStoreitemses().toArray().length+ "</td>");
                         
                         out.print("<td>"+storeList.get(i).getStoreitemses().iterator().next().getDescription()+ "</td>");
                         
                         out.print("<td>"+storeList.get(i).getUser().getLastname().toUpperCase() + "</td>");
                         
                         out.print("<td>"+storeList.get(i).getUser().getCity() + "</td>");

                          out.print("<td>");
                                out.print("<form action=\"StoreInfoAdmin\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+storeList.get(i).getId()+" name=\"storeId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td>");
                                out.print("<form action=\"DeleteStoreAdmin\" method=\"post\">");
      /*inline Javascript */    out.print(" <input type=\"hidden\" value="+storeList.get(i).getId()+" name=\"storeId\" /> <input type=\"submit\" value=\"Delete\"  onclick=\"return confirm('Are you sure you want to delete this store???')\">");
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
