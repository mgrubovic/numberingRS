package rs.numbering.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.numbering.format.Range;

/**
 * Servlet implementation class CheckNumberServlet
 */
//@WebServlet("/CheckNumberServlet")
public class CheckNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append("Served at: ").append(request.getContextPath()).append(" Hey YOU");
		out.println("<p>Network " + request.getParameter("mg") );
		out.println("<br/> Start of range" + request.getParameter("startRange") );
		out.println("<br /> End of range" + request.getParameter("endRange") );
		out.println("</p>");

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
		

		if(goodRequest){
			List <Range> list1 = new ArrayList<>();
			list1.add(rangeInput);

			SearchRanges searchRanges = new SearchRanges();
			List <String> resultList = searchRanges.rangesAvilable(SearchRanges.rangesBig, list1);
			for(String resultLine: resultList){
				out.println("<br/>");
				out.println(resultLine);
				out.println("<p> This was result list</p>");
			}
		}else{
			out.println("<p>You put invalid value try again</p><br/>");
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
