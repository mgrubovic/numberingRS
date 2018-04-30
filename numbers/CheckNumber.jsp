<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.model.*"%>
<%@ page
	import="rs.numbering.source.*, rs.numbering.operation.*, rs.numbering.jaxb.*"%>

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
		<div class="menu">
			<div class="menuitem">
				<a href="../index.html">About</a>
			</div>
			<div class="menuitem">
				<a href="#">Find numbers</a>
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
				<a href="../configuration/mainConfig.html">Configuration</a>
			</div>
		</div>

		<div class="main-right">
			<h3>Searching within RATEL page</h3>
			<%		String jspPath = application.getRealPath("/data"); %>
			<p>On this site you can find out does a subscriber number is
				assigned and if yes, who is the operator of the number</p>

			<form name="form1" action="NumberCheckResult.jsp" method="post">
				<table>
					<tr>
						<td>Area code:</td>
						<td><select name="net">

								<%
						ListAreaCodeJaxb jaxbList = null;
						Object objArea = session.getAttribute("areaCode");
						if(objArea != null && objArea instanceof ListAreaCodeJaxb){
							jaxbList = (ListAreaCodeJaxb)objArea;
						}else{
							String fileXmlAreaCodes = jspPath + File.separator +  "AreaCodes.xml";
							jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);
							session.setAttribute("areaCode", jaxbList);
						}
						for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
							out.print("<option class='ndc-geo' value='" + codeItem.getCode() +"'>" 
										+ codeItem.getCode() +"</option>");
						}
					%>

								<!--option value="" >ALL</option-->
								<!-- option class="ndc-geo" value="10" >10</option>
							<option class="ndc-geo" value="11" >11</option>
							<option class="ndc-geo" value="12" >12</option>
							<option class="ndc-geo" value="13" >13</option>
							<option class="ndc-geo" value="14" >14</option>
							<option class="ndc-geo" value="15" >15</option>
							<option class="ndc-geo" value="16" >16</option>
							<option class="ndc-geo" value="17" >17</option>
							<option class="ndc-geo" value="18" >18</option>
							<option class="ndc-geo" value="19" >19</option>
							<option class="ndc-geo" value="20" >20</option>
							<option class="ndc-geo" value="21" >21</option>
							<option class="ndc-geo" value="22" >22</option>
							<option class="ndc-geo" value="23" >23</option>
							<option class="ndc-geo" value="230" >230</option>
							<option class="ndc-geo" value="24" >24</option>
							<option class="ndc-geo" value="25" >25</option>
							<option class="ndc-geo" value="26" >26</option>
							<option class="ndc-geo" value="27" >27</option>
							<option class="ndc-geo" value="28" >28</option>
							<option class="ndc-geo" value="280" >280</option>
							<option class="ndc-geo" value="29" >29</option>
							<option class="ndc-geo" value="290" >290</option>
							<option class="ndc-geo" value="30" >30</option>
							<option class="ndc-geo" value="31" >31</option>
							<option class="ndc-geo" value="32" >32</option>
							<option class="ndc-geo" value="33" >33</option>
							<option class="ndc-geo" value="34" >34</option>
							<option class="ndc-geo" value="35" >35</option>
							<option class="ndc-geo" value="36" >36</option>
							<option class="ndc-geo" value="37" >37</option>
							<option class="ndc-geo" value="38" >38</option>
							<option class="ndc-geo" value="39" >39</option>
							-->
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


			<%-- In case of different type of numbering
		String numberType = request.getParameter("type");
		String choosenUrl = "You did't make a choice";
		String fileFormat=null;
		if(numberType != null){
			if(numberType.equals("geo")){
				choosenUrl = request.getParameter("geoPage");
				fileFormat="webCsvGeo";
			}else if(numberType.equals("notGeo")){
				choosenUrl = request.getParameter("notGeoPage");
				fileFormat="webCsvNotGeo";
			}else if(numberType.equals("short")){
				choosenUrl = request.getParameter("shortPage");
			}
		}
		out.println("You choose:" + choosenUrl);	
		
		if(choosenUrl != null){
			 url = choosenUrl;
		}
	--%>
			<%
		List <Range> rangesMain;
		Object obj = session.getAttribute("geoRange");
		

		if(obj != null && obj instanceof List <?>){
			rangesMain = (List <Range>)obj;
			//System.out.println("ranges1 = (List <Range>)obj; length" + rangesMain.size() ); 
		}else{ //if range is not initialized we are going to read data from the web page
			
			//first try to take data from the web
			String firstPlace = application.getInitParameter("url-geo");
			String firstFormat = "webCsvGeo";
			
			//second try to take data from the file
			String fileName = application.getInitParameter("file-geo");
			String secondPlace = jspPath + File.separator +  fileName;
			String secondFormat = "fileCsvGeo";
			
			
			DataManager dataManager = new DataManager();
			dataManager.setFirstPlace(firstPlace);		
			dataManager.setFirstFormat(firstFormat);
			
			dataManager.setSecondPlace(secondPlace);
			dataManager.setSecondFormat(secondFormat);
			
			dataManager.getRanges();
			rangesMain = dataManager.rangesMain;
			
			session.setAttribute("geoRange", rangesMain);
			session.setAttribute("dataSource", dataManager.urlDescription);		

		}//end if(obj != null && obj instanceof List <?>)... else
		SearchNumbers.rangesBig =rangesMain;
