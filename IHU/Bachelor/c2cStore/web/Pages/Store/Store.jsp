<%@page import="hibernateModel.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Store</title>
    </head>
    <body>
        <DIV ALIGN='CENTER'>
        <h1 ID="eshopHeader">
            <%  
                Store store=(Store)request.getSession().getAttribute("store"); 
                out.print(store.getStorename());
            %>
        </h1>
        <table>    
            <tr>
                <TD><form action="GotoHomepage" method="post"> <input type="submit" value="Home" ID="buttonInputStyling"></form></TD>
                <TD><form action="Items" method="post"> <td align="right">    <input type="submit" value="Items" ID="buttonInputStyling"> </form></td>  
                <TD><form action="Sales" method="post"> <td align="right">    <input type="submit" value="Sales" ID="buttonInputStyling"> </form></td>  
            </tr>
        </table>
        </DIV>
    </body>
</html>
