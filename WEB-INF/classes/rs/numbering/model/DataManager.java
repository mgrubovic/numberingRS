package rs.numbering.model;

import java.io.File;
import java.util.List;

import rs.numbering.format.*;
import rs.numbering.source.SourceReader;

public class DataManager {
	private String firstPlace;

	private String secondPlace;
	private String firstFormat;
	private String secondFormat;


	public String urlDescription="";
	public List <Range> rangesMain;
	
	public void getRanges(){

		//List <Range> ranges1;
		//String fileFormat="webCsvGeo";
		
		ReadRangeFactory factoryReader =  new ReadRangeFactory(firstFormat);
		ReadRange fromatReader = factoryReader.getFormatReader();
		SourceReader sourceReader = factoryReader.getSourceReader();
		rangesMain = sourceReader.takeData(firstPlace, fromatReader);
		System.out.println("reading web from DataManager" ); 

		if(rangesMain.size()>0){
			 urlDescription = "Table data taken from web address: " + firstPlace;
		}else{ //if range is not initialized from web page we are going to read data from data file on the server 
			//String jspPath = application.getRealPath("/");
			//url = jspPath + "data" + File.separator +  "Geo20180207.csv";
			
			urlDescription = "Table data may not be updated, they are taken from back-up file: " 
			+ secondPlace.substring(secondPlace.lastIndexOf(File.separator)+1);

			System.out.println("Data file is " + secondPlace);

			///fileFormat="fileCsvGeo";
		
			factoryReader =  new ReadRangeFactory(secondFormat);
			fromatReader = factoryReader.getFormatReader();

			sourceReader = factoryReader.getSourceReader();
			rangesMain = sourceReader.takeData(secondPlace, fromatReader);
		
			System.out.println("reading file from DataManager" ); 

		}

	}
	
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

}
