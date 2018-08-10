<%@ page language="java" contentType="aplication/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@ page import="java.io.*"%>
<%@ page import="rs.numbering.jaxb.*"%>
<%-- ListRangeJaxb listRangeJaxb =(ListRangeJaxb)application.getAttribute("xmlData");
		out.print(listRangeJaxb);
	--%>
<%
		File xmlFileRange =  (File)application.getAttribute("xmlDataFile");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(xmlFileRange));
			
			String line = reader.readLine();
			while(line != null){
				out.println(line);
				line = reader.readLine();
			} 
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("file not find name  " + xmlFileRange);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	%>
