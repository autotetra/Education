<%-- 
    Document   : ListModerator
    Created on : Dec 12, 2014, 3:03:14 PM
    Author     : Denis
--%>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.User"%>
<%@ include file="/Pages/Dashboards/SuperUserDashboard.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  type="text/css" rel="stylesheet" href="/myStyle.css">
       
        <title>Mod list page</title>
        
         <!--[if !IE]><!-->
	<style>
	
	/* 
	Max width before this PARTICULAR table gets nasty
	This query will take effect for any screen smaller than 760px
	and also iPads specifically.
	*/
	@media 
	only screen and (max-width: 760px),
	(min-device-width: 768px) and (max-device-width: 1024px)  {
	
		/* Force table to not be like tables anymore */
		table, thead, tbody, th, td, tr { 
			display: block; 
		}
		
		/* Hide table headers (but not display: none;, for accessibility) */
		thead tr { 
			position: absolute;
                        top: -9999px;
			left: -9999px;
		}
		
		tr { border: 1px solid #CC0000 ; 
                 width:auto;
                }
		
		td { 
			/* Behave  like a "row" */
			border:  chartreuse;
                         background-color: #f3f3f3;
			border-bottom: 1px solid #eee; 
			position: relative;
			padding-left: 50%; 
                        width: auto;
		}
		
		td:before { 
			/* Now like a table header */
			position: absolute;
			/* Top/left values mimic padding */
			top: 6px;
			left: 6px;
			width: 45%; 
			padding-right: 10px; 
			white-space: nowrap;
		}
		
		/*
		Label the data
		*/
		td:nth-of-type(1):before { content: "First Name:"; }
		td:nth-of-type(2):before { content: "Surname:"; }
		td:nth-of-type(3):before { content: "Username:"; }
		td:nth-of-type(4):before { content: "City:"; }
		td:nth-of-type(5):before { content: "Role:"; }
		td:nth-of-type(6):before { content: "E-mail:"; }
		td:nth-of-type(7):before { content: "Phone:"; }
		td:nth-of-type(8):before { content: "Update:"; }
		td:nth-of-type(9):before { content: "Delete:"; }
		
	}
	
	/* Smartphones (portrait and landscape) ----------- */
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
			width: 495px; 
		}
	}
	
	</style>
	<!--<![endif]-->
    </head>
    
<body>
    <br>
    <br>
    <br>
       <div id="page-wrap"> 
     <h3>Here you can delete or modify moderators</h3>
        <table>
            
        <%  
               List<User> modList = (List<User>)request.getSession().getAttribute("modList");
                  out.print("<thead>");
                        out.print("<tr>");
                                    
                                    out.print("<th>Name : </th>");
                                    out.print("<th>Surname : </th>");
                                    out.print("<th>Username : </th>");
                                    out.print("<th>City : </th>");
                                    out.print("<th>Role : </th>");
                                    out.print("<th>Email : </th>");
                                    out.print("<th>Phone : </th>");
                                    out.print("<th>Update : </th>");
                                    out.print("<th>Delete : </th>");
                        out.print("</tr>");
                  out.print("</thead>");
               if(modList!=null){
                    for(int i = 0; i<modList.size();i++){
                            
                     out.print("<tbody>");
                         
                        // out.print("<td>"+modList.get(i).getId()+ "</td>");

                         
                         out.print("<td>"+modList.get(i).getName()+ "</td>");

                         out.print("<td>"+modList.get(i).getLastname()+ " </td>");

                         out.print("<td>"+modList.get(i).getUsername()+ "</td>");
                         
                         out.print("<td>"+modList.get(i).getCity()+ "</td>");
                         
                         out.print("<td>"+modList.get(i).getRank()+ "</td>");
                          
                         out.print("<td>"+modList.get(i).getEmail()+ "</td>");
                         
                         out.print("<td>"+modList.get(i).getPhone()+ "</td>");
                         
                         out.print("<td>");
                                out.print("<form action=\"userInfoAdmin\" method=\"post\">");
                                out.print(" <input type=\"hidden\" value="+modList.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Update\">");
                                out.print("</form>");
                         out.print( "</td>");
                         out.print("<td>");
                                out.print("<form action=\"DeleteUserAdmin\" method=\"post\">");
     /*inline Javascript*/      out.print(" <input type=\"hidden\" value="+modList.get(i).getId()+" name=\"userId\" /> <input type=\"submit\" value=\"Delete\" onclick=\"return confirm('Are you sure you want to delete this user???')\">");
                                out.print("</form>");
                         out.print( "</td>");
                    out.print("</tr>");
                 out.print("<tbody>");
                        
                         
                    }
               }
               

           %>
            </thead>
        </table>
        
        
    </div> <!--End of page wrap -->    
    </body>
</html>
