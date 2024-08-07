<%@page import="hibernateModel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Edit Profile</title>
    </head>
    <body>
        <DIV ALIGN="CENTER">
            <H1 STYLE="color: #0000FF; text-shadow: 0px 0px 30px #FF0000; font-family: Palatino;">Edit your profile</H1>
         <% 
             
           User ur = (User)request.getSession().getAttribute("user");
           
         %>
        <table style="color: #FFFFFF;">
            <form action="SaveProfile" method="post">
                <tr>
                    <td>Name:</td>
                    <td align="right"><input type="text" name="name" value=<%out.print(ur.getName());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td align="right"><input type="text" name="surname" value=<%out.print(ur.getLastname());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>User Name:</td>
                    <td align="right"><input type="text" name="username" value=<%out.print(ur.getUsername());%>  required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td align="right"><input type="email" name="email"  value=<%out.print(ur.getEmail());%> required ID="signUpInStyle"></td> 
                </tr>
                <tr>
                    <td>Password:</td>
                    <td align="right"><input type="password" name="password" value=<%out.print(ur.getPassword());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><label>Security Question</label></td>
                    <td align="right">
                        <select name="question">
                            <option value="What is the name of your pet?">What is the name of your pet?</option>
                            <option value="What is the name of your city birth">What is the name of your city birth</option>
                            <option value="What was your first car?">What was your first car?</option>
                            <option value="You love programming?">You love programming?</option>
                      </select>
                    </td>
                </tr>
                <tr>
                    <td>Answer:</td>
                    <td align="right"><input type="text" name="securityanswer" value=<%out.print(ur.getSecretA());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td align="right"><input type="text" name="country" value=<%out.print(ur.getCountry());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td align="right"><input type="text" name="city" value=<%out.print(ur.getCity());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td align="right"><input type="text" name="state" value=<%out.print(ur.getState());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td align="right"><input type="text" name="address" value=<%out.print(ur.getAddress());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td align="right"><input type="text" name="postalcode" value=<%out.print(ur.getPostalCode());%> required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td align="right"><input type="text" name="phone" value=<%out.print(ur.getPhone());%> required ID="signUpInStyle"></td>
                </tr>
                <tr align="center">
                    <td colspan="2"><input type="submit" value="Save Changes" ID="buttonInputStyling"></td>
                </tr>
            </form>
        </table>
        </DIV>
    </body>
</html>
