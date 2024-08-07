<%@page import="java.util.List"%>
<%@page import="hibernateModel.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>ACS</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
        <%
            List<Orders> order = null;
            order = (List<Orders>) request.getSession().getAttribute("order");
            out.print("<table style='color: #ffffff;'>");
            out.print("<tr align='center'>");
            out.print("<td style='color: #0000FF;border-bottom: 1px solid #ffffff;'>Order Code </td>");
            out.print("<td style='color: #0000FF;border-bottom: 1px solid #ffffff;'>User</td>");
            out.print("<td style='color: #0000FF;border-bottom: 1px solid #ffffff;'>Delivered</td>");
            out.print("</tr>");
            for (int i = 0; i < order.size(); i++) {
                out.print("<tr align='center'>");
                out.print("<td>" + order.get(i).getOrderCode() + "</td>");
                out.print("<td>" + order.get(i).getUserId() + "</td>");
                out.print("<td>" + order.get(i).isDelivered() + "</td>");
                out.print("</tr>");
            }
            out.print("</table>");
        %>
        <TABLE>
            <TR>
                
                <TD><form action="CloseOrder" method="POST"><input type="submit" value="Close Order" id='redInputStyling'>
                    </form></TD>
                    <TD><form action="Back"><input type="submit" value="Home" id='redInputStyling'>
                        </form></TD>
            </TR>
        </TABLE>
        </DIV>
    </body>
</html>
