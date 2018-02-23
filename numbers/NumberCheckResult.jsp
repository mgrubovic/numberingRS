<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="rs.numbering.format.*" %>
<%@ page import="rs.numbering.source.*, rs.numbering.operation.*"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/style_main.css"/>


<meta http-equiv="Content-Type" content="text/html; charset=Unicode">
<title>Numbering</title>
</head>
<body>
<div class="header">
  <h1>Telephone numbers in Serbia</h1>
</div>

<div style="overflow:auto">
 <div class="menu">
    <div class="menuitem"><a href="../index.html">About</a></div>
    <div class="menuitem"><a href="CheckNumber.jsp">Find numbers</a></div>
    <div class="menuitem"><a href="../ranges/CheckRange.jsp">Find ranges</a></div>
    <div class="menuitem"><a href="../compare.html">Compare</a></div>
    <div class="menuitem"><a href="../configuration/mainConfig.html">Configuration</a></div>
 </div>
 <div class="main-right">
	<p>
		Net is <%=request.getParameter("net") %>
		<br/>
		Subscriber number is <%=request.getParameter("newSubscriber") %>
		<br/>
		Hidden <%= request.getParameter("numbersToSend") %>
	</p>
	<%
		List <Range> lookForList = new ArrayList<>();
	
		String hiddenString = request.getParameter("numbersToSend");
		String [] data = hiddenString.split("!!!");// get numbers from hidden parameter ;
		if( data.length>0)
		{
			
			for(int i=0; i< data.length; i++){
				Range rangeHidden = new Range();
				String [] innerData = data[i].split(";");
				try{
					rangeHidden.mg= innerData[0];
					if(innerData.length >1){
						rangeHidden.startRange = innerData[1];
						String subscriberRequest = rangeHidden.startRange;
						
/*
						for debugging purpose
		
						out.println("<p>Hidden MG "+ rangeHidden.mg + " </p>");
						out.println("<p>Hidden subscriber number "+ rangeHidden.startRange + " </p>");
*/
						if(rangeHidden.isTelNumber(subscriberRequest)){
							if(rangeHidden.isLengthGood(subscriberRequest, 5, 7)){
								lookForList.add(rangeHidden);
							}else{
								out.println("<p>Number length is invalid, it should be between 5 and 7 digits   "+ subscriberRequest + "</p>");
							}
						}else{
							out.println("<p>You entered invalid value for the subscriber number  "+ subscriberRequest + "</p>");
						}
					}else if(data.length>1){
						out.println("<p>You didn't enter the number  " + rangeHidden.mg + "</p>");
					} // end of if(innerData.length >1) ... else if

				}catch(NumberFormatException ex){
					System.out.println("Error "+ ex.toString());
				}
			}// end of for(int i=0;....

		}

		

	%>
	<%
		Range range1 = new Range();
		String netRequest= request.getParameter("net");
		String subscriberRequest = request.getParameter("newSubscriber");
		boolean goodRequest = true;

		
		if(range1.isTelNumber(netRequest)){
			range1.mg= netRequest;
		}else{
			goodRequest=false;
			out.println("<p>You entered invalid value for network "+ netRequest + " </p>");

		}
		
		if(range1.isTelNumber(subscriberRequest)){
			if(range1.isLengthGood(subscriberRequest, 5, 7)){
				range1.startRange = subscriberRequest;
			}else{
				out.println("<p>Number length is invalid, it should be between 5 and 7 digits   "+ subscriberRequest + "</p>");
				goodRequest=false;
			}
		}else{
			goodRequest=false;
			out.println("<p>You entered invalid value for the subscriber number  "+ subscriberRequest + "</p>");
		}

		// not to duplicate parameter requests, not to send input fields values
		int index = lookForList.size();
		Range lastIndex ;
		boolean inputSend = true; // not to duplicate parameter requests, not to send input fields values

		if(goodRequest && index > 0){
			lastIndex = lookForList.get(index-1); 
			if( range1.mg.equals(lastIndex.mg) && range1.startRange.equals(lastIndex.startRange)	){
				inputSend = false;
			}
		}
		
		if(goodRequest){
			if(inputSend){
				lookForList.add(range1);
			}
			
		}
/*		
		else{
			out.println("<p>You entered invalid value try again</p><br/>");
		}
*/
		SearchNumbers searchNumbers = new SearchNumbers();
		StringBuilder sb = searchNumbers.findNumbers(SearchNumbers.rangesBig, lookForList);
		
		int start = 0;
		int end = sb.indexOf(searchNumbers.appendEnd);
		while(end != -1){
				String row = sb.substring(start, end);
				out.println("<p>");
				out.println(row);
				out.println("</p>");

				start = end +4;
				end = sb.indexOf(searchNumbers.appendEnd, start);
		}

	%>
</div><!-- end of class="main-right" -->	
</div><!-- end of style="overflow:auto" -->

<div class="footer">2017
</p>mgrubovic@yahoo.com</p>
</div>		
	
</body>
</html>