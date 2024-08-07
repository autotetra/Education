<%@page import="model.ItemViewModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
         <% 
             
           ItemViewModel ivm = (ItemViewModel)request.getSession().getAttribute("itemforview");
           out.print("<title>"+ivm.getItem().getDescription()+"</title>");
         
         %>
    
    </head>
    <body>
        <DIV ALIGN='RIGHT'>
        <form action="Cart" method="post"> <input type="submit" value="Cart" ID='buttonInputStyling'></form> 
        </DIV>
        <DIV ALIGN='CENTER' STYLE='color: #FFFFFF;'>
        <% 
           out.print("<h3>Item : </h3>"+ivm.getItem().getDescription());
           out.print("<br><h3>Price : </h3>"+ivm.getItem().getPrice()+"$");
           out.print("<br><h3>In stock : </h3>"+ivm.getItem().getQuantity());
           out.print("<br><h3>Items Category : </h3>"+ivm.getCat().getCategoriename());
           out.print("<br><h3>In Store : </h3>"+ivm.getStore().getStorename());
           out.print("<br><h3>Seller : </h3>"+ivm.getUser().getLastname()+" "+ivm.getUser().getName()+" ("+ivm.getUser().getUsername()+")");
           out.print("<br><h3>Seller's address : </h3>"+ivm.getUser().getCountry()+" ,"+ivm.getUser().getCity()+" , Postal Code :"+ivm.getUser().getPostalCode());
           out.print("<form action=\"Additem\" method=\"post\">");
           out.print("<input type=\"text\" name=\"quantity\" ID='signUpInStyle'>");
           out.print("<input type=\"hidden\" value="+ivm.getItem().getId()+" name=\"itemid\" /> <input type=\"submit\" value=\"Add to Cart\" ID='redInputStyling'></form>");
        %>
        </DIV>
        
    </body>
</html>
