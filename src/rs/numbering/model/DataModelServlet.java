package rs.numbering.model;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import rs.numbering.format.Range;
import rs.numbering.jaxb.ListAreaCodeJaxb;
import rs.numbering.jaxb.OperationJaxb;
import rs.numbering.operation.SearchNumbers;
import rs.numbering.operation.SearchRanges;

/**
 * Application Lifecycle Listener implementation class DataModelServlet
 *
 */
@WebListener
public class DataModelServlet implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DataModelServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
		
    	List <Range> rangesMain;
		ServletContext sContext = event.getServletContext();
		
		String firstPlace = sContext.getInitParameter("url-geo");
		String firstFormat = "webCsvGeo";
		
		String jspPath = sContext.getRealPath("/data"); 
		String fileName = sContext.getInitParameter("file-geo-short");

		String secondPlace = jspPath + File.separator +  fileName;
		String secondFormat = "fileCsvGeo";

		System.out.println("ServletContextListener " + sContext.getInitParameter("url-geo"));
		DataManager dataManager = new DataManager();
		
		dataManager.setFirstPlace(firstPlace);		
		dataManager.setFirstFormat(firstFormat);
		
		dataManager.setSecondPlace(secondPlace);
		dataManager.setSecondFormat(secondFormat);
		
		dataManager.getRanges();
		rangesMain = dataManager.rangesMain;
		
		sContext.setAttribute("geoRange", rangesMain);
		sContext.setAttribute("dataSource", dataManager.urlDescription);
		
		SearchRanges.rangesBig= rangesMain;
		SearchNumbers.rangesBig = rangesMain;
		
		
		// list of area codes for SELECT element in forms
		ListAreaCodeJaxb jaxbList = null;
		String fileXmlAreaCodes = jspPath + File.separator +  "AreaCodes.xml";
		jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);
		sContext.setAttribute("areaCode", jaxbList);
		
		// 
		String fileXmlName = "RangesList.xml";
		String fileXmlPlace = jspPath + File.separator +  fileXmlName;
		File writeRange = new File(fileXmlPlace);
		sContext.setAttribute("xmlDataFile", writeRange);
		OperationJaxb.listRangeToXml(rangesMain, writeRange);
    }
}
