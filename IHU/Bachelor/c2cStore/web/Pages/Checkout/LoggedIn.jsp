<%-- 
    Document   : LoggedIn
    Created on : Dec 10, 2014, 10:15:55 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Checkout</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
        <p style="color: #FF0000; font-size: 20px;">Send items to your address, 
           or you wish them to delivered to another address?</p>
        <form action="CompleteOrder" method="post"><input type="submit" value="To my address" ID="redInputStyling"></form>
        <form action="AnotherAddress" method="post"><input type="submit" value="To another address" ID="redInputStyling"></form>
        </DIV>
    </body>
</html>
