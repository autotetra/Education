<%@page import="hibernateModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Pass Recovery</title>
    </head>
    <body>
        <DIV ALIGN="CENTER" STYLE="color: #FFFFFF;">
            <H1 STYLE="color: #FF0000;">Answer the question</H1>
        <table>
            <form action="passwordRecoveryProccess" method="post">
           
                <tr>
                    <td>     
                        <% 
             
                             User ur = (User)request.getSession().getAttribute("userwhoforgotpass");
                             out.print(ur.getSecretQ());
                         %>
         
                    </td>
                    <td><input type="text" name="secretquestion" ID="signUpInStyle"></td>
                </tr>
                <tr ALIGN="CENTER">
                    <td COLSPAN="2"><input type="submit" value="Recover Password" ID="buttonInputStyling"></td>
                </tr>
            </form>
        </table>
        </DIV>
    </body>
</html>
