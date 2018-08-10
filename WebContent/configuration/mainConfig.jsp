<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>Numbering</title>
<link rel="stylesheet" href="../css/style_main.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>
		<!-- 
		<div class="menu">
			<div class="menuitem">
				<a href="../index.html">About</a>
			</div>
			<div class="menuitem">
				<a href="../numbers/CheckNumber.jsp">Find numbers</a>
			</div>
			<div class="menuitem">
				<a href="../ranges/CheckRange.jsp">Find ranges</a>
			</div>
			<div class="menuitem">
				<a href="../compare.html">Compare</a>
			</div>
			<div class="menuitem">
				<a href="../summary/SummaryHib.jsp">Summary</a>
			</div>
			<div class="menuitem">
				<a href="#">Configuration</a>
			</div>
		</div>
		-->
		<div class="main">
			<h2>Configuration</h2>

			<p>This application uses Java and JSP for application logic.
			</p>
			<p>JavaScript is for checking input values.
			</p>
			<p>JAXB for reading and writing XML
			</p>
			<p>The first choice is to retrieve the data from RATEL site dedicated
				to assigned telephone ranges (blocks):<br />
				http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv
			</p>
			<p>If an attempt to retrieve data from site fails, application
				will try second choice and take data from file in /data folder</p>
			<p>Source for retrieving data, URL address and file name are
				configurable in web.xml file
			</p>				
			<p>Summary page is made using Hibernate and HSQLDB
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