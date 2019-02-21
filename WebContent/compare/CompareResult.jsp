<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/>  />
	
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
	
		<div class="main-right">

			<p> Results of the comparison</p>

			<table>
				<tr><th>Area code</th><th>Start of the range</th><th>End of the range</th><th>Description</th></tr>
				<c:forEach var="resultRange" items="${requestScope.compareRanges}" >
				<tr>
					<td>${resultRange.mg}</td>
					<td>${resultRange.startRange}</td>
					<td>${resultRange.endRange}</td>
					<td>
						<c:forEach var="answerLines" items="${resultRange.description}">
							${answerLines}<br/>
						</c:forEach>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div><!-- end of class="main-right" -->
		
	</div>	<!-- end of style="overflow:auto" -->

	<div class="footer">
		<p>2017
		<br/>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>