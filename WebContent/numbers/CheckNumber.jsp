<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*, rs.numbering.jaxb.*"%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-16">
<link rel="stylesheet" href="../css/style_main.css" />

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/selectTableView.js"></script>
<script type="text/javascript" src="../js/checkAndMakeTable.js"></script>


<title>Numbering</title>
</head>
<body onload = "checkMakeTable()">
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>

		<div class="main-right">
			<h3>Searching within RATEL page</h3>
			
			<p>On this site you can find out does a subscriber number is
				assigned and if yes, who is the operator of the number
				
			</p>

			<form name="form1" id="formGroup" action="../check" method="post">
				<input type="hidden" name="select" value="number">
			
				<table>
					<tr>
						<td>Area code:</td>
						<td>
							<select name="net">
								<c:forEach var="codeItem" items="${applicationScope.areaCode.areaCodeJaxb}">
									<option class="ndc-geo" value="${codeItem.code}">${codeItem.code}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Subscriber number:</td>
						<td><input type="text" name="newSubscriber" /></td>
					</tr>
				</table>
				
				<br/>
				<div id="errInput">	<p></p> </div>
				
				<input type="hidden" name="numbersToSend">
				<input type="button" name="button1" value="Add number" /> 
				<br/>
				<br/>


				<table id="expand-Table" border="3">
					<tr>
						<th>index</th>
						<th>area code</th>
						<th>number</th>
					</tr>
				</table>

				<div id="snd1">
					<p>I'm going to send to server:</p>
					<p/>

				</div>
				<input type="submit" name="send" value="SEND" />

			</form>


			<p>${applicationScope.dataSource}
				<%-- =application.getAttribute("dataSource")--%>
			</p>
		
			<p>
				<!-- NumberingRanges.xml is actually a RangeXmlDownload.jsp  mapped in web.xml -->					
				<a href="../data/NumberingRanges.xml">Table in xml format</a>
			</p>
			
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