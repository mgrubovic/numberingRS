package rs.numbering.model;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import rs.numbering.format.Range;
import rs.numbering.hibernate.OperationHibRange;
import rs.numbering.jaxb.ListAreaCodeJaxb;
import rs.numbering.jaxb.OperationJaxb;


/**
 * Application 
 *
 */
public class DataModelServlet implements ServletContextListener {


    /**
     * Default constructor. 
     */
    public DataModelServlet() {
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
    
	public void contextInitialized(ServletContextEvent event)  { 
		List <Range> rangesMain;
		
		ServletContext sContext = event.getServletContext();
		String filePath = sContext.getRealPath("/data");
		
		DataManager dataManager = new DataManager();
		
		String firstPlace = sContext.getInitParameter("url-geo");
		String firstFormat = "webCsvGeo";
		dataManager.setFirstPlace(firstPlace);
		dataManager.setFirstFormat(firstFormat);
		
		String fileName = sContext.getInitParameter("file-geo");
		String secondPlace = filePath + File.separator +  fileName;
		String secondFormat = "fileCsvGeo";
		dataManager.setSecondPlace(secondPlace);
		dataManager.setSecondFormat(secondFormat);
		
		rangesMain = dataManager.getRanges();
	
		sContext.setAttribute("geoRange", rangesMain);
		sContext.setAttribute("dataSource", dataManager.getUrlDescription());

		//Initialize HSQL database and prepare summary views
		OperationHibRange operateHib = new OperationHibRange();
		operateHib.intitHsql(rangesMain);
		List <Range> areaList = operateHib.getSummary("area");
		List <Range> operatorList = operateHib.getSummary("operator");

		sContext.setAttribute("areaList", areaList);
		sContext.setAttribute("operatorList", operatorList);

	
		// list of area codes for options in the SELECT element used by moduls/OptionsAreaCode.jsp
		// used by summary/AreaCodeDistribution.jsp
		ListAreaCodeJaxb jaxbList = null;
		String fileXmlAreaCodes = filePath + File.separator +  "AreaCodes.xml";
		jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);
		sContext.setAttribute("areaCode", jaxbList);
		
	}

}
