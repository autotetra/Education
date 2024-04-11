<%-- 
    Document   : ListUser
    Created on : Dec 12, 2014, 2:07:07 PM
    Author     : Denis
--%>

<%@page import="java.util.List"%>
<%@page import="hibernateModel.User"%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All registered users</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <h3>Hered you can delete or modify user</h3>
  <table>
        <%  
               List<User> ul = (List<User>)request.getSession().getAttribute("adminUserList");
                   out.print("<thead>");
                          out.print("<tr>");
                             //   out.print("<th>UserID</th>");
                                    out.print("<th>Name</th>");
                                    out.print("<th>Surname</th>");
                                    out.print("<th>Username </th>");
                                    out.print("<th>City </th>");
                                    out.print("<th>Role </th>");
                                    out.print("<th>Email </th>");
                                    out.print("<th>Phone </th>");
                                     out.print("<th>Update </th>");
                                    out.print("<th>Delete </th>");
                            out.print("</tr>");
                    out.print("</thead>");
               if(ul!=null){
                    for(int i = 0; i<ul.size();i++){
                            
               out.print("<tbody>"); 
                         
                     //    out.print("<td>"+ul.get(i).getId()+ "</td>");

                         
                         out.print("<td>"+ul.get(i).getName()+ "</td>");

                         out.print("<td>"+ul.get(i).getLastname()+ " </td>");

                         out.print("<td>"+ul.get(i).getUsername()+ "</td>");
                         
                         out.print("<td>"+ul.get(i).getCity()+ "</td>");
                         
                         out.print("<td>"+ul.get(i).getRank()+ "</td>");
                         
                         out.print("<td>"+ul.get(i).getEmail()+ "</td>");
                         
                         out.print("<td>"+ul.get(i).getPhone()+ "</td>");
                         
                         
                         out.print("<td>");
                                out.print("<form action=\"userInfoAdmin\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+ul.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td>");
                                out.print("<form action=\"DeleteUserAdmin\" method=\"post\">");
      /*inline Javascript */    out.print(" <input type=\"hidden\" value="+ul.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Delete\" onclick=\"return confirm('Are you sure you want to delete this user???')\">");
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
