<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="rs.numbering.format.*"%>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.jaxb.*"%>
<%@ page import="rs.numbering.hibernate.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*, java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style_main.css" />

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/checkInput.js"></script>

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
				<a href="../numbers/CheckNumber.jsp">Find numbers</a>
			</div>
			<div class="menuitem">
				<a href="../ranges/CheckRange.jsp">Find ranges</a>
			</div>
			<div class="menuitem">
				<a href="../compare.html">Compare</a>
			</div>
			<div class="menuitem">
				<a href="SummaryHib.jsp">Summary</a>
			</div>
			<div class="menuitem">
				<a href="../configuration/mainConfig.html">Configuration</a>
			</div>
		</div>
		<div class="main-right">
			<h3>Distribution by area codes</h3>

			<%		String jspPath = application.getRealPath("/data"); %>

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
		
	%>


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
		%>
			<table border="1px" bgcolor="#c5c5c5" id="rangeTableView">
				<tr>
					<td>City</td>
					<td>Area code</td>
					<td>Amount</td>
				</tr>
				<%for(String mgMap : OperationHibRange.sumMap.keySet())
		{
		%>
				<tr>
					<td>
						<%
						for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
							if(codeItem.getCode().equals(mgMap) ){
								out.print(codeItem.getCityLat());
							}
						}
				%>
					</td>

					<td><%=mgMap%></td>
					<td><%=OperationHibRange.sumMap.get(mgMap)%></td>
				</tr>
				<%
		}
		 %>
			</table>
			<%--		out.print(OperationHibRange.sumMap);
	--%>
		</div>
		<!-- end of class="main-right" -->
	</div>
	<!-- end of style="overflow:auto" -->

	<div class="footer">
		2017
		<p>mgrubovic@yahoo.com</p>
	</div>
</body>
</html>