<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
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
			<a href="OperatorDistribution.jsp">Distribution by operators</a>
			<h3>Distribution by area codes</h3>

			<!--Div that will hold the pie chart-->
    		<div id="chart_div"></div>
 		
			<table border="1px" bgcolor="#c5c5c5" id="areaCodeDistribution">
				<tr>
					<th>City</th>
					<th>Area code</th>
					<th>Amount</th>
				</tr>
				<c:forEach var="sumList" items="${applicationScope.areaList}" >
					<tr class="tableRow">
						<td><c:forEach var="codeItem" items="${applicationScope.areaCode.areaCodeJaxb}">
								<c:if test="${codeItem.code eq sumList.mg}" >
									${codeItem.cityLat}
								</c:if>
							</c:forEach>
						</td>
						<td>${sumList.mg}</td>
						<td align="right">
							<fmt:setLocale value="de_DE"/>
							<fmt:formatNumber type ="number" >${sumList.amountRange}</fmt:formatNumber>
						</td>
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