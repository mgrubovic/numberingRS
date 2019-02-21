<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Numbering</title>
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/> />
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
			<h2>Configuration</h2>

			<p>The application logic is written in <strong>Java</strong>, <strong>JSP</strong> and <strong>Servlet</strong> technology.
			</p>
			<p><strong>JavaScript</strong> is used for checking input values and to make group request for number checking.
				<br/>
				Also, <strong>JavaScript</strong> is used to adjust table view by area code, by start of range or by teleco operator.
			</p>
			<p>JAXB is used for reading and writing XML
			</p>
			<p>The first choice is to retrieve the data from RATEL Database of numbering resource use
				<br/>
				${applicationScope.dataSource}
			</p>
			<p>If an attempt to retrieve data from site fails, application
				will try the second choice and take data from file in /data folder</p>
			<p>Source for retrieving data, URL address and file name are
				configurable in web.xml file
			</p>				
			<p>Summary page is made using the Hibernate and the HSQLDB
			</p>


		</div>

		<div class="right">
			<h4>About</h4>
			<p>Test project.</p>
		</div>


	</div>

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>

</html>