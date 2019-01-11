package rs.numbering.model;

import java.util.*;
import java.util.Collections;

import rs.numbering.format.*;
import rs.numbering.jaxb.*;
import rs.numbering.operation.RangeComparator;
import rs.numbering.operation.SearchRanges;

/**
 * @author milosav.grubovic
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		MainApp mApp = new MainApp();
		mApp.readFileCsvGeo();
 	}
	

	public void readFileCsvGeo(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("fileCsvGeo");
		dm.setFirstPlace("C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Geo20180207.csv");
		dm.getRanges();
/*		
		for(Range itemRange :dm.rangesMain){
			System.out.println(itemRange.operator);
		}
*/		
	}
	
	public void searchRanges(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("fileCsvGeo");
		dm.setFirstPlace("C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Geo20180207.csv");
		dm.getRanges();
		Collections.sort(dm.rangesMain, new RangeComparator(RangeComparator.NATURAL));
		
		List <Range> rangesSmall = new ArrayList<>();
		Range test1 = new Range();
		test1.mg = "11";
		test1.startRange= "2111999";
		test1.endRange= "2112999";
		
		Range test2 = new Range();
		test2.mg = "11";
		test2.startRange= "2148000";
		test2.endRange= "2148000";
		
		rangesSmall.add(test1);
		rangesSmall.add(test2);
		
		SearchRanges schRanges = new SearchRanges();


	}
	
	
	// for testing Comparator class RangeComparator using custom short form of database output
	public void readDB(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("dbShort");
		String fileLocation = "C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Ostala pitanja - geografska numeracija\\" + 
								"Telekom veliki zahtevi 2017\\GeografskaDodeljeniBaza.csv";
		dm.setFirstPlace(fileLocation);
		dm.getRanges();
		
		Collections.sort(dm.rangesMain, new RangeComparator(RangeComparator.NATURAL));
		for(Range itemRange :dm.rangesMain){
			System.out.println(itemRange.mg + ", " + itemRange.startRange + ", " +itemRange.endRange + ", " +
								itemRange.fromDate + ", "  + itemRange.untilDate + ", "  +// itemRange.decisionDate + ", "  +
								itemRange.decisionNumber + ", "  + itemRange.operator);
		}
	}
	
//	for writing ArrayList in the xml file 
/*		
		ListRangeJaxb listRangeJaxb = new ListRangeJaxb(); 
		ArrayList <RangeJaxb> listRange = new ArrayList<>(); 
	for(Range range1: dm.rangesMain){
		//OperationJaxb.rangeToXml(new RangeJaxb(range1));
		
		listRange.add(new RangeJaxb(range1));
	}

	listRangeJaxb.setRangesJaxb(listRange);
	OperationJaxb.listRangeToXml(listRangeJaxb);
*/		
	
	
	
	//OperationJaxb.xmlToListRange();

	

}
