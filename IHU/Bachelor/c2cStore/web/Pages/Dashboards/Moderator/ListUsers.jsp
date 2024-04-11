<%-- 
    Document   : StoreList
    Created on : Jan 5
    Author     : Sotsira
--%>
<%@include file="/Pages/Dashboards/ModeratorDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODERATOR</title>
    </head>
    <body>
        <h1><b>List all Users</b></h1>
  <table>
        <%  
               List<User> ul = (List<User>)request.getSession().getAttribute("modUserList");
              
               out.print(" List all users: </br>");
               
                out.print("<tr style=\"border: 2px black ridge\">");
                                    out.print("<td style=\"border:  2px black ridge\">UserID : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Name : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Surname : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Username : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">City : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Role : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Email : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Phone : </td>");
                                     out.print("<td style=\"border:  2px black ridge\">Update : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Delete : </td>");
                            out.print("</tr>");
               if(ul!=null){
                    for(int i = 0; i<ul.size();i++){
                            

                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getId()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getName()+ "</td>");
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getLastname()+ " </td>");
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getUsername()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getCity()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getRank()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getEmail()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">"+ul.get(i).getPhone()+ "</td>");                         
                         out.print("<td style=\"border:  2px black ridge\">");
                                out.print("<form action=\"userInfo\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+ul.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td style=\"border:  2px black ridge\">");
                                out.print("<form action=\"DeleteUser\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+ul.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Delete\"  onclick=\"return confirm('Are you sure you want to delete this User  ?')\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("</tr>");
                       
                        
                         
                    }
               }
               

           %>
        </table>
    </body>
</html>
