<%-- 
    Document   : DeleteProfile
    Created on : Jan 9, 2015, 6:07:48 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/Pages/Settings/Settings.jsp" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Profile</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
        <p STYLE="color: #FF0000; font-size: 30px;">Are you sure you want to delete your profile?</p>
        <TABLE>
            <TR>
                <TD><form action="DeleteProfileSure"><input type="submit" value="Yes" ID="greenTransBox"/></form></TD>
                <TD><form action="Settings"><input type="submit" value="No" ID="redTransBox"/></form></TD>
            </TR>
        </TABLE>
        </DIV>
    </body>
</html>
