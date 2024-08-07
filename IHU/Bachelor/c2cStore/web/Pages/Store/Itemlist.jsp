<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page import="hibernateModel.Store"%>
<%@page import="hibernateModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Create Store</title>
    </head>
    <body>
        <DIV ALIGN='CENTER'>
         <h1 ID='eshopHeader'>
            <%  
               Store store=(Store)request.getSession().getAttribute("store");//.getAttribute("bean");  
               out.print(store.getStorename());

           %>
       </h1>
       <form action="GotoHomepage" method="post"> <input type="submit" value="Home" ID='buttonInputStyling'></form>
       <table style='color: #FFFFFF;'>
            <%  
               List<Storeitems> si = null;
               si=(List<Storeitems>)request.getSession().getAttribute("items");
               out.print("<TR ALIGN='CENTER'>");
               out.print("<td>ItemID : </td>");
               out.print("<td>Description: </td>");
               out.print("<td>Price : </td>");
               out.print("<td>Quantity : </td>");
               out.print("<td>Categorie : </td>");
               out.print("</TR>");
               if(si!=null){
                    for(int i = 0; i<si.size();i++){

                        out.print("<tr ALIGN='CENTER'>");
                         out.print("<td>"+si.get(i).getId()+ "</td>");
                         out.print("<td>"+si.get(i).getDescription()+ "</td>");
                         out.print("<td>"+si.get(i).getPrice()+ " $</td>");
                        out.print("<td>"+si.get(i).getQuantity()+ "</td>");
                        out.print("<td>"+si.get(i).getCategories().getCategoriename()+ "</td>");
                        out.print("<td><form action=\"EditItem\" method=\"post\"><input type=\"hidden\" value="+i+" name=\"itemlistid\" /> <input type=\"submit\" value=\"Edit\" ID='redInputStyling'></form></td>");
                         out.print("<td><form action=\"RemoveItemQ\" method=\"post\"><input type=\"hidden\" value="+i+" name=\"idforemitem\" /> <input type=\"submit\" value=\"Remove\" ID='redInputStyling'></form></td>");
                         
                        out.print("</tr>");
                    }
               }
               

           %>
           <tr>
               <td COLSPAN='7' ALIGN='CENTER'><form action="AddItemConf" method="post"> <input type="submit" value="Add item" ID="buttonInputStyling"></td>
               
           </tr>
        </table>
        </DIV>
    </body>
</html>
