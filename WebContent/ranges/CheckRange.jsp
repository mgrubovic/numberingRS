<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.jaxb.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*, java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style_main.css" />

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/checkInput.js"></script>

<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>
	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>
			<div class="main-right">
			<h3>You can check existence of the range</h3>
			
			<p>On this site you can check out if the numbering range exists	or it is free for assignment</p>
				
			<!-- form action="../CheckNumberServletBack" method="post" -->
			<form action="RangeCheckResult.jsp" method="post">
				<p>
					Area code:
					<select name="mg">
				<%
					String jspPath = application.getRealPath("/data");
					String fileXmlAreaCodes = jspPath + File.separator +  "AreaCodes.xml";
					ListAreaCodeJaxb jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);

					for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
						out.print("<option class='ndc-geo' value='" + codeItem.getCode() +"'>" 
											+ codeItem.getCode() +"</option>");
					}		
				%>

					</select> start range: <input type="text" name="startRange" /> end range: <input
						type="text" name="endRange" />
				</p>

				<div id="errInput"></div>

				<p>
					<input type="submit" name="Search" value="Search" />
				</p>
			</form>
	

			<p>
				<%=application.getAttribute("dataSource")%>
			</p>
			
			<jsp:include page="/moduls/MainTable.jsp"></jsp:include>

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