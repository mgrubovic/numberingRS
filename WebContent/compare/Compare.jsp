<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Numbering</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../css/style_main.css" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Numbering</title>
	
</head>

<div class="header">
	<h1>Telephone numbers in Serbia</h1>
</div>
	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>
		<div class="main">
			<h2>Compare ranges</h2>
	
			<p>On this page you can upload file to compare uploaded file against 
			<br/>${applicationScope.dataSource} 
			<p>
			
			<p>File for copmarision must be in format
				<br/> area code; start range; area code; end range
				<br/> e.g 11;3469000;11;3469999;
			</p>
	
			<form action="/numberingRS/check" method="post" enctype="multipart/form-data" name="select" value="file">
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