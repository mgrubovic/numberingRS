package rs.numbering.model;

import java.util.List;

import rs.numbering.format.*;
/**
 *Purpose of the DataManager class is to prepare main data for the application and for the table.
 * It's looking for row data in two places first and second(back-up).
 */
public class DataManager {
	//these attributes are set by main application (Initialization Servlet) it is the location of data 
	private String firstPlace;
	private String secondPlace;
	
	//these attributes are set by main application (Initialization Servlet), 
	//it describes format of the row data to translate into a Range object.
	private String firstFormat;
	private String secondFormat;


	private String urlDescription="";
	
	public List <Range> getRanges(String format, String place){
		ReadRangeFactory factoryReader =  new ReadRangeFactory(format);
		return factoryReader.takeData(place);
	}
	
	public List <Range> getRanges(){
		List <Range> rangesMain = getRanges(firstFormat, firstPlace);
		if(!rangesMain.isEmpty()){
			setUrlDescription("The table data are taken from web address: " + firstPlace);
			return rangesMain;
		}else{
			rangesMain = getRanges(secondFormat, secondPlace);
			setUrlDescription("The table data are taken from backup file: " + secondPlace);
			return rangesMain;
		}
	}

/*
 * 			
			setUrlDescription("Table data may not be updated, they are taken from back-up file: " 
			+ secondPlace.substring(getSecondPlace().lastIndexOf(File.separator)+1));

			System.out.println("Data file is " + getSecondPlace());
	
 */

	public String getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(String firstPlace) {
		this.firstPlace = firstPlace;
	}

	public String getSecondPlace() {
		return secondPlace;
	}

	public void setSecondPlace(String secondPlace) {
		this.secondPlace = secondPlace;
	}

	public String getFirstFormat() {
		return firstFormat;
	}

	public void setFirstFormat(String firstFormat) {
		this.firstFormat = firstFormat;
	}

	public String getSecondFormat() {
		return secondFormat;
	}

	public void setSecondFormat(String secondFormat) {
		this.secondFormat = secondFormat;
	}

	public String getUrlDescription() {
		return urlDescription;
	}

	public void setUrlDescription(String urlDescription) {
		this.urlDescription = urlDescription;
	}

}
