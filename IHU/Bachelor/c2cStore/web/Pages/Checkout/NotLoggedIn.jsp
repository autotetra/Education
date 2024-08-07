<%-- 
    Document   : NotLoggedIn
    Created on : Dec 10, 2014, 10:06:25 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
            <H3 STYLE="color: #0000FF;">Enter your credentials</H3>
        <form action ="CompleteOrder" method="post">
            <table style="color: #ffffff;">
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td><input type="text" name="surname" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td align="right"><input type="text" name="country" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td align="right"><input type="text" name="city" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td align="right"><input type="text" name="state" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td align="right"><input type="text" name="address" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td align="right"><input type="text" name="postalcode" required id="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td align="right"><input type="text" name="phone" required id="signUpInStyle"></td>
                </tr>
                <tr align="center">
                    <td colspan="2"><input type="submit" value="Complete Order" id="buttonInputStyling"></td>
                </tr>
            </table>
        </form>
        </DIV>
    </body>
</html>
