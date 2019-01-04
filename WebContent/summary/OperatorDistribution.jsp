<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.jaxb.*"%>
<%@ page import="rs.numbering.hibernate.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*, java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style_main.css" />

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../js/pieChart.js"></script>

<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>
	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>

		<div class="main">
			<a href="AreaCodeDistribution.jsp">Distribution by area codes</a>
			
			<h3>Distribution by operators</h3>
			
			<!--Div that will hold the pie chart-->
    		<div id="chart_div"></div>
			
			
			<table border="1px" bgcolor="#c5c5c5" id="operatorDistribution">
				<tr>
					<td>Operator</td>
					<td>Amount</td>
				</tr>
				
				<c:forEach var="sumList" items="${applicationScope.operatorMap}" >
					<tr class="tableRow">
						<td>${sumList.key}</td>
						<td>${sumList.value}</td>
					</tr>
				</c:forEach>
		 		
			</table>

		</div>
		<!-- end of class="main" -->
	</div>
	<!-- end of style="overflow:auto" -->

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>