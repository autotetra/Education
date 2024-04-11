<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Settings</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
        <H1 ID="eshopHeader">User Settings</H1>
        </DIV>
        <DIV ALIGN='CENTER'>
       <table>
           <tbody>
               <tr>
                   <td><form action="EditProfile" method="post"><input type="submit" value="Edit Profile" ID='redInputStyling'></form></td>
                   <td><form action="DeleteProfile" method="post"><input type="submit" value="Delete Profile" ID="redInputStyling"></form></td>
               </tr>
           </tbody>
       </table>
        </DIV>
        <br /><br/>
    </body>
</html>
