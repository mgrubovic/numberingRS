package rs.numbering.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rs.numbering.format.Range;
import rs.numbering.jaxb.OperationJaxb;

/**
 * Servlet implementation class OutputDataCtrlServlet
 */


public class OutputDataCtrlServlet extends HttpServlet {
	
	public OutputDataCtrlServlet() {
		super();

	}

	private static final long serialVersionUID = 54L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletContext sContext = request.getServletContext();
    	String filePath = sContext.getRealPath("/data");
    	List <Range> rangesMain = (List<Range>) sContext.getAttribute("geoRange");

    	String fileXmlName = "RangesList.xml";
		String fileXmlPlace = filePath + File.separator +  fileXmlName;
		File writeRange = new File(fileXmlPlace);
		OperationJaxb.listRangeToXml(rangesMain, writeRange);
		
		RequestDispatcher view = request.getRequestDispatcher("/data/RangesList.xml");
		view.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
