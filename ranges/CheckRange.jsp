<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ page import="rs.numbering.format.*" %>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*, java.io.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style_main.css"/>

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/checkInput.js"></script>

<title>Numbering</title>
</head>
<body>
<div class="header">
  <h1>Telephone numbers in Serbia</h1>
</div>
<div style="overflow:auto">
 <div class="menu">
    <div class="menuitem"><a href="../index.html">About</a></div>
    <div class="menuitem"><a href="../numbers/CheckNumber.jsp">Find numbers</a></div>
    <div class="menuitem"><a href="CheckRange.jsp">Find ranges</a></div>
    <div class="menuitem"><a href="../compare.html">Compare</a></div>
    <div class="menuitem"><a href="../configuration/mainConfig.html">Configuration</a></div>
 </div>
 <div class="main-right">
	<h3>You can check existence of the range</h3>
	
	<p>On this site you can check out does the numbering range exists or it is free for assignment
	
	<form action="RangeCheckResult.jsp" method="post" >
		<p>Network:
				<!-- input type="text" name="mg" maxlength="3" size="3" --> 
				<select  name="mg" >
							<!--option value="" >ALL</option-->
							<option class="ndc-geo" value="10" >10</option>
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
				</select>
		
		
			start range: <input type="text" name="startRange"/>
			end range: <input type="text" name="endRange"/>
		</p>
		
		<div id="errInput">
		</div>
		
		<p>
			<input type="submit" name="Search" value="Search" />
		</p>
	</form>
	<%--
		*** For further use ***
		This is extension for checking other types of numbers
		it requires to be called from chooseType.html
	
		String numberType = request.getParameter("type");
		String choosenUrl = "You did't make a choise";
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
	
		}  	
	--%>
	<%
		/* Old approach
		List <Range> ranges1 = (List <Range>)session.getAttribute("geoRange");	
		if( ranges1==null){
			WebReader webReader = new WebReader();
			
			String url ="http://123registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv";
			String fileFormat="webCsvGeo";
			ReadRangeFactory factoryReader =  new ReadRangeFactory(fileFormat);
			ReadRange fromatReader = factoryReader.getFormatReader();
			webReader.takeData(url, fromatReader);
			ranges1= webReader.listRanges;
			// SearchRanges.rangesBig= ranges1;
			if(ranges1.size()>0){
				session.setAttribute("geoRange", ranges1);
			}
		}
		SearchRanges.rangesBig= ranges1;
		
		
		Older approach
		
		List <Range> ranges1;
		Object obj = session.getAttribute("geoRange");
		String url = null;
		String urlDescription =null;
		//WebReader webReader = new WebReader();
		//FileRangeReader fileReader = new FileRangeReader();
		
		if(obj != null && obj instanceof List <?>){
			ranges1 = (List <Range>)obj;
			System.out.println("ranges1 = (List <Range>)obj; length" + ranges1.size() ); 
		}else{ //if range is not initialized we are going to read data from the web page
	
			url = application.getInitParameter("url-geo");
			
			
			//url = "http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv";
			
			String fileFormat="webCsvGeo";
	
			ReadRangeFactory factoryReader =  new ReadRangeFactory(fileFormat);
			ReadRange fromatReader = factoryReader.getFormatReader();
			SourceReader sourceReader = factoryReader.getSourceReader();
			ranges1 = sourceReader.takeData(url, fromatReader);
			if(ranges1.size()>0){
				session.setAttribute("geoRange", ranges1);
				urlDescription = "Table data taken from web address: " + url;
				session.setAttribute("dataSource", urlDescription);
			}else{ //if range is not initialized from web page we are going to read data from data file on the server 
				String jspPath = application.getRealPath("/");
				url = jspPath + "data" + File.separator +  "Geo20180207.csv";
				
				urlDescription = "Table data may not be updated they are taken from back-up file: " + "Geo20180207.csv";

				System.out.println("Data file is " + url);

				fileFormat="fileCsvGeo";
			
				factoryReader =  new ReadRangeFactory(fileFormat);
				fromatReader = factoryReader.getFormatReader();

				sourceReader = factoryReader.getSourceReader();
				ranges1 = sourceReader.takeData(url, fromatReader);
				
				session.setAttribute("geoRange", ranges1);
				session.setAttribute("dataSource", urlDescription);
				
				System.out.println("reading file" ); 

			}

		}//end if(obj != null && obj instanceof List <?>)... else
			
*/			
		List <Range> rangesMain;
		Object obj = session.getAttribute("geoRange");
		
		
		if(obj != null && obj instanceof List <?>){
			rangesMain = (List <Range>)obj;
			System.out.println("ranges1 = (List <Range>)obj; length" + rangesMain.size() ); 
		}else{ //if range is not initialized we are going to read data from the web page
			
			String firstPlace = application.getInitParameter("url-geo");
			String firstFormat = "webCsvGeo";
			
			String jspPath = application.getRealPath("/");
			String fileName = application.getInitParameter("file-geo");
			String secondPlace = jspPath + "data" + File.separator +  fileName;
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
			
		SearchRanges.rangesBig= rangesMain;

		
	%>
	
	<p> <%=session.getAttribute("dataSource")%><p>
		
	<table border="1px" bgcolor="#c5c5c5">
		<tr><td>Index</td><td>Net</td><td>Start of range </td><td>End of range</td><td>Operator</td></tr>
		<%
			for(int i=0; i<rangesMain.size(); i++){
			Range firstRange = rangesMain.get(i);
		%>
			<!-- bgcolor="< %=firstRange.getColorCell()% >" -->
			<tr><td><%=i+1%></td><td><%=firstRange.mg%></td>
			<td><%=firstRange.startRange %></td><td><%=firstRange.endRange %></td><td><%= firstRange.operator %></td></tr>
		<%
			}
		%>
	</table>

</div><!-- end of class="main-right" -->	
</div><!-- end of style="overflow:auto" -->

	
<div class="footer">2017
</p>mgrubovic@yahoo.com</p>
</div>	
	
</body>
</html>