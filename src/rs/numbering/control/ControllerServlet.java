package rs.numbering.control;

import rs.numbering.operation.*;
import rs.numbering.format.*;


import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		ServletContext sContext = request.getServletContext();
		List <Range> rangesMain = (List<Range>) sContext.getAttribute("geoRange");
		
		String hiddenParam = request.getParameter("select");
		if(hiddenParam.equals("range")){
			String mgRequest= request.getParameter("mg");
			String startRangeRequest = request.getParameter("startRange");
			String endRangeRequest = request.getParameter("endRange");

			SearchRanges searchRanges = new SearchRanges(rangesMain);
			List <String> answerLines = searchRanges.getAnswers(mgRequest, startRangeRequest, endRangeRequest);

			request.setAttribute("answerRange", answerLines);
			RequestDispatcher view = request.getRequestDispatcher("/ranges/RangeCheckResult.jsp");
			view.forward(request, response);

		}else if(hiddenParam.equals("number")){
			String hiddenString = request.getParameter("numbersToSend");
			SearchNumbers searchNumbers = new SearchNumbers(rangesMain);
			List <String> answerLines = searchNumbers.getAnswers(hiddenString);
			request.setAttribute("answers", answerLines);
			RequestDispatcher view = request.getRequestDispatcher("/numbers/NumberCheckResult.jsp");
			view.forward(request, response);
		}else{
			System.out.println("No catch for " + " hiddenParam  "+ hiddenParam);
		}

	}

}
