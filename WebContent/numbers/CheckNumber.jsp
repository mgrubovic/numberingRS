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
<!-- script type="text/javascript" src="../js/numberInput.js"></script	-->

<title>Numbering</title>
</head>
<body>
	<div class="header">
		<h1>Telephone numbers in Serbia</h1>
	</div>

	<div style="overflow: auto">
		<jsp:include page="/moduls/Menu.jsp"></jsp:include>

		<div class="main-right">
			<h3>Searching within RATEL page</h3>
			<%--		String jspPath = application.getRealPath("/data"); --%>
			
			<p>On this site you can find out does a subscriber number is
				assigned and if yes, who is the operator of the number
				
			</p>

			<form name="form1" action="NumberCheckResult.jsp" method="post">
				<table>
					<tr>
						<td>Area code:</td>
						<td><select name="net">
							<c:forEach var="codeItem" items="${applicationScope.areaCode.areaCodeJaxb}">
								<option class="ndc-geo" value="${codeItem.code}">${codeItem.code}</option>
							</c:forEach>


						</select></td>
					</tr>
					<tr>
						<td>Subscriber number:</td>
						<td><input type="text" name="newSubscriber" /></td>
					</tr>
				</table>
				<br />

				<div id="errInput">
					<p></p>
				</div>
				<input type="hidden" name="numbersToSend"> <input
					type="button" name="button1" value="Add number" /> <br /> <br />


				<table id="expand-Table" border="3">
					<tr>
						<th>index</th>
						<th>area code</th>
						<th>number</th>
					</tr>
				</table>

				<div id="snd1">
					<p>I'm going to send to server:</p>
					<p />

				</div>
				<input type="submit" name="send" value="SEND" />

			</form>


			<p>${applicationScope.dataSource}
				<%-- =application.getAttribute("dataSource")--%>
			</p>
		
			<p>
				<!-- NumberingRanges.xml is actually a RangeXmlDownload.jsp  mapped in web.xml -->					
				<a href="../data/NumberingRanges.xml">Table in xml format</a>
			<p>
			
			<jsp:include page="/moduls/MainTable.jsp"></jsp:include>
	
			
	<script type="text/javascript">
		
		var vButton = document.form1.button1;
		var vSubmit = document.form1.send;
		
		var numClicked = 0;

		var vSubscriber = document.form1.newSubscriber;
		var vNet = document.form1.net;
		var vHidden = document.form1.numbersToSend;
		
		var elemTable = document.getElementById("expand-Table")
		var vHiddenValue = "";
		
		var snd1 = document.getElementById("snd1");
		var p1 = snd1.getElementsByTagName("p")[1];

		
		var pError = document.getElementById("errInput").getElementsByTagName("p")[0];
		
		function makeTable(event)
		{
			var goodInput=true;
			newSubscriberValue = vSubscriber.value;
			var txtNum = document.createTextNode(newSubscriberValue);
			if(newSubscriberValue.length == 0 || isNaN(newSubscriberValue)){

				pError.innerHTML =  "Please enter the number";
				//alert("event is "+ event.type + " triger " + event.triger);
				goodInput=false;
				
				
			}else if(newSubscriberValue.length > 7){
				pError.innerHTML =  "The number " + newSubscriberValue + " is to long it should be less then 8 digits";
				goodInput=false;

			}else if(newSubscriberValue.length < 5){
				pError.innerHTML =  "The number " + newSubscriberValue + " is to short it should have more then 4 digits";
				goodInput=false;

			}else{ // if everything is good append row in table and number in request hidden attribute
				//alert("number" + txtNum);
				pError.innerHTML =  "";
			
				newNetValue= vNet.value;
				var txtNet = document.createTextNode(newNetValue);
				
				var elemRow = document.createElement("tr");
				var elemCell1 = document.createElement("td");
				var elemCell2 = document.createElement("td");
				var elemCell3 = document.createElement("td");	
	
				elemCell1.appendChild(document.createTextNode(numClicked));
				elemRow.appendChild(elemCell1);
				
				elemCell2.appendChild(txtNet);
				elemRow.appendChild(elemCell2);
				
				elemCell3.appendChild(txtNum);
				elemRow.appendChild(elemCell3);
				
				elemTable.appendChild(elemRow);
			
				numClicked++;		
	
				vHiddenValue =  vHiddenValue + newNetValue + ";" + newSubscriberValue + "!!!";
				vHidden.value = vHiddenValue;
				
				toSend(vHiddenValue);	// it is a control function to show string that is going to send to server				
			}// end of if else block
			
			if(!goodInput && numClicked == 0){
			       if(event.preventDefault){ event.preventDefault()}; 
			       if(window.event){window.event.returnValue = false;}
				
			}
			
		}
		
		function toSend(vHiddenValue){
		

			p1.innerHTML =  vHiddenValue;
			snd1.appendChild(p1);
		}
		
		
		if(vButton.addEventListener){
			vButton.addEventListener("click", makeTable);
			document.form1.addEventListener("submit", makeTable);
		}else{
		    //IE doesn't have addEventListner
		    vButton.attachEvent("onclick", makeTable);
		    document.form1.attachEvent("onsubmit", makeTable);
		}

	</script>
		</div>
		<!-- end of class="main" -->
	</div>

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>

</body>
</html>