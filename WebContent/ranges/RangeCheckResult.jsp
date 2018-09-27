<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style_main.css" />
<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>
	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>
	
		<div class="main-right">


			<%
				String mgRequest= request.getParameter("mg");
				String startRangeRequest = request.getParameter("startRange");
				String endRangeRequest = request.getParameter("endRange");

			%>
			<p> Checking availability of the request
				<br/>
				Area code <%=mgRequest %>
				<br/>
				Start of range <%=startRangeRequest %>
				<br/>
				End of range <%=endRangeRequest %>
			</p>
			<%	
				boolean goodRequest = true;
				SearchRanges searchRanges = new SearchRanges();
				goodRequest = searchRanges.isRequestGood(mgRequest, startRangeRequest, endRangeRequest);
				
				if(goodRequest){
					List <Range> list1 = new ArrayList<>();
					list1.add(searchRanges.rangeInput);
					searchRanges.compareRanges(list1);
					
					for(String line:searchRanges.answerLines){
						out.println("<p>");
						out.println(line);
						out.println("</p>");
					}
		
					
				}else{
					out.println("<p>You put invalid value try again</p><br/>");
				}
			%>
		
		</div>
		<!-- end of class="main-right" -->
	</div>
	<!-- end of style="overflow:auto" -->


	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>