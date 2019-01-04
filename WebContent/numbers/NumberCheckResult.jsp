<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_main.css" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-16">
<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>

		<div class="main-right">
			<c:forEach var="answerLines" items="${requestScope.answers}">
				<p>${answerLines}</p>
			</c:forEach>
		

			<%
/*			
				Using MVC but with scriplets
				List <String> answerLines = (List <String>)request.getAttribute("answers");
				for(String line:answerLines){
					out.println("<p>");
					out.println("--" + line);
					out.println("</p>");
				}
*/				
				
/*				First version while building functionality
				String hiddenString = request.getParameter("numbersToSend");

				SearchNumbers searchNumbers = new SearchNumbers();
				searchNumbers.makeList(hiddenString);
				searchNumbers.makeAnswer();
				request.setAttribute("answers", searchNumbers.answerLines);
				for(String line:searchNumbers.answerLines){
					out.println("<p>");
					out.println(line);
					out.println("</p>");
				}
*/
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