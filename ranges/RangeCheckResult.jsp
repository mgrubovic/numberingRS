<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="rs.numbering.format.*" %>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>

<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style_main.css"/>
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

	<p>
		Network  <%=request.getParameter("mg") %>
		<br/>
		Start of range <%=request.getParameter("startRange") %>
		<br/>
		End of range <%= request.getParameter("endRange") %>
	</p>
	<%
		Range rangeInput = new Range();
		String mgRequest= request.getParameter("mg");
		String startRangeRequest = request.getParameter("startRange");
		String endRangeRequest = request.getParameter("endRange");
		boolean goodRequest = true;
		
		if(rangeInput.isTelNumber(mgRequest)){
			rangeInput.mg= mgRequest;
		}else{
			goodRequest=false;
			out.println("<p>You put invalid value for net "+ mgRequest + " </p>");

		}
		
		if(rangeInput.isTelNumber(startRangeRequest)){
			if(rangeInput.isLengthGood(startRangeRequest, 5, 7)){
				rangeInput.startRange = startRangeRequest;
			}else{
				out.println("<p>Start range length is invalid, it should be between 5 and 7 digits   "+ startRangeRequest + "</p>");
				goodRequest=false;

			}			
		}else{
			goodRequest=false;
			out.println("<p>You put invalid value for start range  "+ startRangeRequest + "</p>");
		}
		
		if(rangeInput.isTelNumber(endRangeRequest)){
			if(rangeInput.isLengthGood(endRangeRequest, 5, 7)){
				rangeInput.endRange = endRangeRequest;
			}else{
				out.println("<p>End range length is invalid, it should be between 5 and 7 digits   "+ endRangeRequest + "</p>");
				goodRequest=false;

			}			
		}else{
			goodRequest=false;
			out.println("<p>You put invalid value for end range  "+ endRangeRequest + "</p>");
		}
		
		List <Range> rangesAll = (List <Range>)application.getAttribute("geoRange");	
		System.out.println("Input number " + rangeInput.mg + " " + rangeInput.startRange + " " + rangeInput.endRange);
		if(goodRequest){
			List <Range> list1 = new ArrayList<>();
			list1.add(rangeInput);
//			System.out.println("list 1 " + list1);
/*
			if( rangesAll==null){
		
				String url ="http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv";
		
				WebReader webReader = new WebReader();
		
				String fileFormat="webCsvGeo";
		
				ReadRangeFactory factoryReader =  new ReadRangeFactory(fileFormat);
				ReadRange fromatReader = factoryReader.getFormatReader();
				webReader.takeData(url, fromatReader);
				rangesAll= webReader.listRanges;	
				application.setAttribute("geoRange", rangesAll);
			}
			SearchRanges.rangesBig = rangesAll;
*/			
			SearchRanges searchRanges = new SearchRanges();
			
/*			MyNumber myNumber = new MyNumber();
			System.out.println("MyNumber class " + myNumber.appendEnd);
*/
			StringBuilder sb = searchRanges.compareRanges(SearchRanges.rangesBig, list1);
			
			int start = 0;
			int end = sb.indexOf(searchRanges.appendEnd);
			while(end != -1){
				String row = sb.substring(start, end);
				out.println("<br/>");
				out.println(row);
				start = end +4;
				end = sb.indexOf(searchRanges.appendEnd, start);
			}
		}else{
			out.println("<p>You put invalid value try again</p><br/>");
		}
	%>
</div><!-- end of class="main-right" -->	
</div><!-- end of style="overflow:auto" -->

	
<div class="footer">2017
</p>mgrubovic@yahoo.com</p>
</div>			
	
</body>
</html>