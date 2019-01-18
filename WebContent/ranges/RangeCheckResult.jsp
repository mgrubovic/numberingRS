<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_main.css" />
	<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>
	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>
	
		<div class="main-right">

			<p> Checking availability of the request</p>
			<table>
				<tr><td>Area code</td><td> ${param["mg"]}</td></tr>
				
				<tr><td>Start of the range</td><td> ${param["startRange"]}</td></tr>
				
				<tr><td>End of the range</td><td> ${param["endRange"]}</td></tr> 
			</table>
			

			<c:forEach var="answerLines" items="${requestScope.answerRange}">
				<p>${answerLines}</p>
			</c:forEach>
		</div><!-- end of class="main-right" -->
		
	</div>	<!-- end of style="overflow:auto" -->

	<div class="footer">
		<p>2017
		<br/>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>