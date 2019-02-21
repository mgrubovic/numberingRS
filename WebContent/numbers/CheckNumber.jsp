<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href=<c:url value="/css/style_main.css"/>  />
	
	<script type="text/javascript" src=<c:url value="/js/jquery-1.11.3.min.js"/> ></script>
	<script type="text/javascript" src=<c:url value="/js/checkAndMakeTable.js"/> ></script>
	
	<title>Numbering</title>
</head>
<body onload = "checkMakeTable()">
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		
		<div class="menu">
			<jsp:include page="/moduls/Menu.jsp"></jsp:include>
			
			<div class="download">
				<h4>Download</h4>
				<a href=<c:url value="/output"/> >Source data in XML</a>
			</div>
		</div>

		<div class="main-right">
			<h3>Searching within RATEL page</h3>
			
			<p>On this site you can find out does a subscriber number is
				assigned and if yes, who is the operator of the number
			</p>

			<form name="form1" id="formGroup" action=<c:url value="/check" /> method="post">
				<!-- for the controller (Dispatch Servlet  ControllerServlet.java) /check -->
				<input type="hidden" name="select" value="number">
			
				<table>
					<tr>
						<td>Area code:</td>
						<td><select name="net">
								<jsp:include page="/moduls/OptionsAreaCode.jsp"></jsp:include>
							</select>
						</td>
					</tr>
					<tr>
						<td>Subscriber number:</td>
						<td><input type="text" name="newSubscriber" /></td>
					</tr>
				</table>
			
				<div id="errInput">	<p></p> </div>
				
				<input type="hidden" name="numbersToSend">
				<input type="button" name="button1" value="Add number" /> 
				<br/><br/>
					<table id="expand-Table" border="3">
					<!-- checkAndMakeTable.js is responsible for filling data in this table -->
						<tr>
							<th>index</th>
							<th>area code</th>
							<th>number</th>
						</tr>
					</table>

					<input type="submit" name="send" value="SEND" />
				

			</form>

			<p>${applicationScope.dataSource}</p>
	
			<jsp:include page="/moduls/MainTable.jsp"></jsp:include>

		</div>
		<!-- end of class="main" -->
	</div>

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>