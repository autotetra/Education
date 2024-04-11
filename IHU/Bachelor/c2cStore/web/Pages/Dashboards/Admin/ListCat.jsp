<%-- 
    Document   : ListCat
    Created on : Dec 21, 2014, 2:05:47 AM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Categories</title>
        <link  type="text/css" rel="stylesheet" href="/myStyle.css">
    </head>
    <body>
        <br>
        <br>
        <br>
        <h3>Here you can delete or modify<br>Category</h3>
        <table id="CategoryTable" border="1">
        <%  
               List<Categories> catList = (List<Categories>)request.getSession().getAttribute("catList");
              
             
            out.print("<thead>");   
                out.print("<tr>");
                
                                    
                                    // out.print("<td>ID : </td>");
                                     out.print("<th>Category name  </th>");
                                     out.print("<th>Update  </th>");
                                     out.print("<th>Delete  </th>");
                 
               out.print("</tr>");
           out.print("</thead>");
               if(catList!=null){
                    for(int i = 0; i<catList.size();i++){
                   
                    out.print("<tbody>");     
                         
                         //out.print("<td>"+catList.get(i).getId()+ "</td>");

                         
                         out.print("<td>"+catList.get(i).getCategoriename()+ "</td>");

                         out.print("<td>");
                                out.print("<form action=\"catInfoAdmin\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+catList.get(i).getId()+" name=\"catId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td>");
    /*===inline javaScript */  out.print("<form action=\"DeleteCategoryAdmin\" method=\"post\" onclick=\"return confirm('Are you sure to delete this category???')\">");
                                out.print(" <input type=\"hidden\" value="+catList.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Delete\">");
                                out.print("</form>");
                                
                         out.print("</td>");
                         out.print("</tr>");
                       
                        out.print("</tbody>");  
                         
                    }
               }
               

           %>
        </table>
    </body>
</html>
