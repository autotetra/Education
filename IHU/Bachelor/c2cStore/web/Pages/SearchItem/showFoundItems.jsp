<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Items Found</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
        <h1 ID="eshopHeader">Items Found:</h1>
        <table style="color: #FFFFFF;">
            <%  
                List<Storeitems> si = null;
                si=(List<Storeitems>)request.getSession().getAttribute("searchResultList");
                if(si!=null)
                {
                    out.print("<TR ALIGN='CENTER' style='color: #00FF00;'>");
                    out.print("<td style='border-bottom: 1px solid #ffffff;'>ItemID : </td>");
                    out.print("<td style='border-bottom: 1px solid #ffffff;'>Description : </td>");
                    out.print("<td style='border-bottom: 1px solid #ffffff;'>Price : </td>");
                    out.print("<td style='border-bottom: 1px solid #ffffff;'>Quantity : </td>");
                    out.print("<td style='border-bottom: 1px solid #ffffff;'>Categorie : </td>");
                    out.print("</TR>");
                    for(int i = 0; i<si.size();i++)
                    {
                        out.print("<tr ALIGN='CENTER'>");
                        
                        out.print("<td>"+si.get(i).getId()+ "</td>");
                        
                        out.print("<td>"+si.get(i).getDescription()+ "</td>");
                        
                        out.print("<td>"+si.get(i).getPrice()+ " $</td>");
                        
                        out.print("<td>"+si.get(i).getQuantity()+ "</td>"); 
                        
                        out.print("<td>"+si.get(i).getCategories().getCategoriename()+ "</td>"); 
                        out.print("<td><form action=\"Additem\" method=\"post\"> <input type=\"hidden\" value="+si.get(i).getId()+" name=\"itemid\" /> <input type=\"submit\" value=\"Add\" ID='redInputStyling'></form></td>"); 
                        out.print("</tr>");
                    }
               }
            %>
        </table>
        </DIV>
    </body>
</html>
