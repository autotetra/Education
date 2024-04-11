<%@page import="dao.UserPanelDAO"%>
<%@page import="java.util.List"%>
<%@page import="hibernateModel.Storeitems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
    <HEAD>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
        <LINK REL="stylesheet" HREF="indexStyle.css"/>
        <TITLE>Welcome</TITLE>
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
			width: auto;
                        height: auto;
		}
	}
	
        </STYLE>
    </HEAD>
    <BODY BGCOLOR="#777777">
        <DIV ALIGN="CENTER">
            <TABLE>
                <TR ALIGN="CENTER">
                    <TH>
                        <H1 ID="eshopHeader">C2C Store</H1>
                    </TH>
                </TR>
                <FORM ACTION="Index" METHOD="POST">
                <TR COLSPAN="2" ALIGN="CENTER">
                    <TH>
                        <INPUT TYPE="TEXT" NAME="name" ID="signUpInStyle" PLACEHOLDER="Username" REQUIRED/>
                    </TH>
                </TR>
                <TR COLSPAN="2" ALIGN="CENTER">
                    <TH>
                        <INPUT TYPE="PASSWORD" NAME="password" ID="signUpInStyle" PLACEHOLDER="Password" REQUIRED/>
                    </TH>
                </TR>
                <TR>
                    <TH ALIGN="CENTER">
                        <INPUT TYPE="SUBMIT" VALUE="Login" ID="buttonInputStyling"/></FORM>
                        <FORM ACTION="Register" METHOD="POST">
                            <INPUT TYPE="SUBMIT" VALUE="Register" ID="buttonInputStyling">
                        </FORM>
                    </TH>
                </TR>
                <TR ALIGN="CENTER">
                    <TH>
                        <FORM ACTION="Catalogue" METHOD="POST">
                            <INPUT TYPE="SUBMIT" VALUE="Catalogue" ID="buttonInputStyling"/>
                        </FORM>
                    </TH>
                </TR>
                <TR ALIGN="CENTER">
                    <TH>
                        <FORM ACTION="passrecover" METHOD="POST">
                            <INPUT TYPE="SUBMIT" VALUE="Forgot Password?" ID="transLinkLike"/>
                        </FORM>
                    </TH>
                </TR>
            </TABLE>
        </DIV>
        <BR/>
        <BR/>
        <BR/>
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
        <BR/>
        <%  
            UserPanelDAO upd = new UserPanelDAO();
            List<Storeitems> items = null;
            items =(List<Storeitems>)upd.retrieveItems();
            out.print("<DIV ALIGN='CENTER'><TABLE STYLE='color: #FFFFFF;' border='5'><THEAD>");
            out.print("<TR ALIGN='CENTER'><TD COLSPAN='3'><H3>Offers!</H3></TD></TR>");
            out.print("<TR>");
            out.print("<TH>Description </TH>");
            out.print("<TH>Price</TH>");
            out.print("<TH>Quantity</TH>");
            out.print("</THEAD></TR>");
            for(int i = 0; i<items.size(); i++)
            {
                out.print("<TR ALIGN='CENTER'>");
                out.print("<TD>" + items.get(i).getDescription() + "</TD>");
                out.print("<TD>" + items.get(i).getPrice() + "</TD>");
                out.print("<TD>" + items.get(i).getQuantity() + "</TD>");
                out.print("</TR>");
            }
            out.print("</TABLE></DIV>");
        %>
    </BODY>
</HTML>