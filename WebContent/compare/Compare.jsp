<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Numbering</title>
	<meta charset=UTF-8">
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/>  />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Numbering</title>
	
</head>

<div class="header">
	<h1>Telephone numbers in Serbia</h1>
</div>
	<div style="overflow: auto">
	
		<div class="menu">
			<jsp:include page="/moduls/Menu.jsp"></jsp:include>
		</div>
		
		<div class="main">
			<h2>Compare ranges</h2>
	
			<p>On this page, you can upload a file, and then compare the uploaded file against 
			<br/>${applicationScope.dataSource} 
			<p>
			
			<p>File for comparison must be in format
				<br/> area code; start range; area code; end range
				<br/> e.g 11;3469000;11;3469999;
			</p>
	
			<form action=<c:url value="/check" /> method="post" enctype="multipart/form-data" name="select" >
				<input type="hidden" name="select" value="file">
	
				<input type="file"  value="Choose" name="myFile" accept = "text/plain">
				<br/>
				<input type="submit" value="Upload" >
			</form>
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