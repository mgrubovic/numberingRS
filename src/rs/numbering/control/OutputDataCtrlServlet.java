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

import org.json.simple.JSONObject;

import rs.numbering.format.Range;
import rs.numbering.jaxb.OperationJaxb;
import rs.numbering.json.simple.RangeJsonSimple;

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
    	String responseTo= "/numbers/CheckNumber.jsp";
    	ServletContext sContext = request.getServletContext();
    	String dataFolder = "/data";
    	String filePath = sContext.getRealPath(dataFolder) + File.separator;

    	List <Range> rangesMain = (List<Range>) sContext.getAttribute("geoRange");

    	String type = request.getParameter("file");
    	if(type.equals("json")){
    		responseTo = makeJsonFile(rangesMain, filePath);
    	}else if (type.equals("xml")){
    		responseTo = makeXmlFile(rangesMain, filePath);
    	}
    	RequestDispatcher view = request.getRequestDispatcher(responseTo);
		view.forward(request, response);
	}

	private String makeXmlFile(List <Range> rangesMain, String filePath ) {
    	String fileXmlName = "RangesList.xml";
		String fileXmlPlace = filePath +fileXmlName;
		File writeRange = new File(fileXmlPlace);
		OperationJaxb.listRangeToXml(rangesMain, writeRange);
		return "/data/RangesList.xml";
	}

	private String makeJsonFile(List <Range> rangesMain, String filePath) {
		System.out.println("You click on Json file");
    	String fileJsonName = "RangesList.json";
		String fileJsonPlace = filePath +fileJsonName;
		RangeJsonSimple rjs= new RangeJsonSimple();
		JSONObject jo = rjs.prepareJson(rangesMain);
		rjs.jsonToFile(jo, fileJsonPlace);
		return "/data/RangesList.json";

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
