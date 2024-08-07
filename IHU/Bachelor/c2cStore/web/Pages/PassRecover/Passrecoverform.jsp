<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Pass Recovery</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
            <H1 STYLE="color: #FF0000;">Enter your username</H1>
        <table STYLE="color: #FFFFFF;">
            <form action="getUserForPassRec" method="post">
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" ID="signUpInStyle"></td>
                </tr>
                <tr align="CENTER">
                    <td COLSPAN="2"><input type="submit" value="Recover Password" ID="buttonInputStyling"></td>
                </tr>
            </form>
        </table>
        </DIV>
    </body>
</html>
