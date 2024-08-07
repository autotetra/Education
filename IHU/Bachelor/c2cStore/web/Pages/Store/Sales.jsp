<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page import="hibernateModel.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%  
                Store store=(Store)request.getSession().getAttribute("store"); 
                out.print("<H1 ID='eshopHeader'>"+store.getStorename()+"</H1>");
                
                
        %>
        <DIV align="center" style="color: #FFFFFF;">
        <h3 STYLE="color: #FF0000; text-shadow: 0px 0px 5px #888888;">Sales of <%out.print(store.getStorename());%></h3>
        <table>
        <%
                List<Storeitems> si =(List<Storeitems>)request.getSession().getAttribute("itemssoldinthestore");
               out.print("Items Sold : </br>");
               if(si!=null){
                   out.print("<TR ALIGN='CENTER'>");
                   out.print("<td style='color: #00FF00;border-bottom: 1px solid #FFFFFF;'>ItemID : </td>");
                   out.print("<td style='color: #00FF00;border-bottom: 1px solid #FFFFFF;'>Description : </td>");
                   out.print("<td style='color: #00FF00;border-bottom: 1px solid #FFFFFF;'>Price : </td>");
                   out.print("<td style='color: #00FF00;border-bottom: 1px solid #FFFFFF;'>Categorie : </td>");
                   out.print("</TR>");
                    for(int i = 0; i<si.size();i++){

                        out.print("<tr ALIGN='CENTER'>");
                         out.print("<td>"+si.get(i).getId()+ "</td>");
                         out.print("<td>"+si.get(i).getDescription()+ "</td>");
                         out.print("<td>"+si.get(i).getPrice()+ " $</td>");
                         out.print("<td>"+si.get(i).getCategories().getCategoriename()+ "</td>");
                    
                        out.print("</tr'>");
                    }
               }
        %> 
        </table>
        </div>
    </body>
</html>
