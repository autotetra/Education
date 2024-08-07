<%-- 
    Document   : update
    Created on : Dec 15, 2014, 12:19:19 AM
    Author     : Denis
--%>
<%@ include file="/Pages/Dashboards/Admin/menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hibernateModel.User"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Menu.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <br>
        <br>
        <br>
       <h1>User update</h1>
        <%
            
            User user=(User)request.getSession().getAttribute("userById");   
           String name= user.getName(); 
           String surname =user.getLastname();
           String username= user.getUsername();             
           String email= user.getEmail();             
           String pass= user.getPassword();             
           String answer=user.getSecretA();
           String country=user.getCountry();
           String city=user.getCity();
           String state=user.getState();
           String address=user.getAddress();
           String postCode=user.getPostalCode();
           String phone=user.getPhone();
           //Integer id=user.getId();
         //  String idString=Integer.toString(id);
         
         %>   
      <div align="right">
          <a href="${pageContext.request.contextPath}/Logout">Logout</a>
      </div>
       
        
        
         <table>
            <form action="UpdateAdmin" method="post">
              <tr>
                    <td>Name:</td>
                    <td align="right"><input type="text" name="name" value="<% out.print(name);  %>"></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td align="right"><input type="text" name="surname"  value="<% out.print(surname);  %>"></td>
                </tr>
                <tr>
                    <td>User Name:</td>
                    <td align="right"><input type="text" name="username" placeholder="5-15 Characters"  value="<% out.print(username);%>"</td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td align="right"><input type="email" name="email"  placeholder="Enter a valid email"  value="<% out.print(email);%>"</td> 
                </tr>
                <tr>
                    <td>Password:</td>
                    <td align="right"><input type="password" name="password"  value="<% out.print(pass);%>"</td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td align="right"><input type="password" name="confirmpassword" value="<% out.print(pass);%>"</td>
                </tr>
                 <td>Role:</td>
                    <td align="right"><select name="rank">
                            <option value="user">User</option>
                            <option value="moderator">Moderator</option>
                       
                            
                      </select></td>
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
                    <td align="right"><input type="text" name="securityanswer" value="<% out.print(answer);%>"</td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td align="right"><input type="text" name="country" value="<% out.print(country);%>"</td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td align="right"><input type="text" name="city" value="<% out.print(country);%>"</td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td align="right"><input type="text" name="state" value="<% out.print(state);%>"</td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td align="right"><input type="text" name="address" value="<% out.print(address);%>"</td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td align="right"><input type="text" name="postalcode" value="<% out.print(postCode);%>"</td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td align="right"><input type="text" name="phone" value="<% out.print(phone);%>"</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right"><input type="submit" value="Update">
                       <input type="hidden" name="userId" value="<% out.print(user.getId()); %>"> 
                    </td>
                </tr>
            </form>
        </table>
        
     <a href="${pageContext.request.contextPath}/ListUsersAdmin">Back to list</a>           
    </body>
</html>
