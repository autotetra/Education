<%@page import="model.CartItemModel"%>
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
         <h1 ID="eshopHeader">
             Review Purchase
       </h1>
        <DIV ALIGN="CENTER" STYLE="color: #FFFFFF;">
       <table style="border: 4px blue ridge">
            <%  
               List<CartItemModel> si = null;
               si=(List<CartItemModel>)request.getSession().getAttribute("cartlist");//.getAttribute("bean");
               if(si!=null){
                   out.print("<TR ALIGN='CENTER'>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>ItemID : </td>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>Description : </td>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>Price : </td>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>Store Quantity : </td>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>Categorie : </td>");
                   out.print("<td style='border-bottom: 1px solid #ffffff;'>Order Quantity : </td>");
                   out.print("</TR>");
                    for(int i = 0; i<si.size();i++){

                        out.print("<tr ALIGN='CENTER'>");
                         out.print("<td>"+si.get(i).getSi().getId()+ "</td>");
                         out.print("<td>"+si.get(i).getSi().getDescription()+ "</td>");
                         out.print("<td>"+si.get(i).getSi().getPrice()+ " $</td>");
                         out.print("<td>"+si.get(i).getSi().getQuantity()+ "</td>");
                         out.print("<td>"+si.get(i).getSi().getCategories().getCategoriename()+ "</td>");
                         out.print("<td>"+si.get(i).getQua()+ "</td>");
                         
                        out.print("</tr>");
                    }
               }
               

           %>
        </table>
        <form action="Checkout" method="post"><input type="submit" value="Checkout" ID="redInputStyling"></form>
        </DIV>
    </body>
</html>
