<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Sign Up</title>
        <STYLE>/* Smartphones (portrait and landscape) ----------- */
	@media only screen
	and (min-device-width : 320px)
	and (max-device-width : 480px) {
		body { 
			padding: 0; 
			margin: 0; 
			width: 320px; }
		}
	
	/* iPads (portrait and landscape) ----------- */
	@media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {
		body { 
			width: auto;
                        height: auto;
		}
	}
	
        </STYLE>
    </head>
    <body>
        <DIV ALIGN="CENTER">
            <H1 STYLE="color: #0000FF; text-shadow: 0px 0px 30px #FF0000; font-family: Palatino;">Enter your info</H1>
        <table STYLE="color: #FFFFFF;">
            <form action="RegisterValidate" method="post">
                <tr>
                    <td>Name:</td>
                    <td align="right"><input type="text" name="name" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td align="right"><input type="text" name="surname" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>User Name:</td>
                    <td align="right"><input type="text" name="username" placeholder="5-15 Characters" required ID="signUpInStyle"></td>
                    <td><label for="male" style="color: #CC0000">Username Exist</label></TD>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td align="right"><input type="email" name="email"  placeholder="Enter a valid email" required ID="signUpInStyle"></td> 
                </tr>
                <tr>
                    <td>Password:</td>
                    <td align="right"><input type="password" name="password" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td align="right"><input type="password" name="confirmpassword" required ID="signUpInStyle"></td>
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
                    <td align="right"><input type="text" name="securityanswer" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td align="right"><input type="text" name="country" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td align="right"><input type="text" name="city" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td align="right"><input type="text" name="state" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td align="right"><input type="text" name="address" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td align="right"><input type="text" name="postalcode" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td align="right"><input type="text" name="phone" required ID="signUpInStyle"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register" ID="buttonInputStyling"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
