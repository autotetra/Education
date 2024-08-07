<%@page import="java.util.List"%>
<%@page import="hibernateModel.User"%> 
<%@page import="hibernateModel.Storeitems"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <title>Welcome</title>
        <STYLE>
            
	
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
		
		tr { border: none ; 
                 width: auto;
                }
		
		td { 
			/* Behave  like a "row" */
			border:  chartreuse;
                         //background-color: #f3f3f3;
			//border-bottom: 1px solid #eee; 
			position: relative;
			//padding-left: 50%; 
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
		td:nth-of-type(1):before { content: "Description:"; }
		td:nth-of-type(2):before { content: "Price:"; }
		td:nth-of-type(3):before { content: "Quantity:"; }
		
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
	
        </STYLE>
    </head>
    <body>
       <%  
            User user=(User)request.getSession().getAttribute("user");//.getAttribute("bean");  
            out.print("<DIV ALIGN='RIGHT'><P STYLE='color: #FFFFFF;'>Welcome, "+user.getName()+"</P>");
            out.print("<form action='Logout' method='post'><input type='submit' value='Logout' ID='transLinkLike'></form></DIV>");
       %> 
       <br />
        <DIV ALIGN="CENTER">
            <TABLE>
                <TR>
                    <TH>
                        <FORM ACTION="Search" METHOD="POST">
                            <INPUT TYPE="TEXT" NAME="searchKey" PLACEHOLDER="3 Chars Minimum" REQUIRED ID="searchInButton"/>
                            <INPUT TYPE="SUBMIT" VALUE="Search" NAME="searchBtn" ID="greenLinkLike"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        </FORM>
                    </TH>
                </TR>
            </TABLE>
        </DIV>
       <br />
       <br/>
       <DIV ALIGN="CENTER">
       <table>
           <tbody>
               <tr>
                   <th align="center"><form action="Store" method="post"><input type="submit" value="Manage Store" ID="sampleTransBox"></form></th>
                   <th align="center"><form action="aboutFrm" method="post"><input type="submit" value="Contact Us" ID="sampleTransBox"></form></th>
                   <th align="center"><form action="Catalogue" method="post"><input type="submit" value="Catalogue" ID="sampleTransBox"></form></th>
                   <th align="center"><form action="Settings" method="post"><input type="submit" value="Settings" ID="sampleTransBox"></form></th>
               </tr>
           </tbody>
       </table>
         
       </DIV>
        <br /><br/>
        <%  
            List<Storeitems> si = null;
            si=(List<Storeitems>)request.getSession().getAttribute("randomitems");
            out.print("<DIV ALIGN='CENTER'>");
            out.print("<table border = '5' STYLE='color: #FFFFFF;'><THEAD>");
            out.print("<tr ALIGN='CENTER'><TD COLSPAN='3'><H3>Offers!</H3></TD></TR>");
            out.print("<TR ALIGN='CENTER'>");
            out.print("<tH>Description </tH>");
            out.print("<tH>Price </tH>");
            out.print("<tH>Quantity</tH></tr></THEAD>");
            for(int i = 0; i<si.size(); i++)
            {
                out.print("<tr ALIGN='CENTER'>");
                out.print("<td>" + si.get(i).getDescription()+"</td>");
                out.print("<td>" + si.get(i).getPrice()+"</td>");
                out.print("<td>" + si.get(i).getQuantity()+"</td>");
                out.print("<tr>");
                out.print("<br />");
            }
            out.print("</table></DIV>");
        %>
    </body>
</html>