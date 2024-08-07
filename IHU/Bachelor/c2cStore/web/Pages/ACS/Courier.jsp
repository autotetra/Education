<%@page import="hibernateModel.Orders"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>ACS</title>
    </head>
    <body>
        <DIV ALIGN="RIGHT">
            <form action="Logout"><input type="submit" value="Logout" ID="redInputStyling"></FORM>
        </DIV>
        <DIV ALIGN="CENTER" STYLE="color: #FFFFFF;">
            <TABLE>
                <TR>
                    <form action="SearchOrders" method="POST">
            Enter Order Code<input type="text" name="ordercode" ID="inputStyling"><input type="submit" value="Search" ID="buttonInputStyling">
        </form>
                </TR>
            </TABLE>
        </form>
        <%
            List<Orders> orders = null;
            orders = (List<Orders>) request.getSession().getAttribute("allOrders");
            out.print("<table>");
            out.print("<tr ALIGN='CENTER'>");
            out.print("<td style='border-bottom: 1px solid #FFFFFF;'>Order Code </td>");
            out.print("<td style='border-bottom: 1px solid #FFFFFF;'>User</td>");
            out.print("<td style='border-bottom: 1px solid #FFFFFF;'>Delivered</td>");
            out.print("</tr>");
            for (int i = 0; i < orders.size(); i++) {
                out.print("<tr ALIGN='CENTER'>");
                out.print("<td>" + orders.get(i).getOrderCode() + "</td>");
                out.print("<td>" + orders.get(i).getUserId() + "</td>");
                out.print("<td style='color: #FF0000;'>" + orders.get(i).isDelivered() + "</td>");
                out.print("</tr>");
                out.print("<br />");
            }
            out.print("</table>");
        %>
        </DIV>
    </body>
</html>