/*		ListRangeJaxb listRangeJaxb = new ListRangeJaxb();
		for(Range rangeItem: rangesMain){
			RangeJaxb rangeJaxb = new RangeJaxb(rangeItem);
			listRangeJaxb.add(rangeJaxb);
		}
		application.setAttribute("xmlData", listRangeJaxb);
*/		
		String fileXmlName = "RangesList.xml";
		String fileXmlPlace = jspPath + File.separator +  fileXmlName;

		File writeRange = new File(fileXmlPlace);
		application.setAttribute("xmlDataFile", writeRange);
		OperationJaxb.listRangeToXml(rangesMain, writeRange);
		
	
	%>

			<p>
				<%=session.getAttribute("dataSource")%>
			<p>

				<!-- NumberingRanges.xml is actually a RangeXmlDownload.jsp  mapped in web.xml -->
			<p>
				<a href="../data/NumberingRanges.xml">Table in xml format</a>
			<p>
			<form action="" name="tableForm">
				<div id="divTable">
					<table border="1px" bgcolor="#c5c5c5" id="rangeTableView">
						<tr>
							<td>Index</td>
							<td>Area code</td>
							<td>Start of range</td>
							<td>End of range</td>
							<td>Operator</td>
						</tr>
						<tr>
							<td />
							<td><select name="selectAreaTable">
									<option class='ndc-geo' value='ALL'>ALL</option>
									<%

					for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
						out.print("<option class='ndc-geo' value='" + codeItem.getCode() +"'>" 
											+ codeItem.getCode() +"</option>");
					}		
				%>

							</select></td>
							<td><input type="text" name="selectStartRange" maxLength="3"
								size="3" /></td>
							<td></td>
							<td><select name="operatorTable">
									<option class='oTable' value='ts'>ALL</option>
									<!-- 
					<option class='oTable' value='ts'>Telekom Srbija a.d., Beograd</option> <
					 -->
							</select></td>
						</tr>

						<%
			for(int i=0; i<rangesMain.size(); i++){
			Range firstRange = rangesMain.get(i);
		%>
						<!-- 	This is option when program logic decides witch color row of table will be 
					tr bgcolor="<%-- =firstRange.getColorCell() --%>"
			-->
						<tr class="tableRow">
							<td><%=i+1%></td>
							<td><%=firstRange.mg%></td>
							<td><%=firstRange.startRange %></td>
							<td><%=firstRange.endRange %></td>
							<td><%= firstRange.operator %></td>
						</tr>
						<%
			}
		%>
					</table>
				</div>
			</form>
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