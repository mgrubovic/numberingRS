package rs.numbering.model;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import rs.numbering.format.Range;
import rs.numbering.format.ReadRange;
import rs.numbering.hibernate.OperationHibRange;
import rs.numbering.jaxb.ListAreaCodeJaxb;
import rs.numbering.jaxb.OperationJaxb;
import rs.numbering.source.SourceReader;

/**
 * Application 
 *
 */
@WebListener
public class DataModelServlet implements ServletContextListener {

	protected static List <Range> rangesMain;
	String filePath = ""; 
	String urlDescription = "";
	
    /**
     * Default constructor. 
     */
    public DataModelServlet() {
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
    
	public void contextInitialized(ServletContextEvent event)  { 
		
		
		ServletContext sContext = event.getServletContext();
		filePath = sContext.getRealPath("/data");
		
		String firstPlace = sContext.getInitParameter("url-geo");
		String firstFormat = "webCsvGeo";
		urlDescription = "Table data are taken from web address: " + firstPlace;
		DataManager dm = new DataManager();
		rangesMain = dm.getRanges( firstFormat, firstPlace);
		
		// if app is not able to read from the web then it reads from a backup file
		if(rangesMain.isEmpty()){
			String fileName = sContext.getInitParameter("file-geo");
			String secondPlace = filePath + File.separator +  fileName;
			String secondFormat = "fileCsvGeo";
			urlDescription = "Table data are taken from backup file: " + fileName;
			rangesMain = dm.getRanges( secondFormat, secondPlace);
		}
		
	
		sContext.setAttribute("geoRange", rangesMain);
		sContext.setAttribute("dataSource", urlDescription);

		//Initialize HSQL database and preparing summary views
		OperationHibRange operateHib = new OperationHibRange();
		operateHib.intitHsql(rangesMain);		
		sContext.setAttribute("operateHib", OperationHibRange.sumMap);
		sContext.setAttribute("operatorMap", OperationHibRange.operatorMap);
	
		// list of area codes for SELECT element in forms
		ListAreaCodeJaxb jaxbList = null;
		String fileXmlAreaCodes = filePath + File.separator +  "AreaCodes.xml";
		jaxbList = OperationJaxb.xmlToListAreaCode(fileXmlAreaCodes);
		sContext.setAttribute("areaCode", jaxbList);
		
		// 
		String fileXmlName = "RangesList.xml";
		String fileXmlPlace = filePath + File.separator +  fileXmlName;
		File writeRange = new File(fileXmlPlace);
		OperationJaxb.listRangeToXml(rangesMain, writeRange);
		sContext.setAttribute("xmlDataFile", writeRange);

	}

	// for deletion
	public List <Range> getRanges(String format, String place){

		ReadRangeFactory factoryReader =  new ReadRangeFactory(format);
		ReadRange fromatReader = factoryReader.getFormatReader();
		SourceReader sourceReader = factoryReader.getSourceReader();
		List <Range> forRangesMain = sourceReader.takeData(place, fromatReader);
		return forRangesMain;
	}
}
