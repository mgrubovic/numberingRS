package rs.numbering.control;

import rs.numbering.operation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import rs.numbering.format.*;
import rs.numbering.operation.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllServlet
 */
@WebServlet("/ControllServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String hiddenParam = request.getParameter("select");
		System.out.println("hiddenParam is "+ hiddenParam);
		if(hiddenParam.equals("range")){
			String mgRequest= request.getParameter("mg");
			String startRangeRequest = request.getParameter("startRange");
			String endRangeRequest = request.getParameter("endRange");

			boolean goodRequest = true;
			SearchRanges searchRanges = new SearchRanges();
			goodRequest = searchRanges.isRequestGood(mgRequest, startRangeRequest, endRangeRequest);
			
			if(goodRequest){
				List <Range> list1 = new ArrayList<>();
				list1.add(searchRanges.rangeInput);
				searchRanges.compareRanges(list1);
				
				request.setAttribute("answerRange", searchRanges.answerLines);
				RequestDispatcher view = request.getRequestDispatcher("/ranges/RangeCheckResult.jsp");
				view.forward(request, response);
/*				
				for(String line:searchRanges.answerLines){
					out.println("<p>");
					out.println(line);
					out.println("</p>");
				}
*/				
			}else{
				out.println("<p>You put invalid value try again</p><br/>");
			}
		}else if(hiddenParam.equals("number")){
			String hiddenString = request.getParameter("numbersToSend");
			SearchNumbers searchNumbers = new SearchNumbers();
			searchNumbers.makeList(hiddenString);
			searchNumbers.makeAnswer();
			request.setAttribute("answers", searchNumbers.answerLines);
			RequestDispatcher view = request.getRequestDispatcher("/numbers/NumberCheckResult.jsp");
			view.forward(request, response);
		}else{
			System.out.println("No catch for " + " hiddenParam  "+ hiddenParam);
		}
		

	}

}
