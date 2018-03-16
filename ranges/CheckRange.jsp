<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="rs.numbering.format.*" %>
<%@ page import="rs.numbering.model.*"%>
<%@ page import="rs.numbering.jaxb.*"%>
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
	<%		String jspPath = application.getRealPath("/data"); %>
	
	
	<p>On this site you can check out does the numbering range exists or it is free for assignment
	
	<form action="RangeCheckResult.jsp" method="post" >
		<p>Area code:
				<!-- input type="text" name="mg" maxlength="3" size="3" --> 
				<select  name="mg" >
				<%
					String fileXmlAreaCodes = jspPath + File.separator +  "AreaCodes.xml";
					ListAreaCodeJaxb jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);

					for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
						out.print("<option class='ndc-geo' value='" + codeItem.getCode() +"'>" 
											+ codeItem.getCode() +"</option>");
							}		
				%>
	
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
			
			//first try to take data from the web
			String firstPlace = application.getInitParameter("url-geo");
			String firstFormat = "webCsvGeo";
			
			//second try to take data from the file
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
	<form action=""  name="tableForm" >
	<div id="divTable">
	<table border="1px" bgcolor="#c5c5c5" id="rangeTableView">
		<tr><td>Index</td><td>Area code</td><td>Start of range </td><td>End of range</td><td>Operator</td></tr>
			<tr>
			<td/>
			<td>
				<select  name="selectAreaTable" >
					<option class='ndc-geo' value='ALL'>ALL</option>
				<%

					for(AreaCodeJaxb codeItem :jaxbList.areaCodeJaxb){
						out.print("<option class='ndc-geo' value='" + codeItem.getCode() +"'>" 
											+ codeItem.getCode() +"</option>");
					}		
				%>
					
				</select>
			</td>
			<td><input type="text" name="selectStartRange" maxLength="3" size="3" /></td>
			<td></td>
			<td>
				<select  name="operatorTable" >
					<option class='oTable' value='ts'>ALL</option>
					<!-- 
					<option class='oTable' value='ts'>Telekom Srbija a.d., Beograd</option> <
					 -->
				</select>
			</td>
			</tr>

		<%
			for(int i=0; i<rangesMain.size(); i++){
			Range firstRange = rangesMain.get(i);
		%>
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
	$(document).ready(function(){
		
		var dataTable = $("#rangeTableView");
		var arrData=[];
		var operator_Unique = [];

		
		dataTable.find(".tableRow").each(function(){
			//$(".tableRow").each(function(){
			        var currentRow=$(this);

			        var col1_value=currentRow.find("td:eq(0)").text();//index
			        var col2_value=currentRow.find("td:eq(1)").text();//area code
			        var col3_value=currentRow.find("td:eq(2)").text();//start range
			        var col4_value=currentRow.find("td:eq(3)").text();//end range
			        var col5_value=currentRow.find("td:eq(4)").text();//operator

			        removeDuplicates(col5_value);
			        var obj={};
			        obj.col1=col1_value;
			        obj.col2=col2_value;
			        obj.col3=col3_value;
			        obj.col4=col4_value;
			        obj.col5=col5_value;

			        arrData.push(obj);

		});
		selectUniqueOperator();
		
		function removeDuplicates(arr){
		  if(operator_Unique.indexOf(arr) == -1){
			  operator_Unique.push(arr);
			  //$("#errInput").append("adding " + arr);
		   }
		};
		
		function selectUniqueOperator(){
			var selectOperator = $(":input[name=operatorTable]");
			for(var i=0; i<operator_Unique.length; i++)
			{
				//$("#errInput").append("+for-" + operator_Unique[i] + "for+");
				var optionOperator = "<option class='oTable' value='ts'>" + operator_Unique[i] + "</option>";
				selectOperator.append(optionOperator);
			}
		};
			
		$("form[name=tableForm]").change(changeTable);
		
		var startRangeTable ="";
/*		$("form[name=tableForm] :input[name=selectStartRange]").keypress(function(event){
			var lastChar="";
			var key = event.which;
			if(!(key ==8 || key ==0)){
				lastChar=String.fromCharCode(key);
			}
			
			startRangeTable = $(":input[name=selectStartRange]").val() + lastChar;
			
			changeTable();
		});// end of $("form[name=tableForm] :input[name=selectStartRange]").keypress

*/		
		$("form[name=tableForm] :input[name=selectStartRange]").on('input',function () { 
			changeTable();
        });	
		
//		$("form[name=tableForm]").change( function(){
		function changeTable(){
			var selectAreaTable = $(":input[name=selectAreaTable]").val();
			var operatorTable = $(":input[name=operatorTable] option:selected").text();
			startRangeTable = $(":input[name=selectStartRange]").val()

			//$( "#myselect option:selected" ).text();
			
	        var selectedRows = "";
			//$("#errInput").append("- " + selectAreaTable+ " " + operatorTable +" "+ startRangeTable +"--");

			//alert('change');
			//$("#errInput").append("dataTable-" + firstDataTable);
			for(var i=0; i<arrData.length; i++){
				var col1_value = arrData[i].col1;
				var col2_value = arrData[i].col2;
				var col3_value = arrData[i].col3;
		        var col4_value = arrData[i].col4;
		        var col5_value = arrData[i].col5;
		        
				if( (selectAreaTable == "ALL" || selectAreaTable == col2_value) &&
						col3_value.startsWith(startRangeTable) &&
						(operatorTable == "ALL" || operatorTable == col5_value)){
					 selectedRows = selectedRows + "<tr class='tableRow'>" +
						"<td>"+ col1_value + "</td>"+
						"<td>"+ col2_value + "</td>"+
						"<td>"+ col3_value + "</td>"+
						"<td>"+ col4_value + "</td>"+
						"<td>"+ col5_value + "</td>"+
						"</tr>";
					//$("#errInput").append("- " + col2_value + " " + col3_value + " " + col4_value + "--");
			
				}
			}
			$(".tableRow").each(function(){
	        	var currentRow=$(this);
				currentRow.remove();
			});
			dataTable.append(selectedRows);
			//$("#errInput").append(selectedRows);
		}
//		});//end of	$("form[name=tableForm]").change( function(){
		
		
			
			
/*
			dataTable.find(".tableRow").each(function(){
			//$(".tableRow").each(function(){
			        var currentRow=$(this);

			        var col1_value=currentRow.find("td:eq(0)").text();
			        var col2_value=currentRow.find("td:eq(1)").text();
			        var col3_value=currentRow.find("td:eq(2)").text();
			        
			        var obj={};
			        obj.col1=col1_value;
			        obj.col2=col2_value;
			        obj.col3=col3_value;

			        arrData.push(obj);
					
				
					if( selectAreaTable == col2_value ){
						var selectedRow = "<tr class='tableRow'>" +
								"<td>"+ col1_value + "</td>"+
								"<td>"+ col2_value + "</td>"+
								"<td>"+ col3_value + "</td>"+
								"</tr>";
				
								dataTable.append(selectedRow);
					}else{
						currentRow.remove();
					}
			});// end of loop dataTable.find
*/ 

	});
	</script>		

</div><!-- end of class="main-right" -->	
</div><!-- end of style="overflow:auto" -->

	
<div class="footer">2017
</p>mgrubovic@yahoo.com</p>
</div>	
	
</body>
</html>
