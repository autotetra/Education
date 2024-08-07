
<%@ include file="/Pages/Dashboards/ModeratorDashboard.jsp" %>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Categories</title>
    </head>
    <body>
        <h3>List Category</h3>
        <table>
        <%  
               List<Categories> ViewList = (List<Categories>)request.getSession().getAttribute("ViewList");
              
             ;
               
                out.print("<tr style=\"border: 2px black ridge\">");
                                    
                                     out.print("<td style=\"border:  2px black ridge\">ID : </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Category name  </td>");
                                    out.print("<td style=\"border:  2px black ridge\">Delete  </td>");
                            out.print("</tr>");
               if(ViewList!=null){
                    for(int i = 0; i<ViewList.size();i++){
                            

                         
                         out.print("<td style=\"border:  2px black ridge\">"+ViewList.get(i).getId()+ "</td>");

                         
                         out.print("<td style=\"border:  2px black ridge\">"+ViewList.get(i).getCategoriename()+ "</td>");

                         out.print("<td style=\"border:  2px black ridge\">");
                                out.print("<form action=\"DeleteCategory\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+ViewList.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Delete\">");
                                out.print("</form>");
                         
                         out.print("</tr>");
                       
                        
                         
                    }
               }
               

           %>
        </table>
    </body>
</html>
