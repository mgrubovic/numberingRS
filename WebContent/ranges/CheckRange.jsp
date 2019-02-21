<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/>  />

	<script type="text/javascript" src=<c:url value="/js/jquery-1.11.3.min.js"/> ></script>
	<script type="text/javascript" src=<c:url value="/js/checkInput.js.js"/> ></script>

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
			<h3>You can check existence of the range</h3>
			
			<p> On this site you can check out if the numbering range exists or it is free for assignment</p>
				
			<form action=<c:url value="/check" /> method="post">
				<input type="hidden" name="select" value="range">
				<p>Select area code:
					<select name="net">
						<jsp:include page="/moduls/OptionsAreaCode.jsp"></jsp:include>
					</select>
	
					start range: <input type="text" name="startRange" /> 
					end range: <input type="text" name="endRange" />
				</p>

				<div id="errInput"></div>

				<p>
					<input type="submit" name="Search" value="Search" />
				</p>
			</form>
	

			<p>${applicationScope.dataSource}</p>
			
			<jsp:include page="/moduls/MainTable.jsp"></jsp:include>

		</div>	<!-- end of class="main-right" -->
	</div>	<!-- end of style="overflow:auto" -->

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>