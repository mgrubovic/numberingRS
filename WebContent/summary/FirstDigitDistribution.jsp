<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/>  />

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src=<c:url value="/js/jquery-1.11.3.min.js"/> ></script>
	<script type="text/javascript" src=<c:url value="/js/pieChart.js"/> ></script>
	<script type="text/javascript" src=<c:url value="/js/percent.js"/> ></script>
	
	
	<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>
	<div style="overflow: auto">

		<div class="menu">
			<jsp:include page="/moduls/Menu.jsp"></jsp:include>
		</div>
		
		<div class="main">
		<fmt:setLocale value="de_DE"/>
			<a href="AreaCodeDistribution.jsp">Distribution by area codes</a>
			<a href="OperatorDistribution.jsp">Distribution by operator</a>
			
			
			<h3>Distribution of first digit</h3>
			
			<!--Div that will hold the pie chart-->
    		<div id="chart_div"></div>
    		
			<p>Table presents distribution of first digit among area codes. Subscriber geographic numbers for the first digit
			may have 2,3,4,5,6,7 or 8.
			</p>
			
			<table id="firstDigitDistribution">
				<tr>
					<th></th>
					<th>All</th>
					<th>2</th>
					<th>3</th>
					<th>4</th>
					<th>5</th>
					<th>6</th>
					<th>7</th>
					<th>8</th>
				</tr>
				
				<c:forEach var="sumList" items="${applicationScope.firstDigit}" >
					<tr class="tableRow" align="right">
						
						<td>${sumList[0]}</td>
						
						<td><fmt:formatNumber type ="number" >${sumList[1]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[2]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[3]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[4]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[5]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[6]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[7]}</fmt:formatNumber></td>
						<td><fmt:formatNumber type ="number" >${sumList[8]}</fmt:formatNumber></td>
					</tr>
				</c:forEach>
		 		
			</table>
		 
		</div>	<!-- end of class="main" -->
		
	</div>		<!-- end of style="overflow:auto" -->

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>