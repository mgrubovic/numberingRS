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
			<a href="AreaCodeDistribution.jsp">Distribution by area codes</a>
			
			<h3>Distribution by operators</h3>
			
			<!--Div that will hold the pie chart-->
    		<div id="chart_div"></div>
			
			<table border="1px" bgcolor="#c5c5c5" id="operatorDistribution">
				<tr>
					<th>Operator</th>
					<th>Amount</th>
				</tr>
				
				<c:forEach var="sumList" items="${applicationScope.operatorList}" >
					<tr class="tableRow">
						<td>${sumList.operator}</td>
						<td align="right">
							<fmt:setLocale value="de_DE"/>
							<fmt:formatNumber type ="number" >${sumList.amountRange}</fmt:formatNumber>
						</td>
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