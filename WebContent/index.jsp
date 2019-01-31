<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>Numbering</title>
<link rel="stylesheet" href="css/style_main.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>

		<div class="main">
			<h2>About</h2>

			<p>This application uses data from the 
			<a href="http://registar.ratel.rs/en/reg202?">RATEL Database of assigned telephone numbering resource</a>. 
			The Database is the official database of telephone numbers and ranges(blocks)in Serbia. 
			</p>
			<p>This site can help you to:</p>
				<ul>
					<li>Find out does the (geographic) subscriber number is assigned and if it is, who is assignee (operator) </li>
					<li>Check out availability of geographic numbering range(block)</li>
					<li>See the distribution of phone numbers in relation to the area code</li>
					
				</ul>
	
		</div>

		<div class="right">
			<h4>About</h4>
			<p>Test project.</p>
		</div>
		<div class="right">
			<h4>News</h4>
			<p>Currently there are no news.</p>
		</div>


	</div>

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>

</html>