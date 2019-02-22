package rs.numbering.model;

import java.util.*;

import rs.numbering.format.*;
import rs.numbering.operation.RangeComparator;

/**
 * @author milosav.grubovic
 *
 */
public class MainApp {

	List <Range> rangesMain;
	
	public static void main(String[] args) {
		MainApp mApp = new MainApp();
		mApp.readFileCsvGeo();
 	}
	

	public void readFileCsvGeo(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("fileCsvGeo");
		dm.setFirstPlace("C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Geo20180207.csv");
		rangesMain = dm.getRanges();
	
	}
	
	public void searchRanges(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("fileCsvGeo");
		dm.setFirstPlace("C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Geo20180207.csv");
		dm.getRanges();
		Collections.sort(rangesMain, new RangeComparator(RangeComparator.NATURAL));
		
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
		
	}
	
	
	// for testing Comparator class RangeComparator using custom short form of database output
	public void readDB(){
		DataManager dm= new DataManager();
		dm.setFirstFormat("dbShort");
		String fileLocation = "C:\\Numeracija\\01 Dodela Numeracije\\1 - Geografska numeracija\\Ostala pitanja - geografska numeracija\\" + 
								"Telekom veliki zahtevi 2017\\GeografskaDodeljeniBaza.csv";
		dm.setFirstPlace(fileLocation);
		dm.getRanges();
		
		Collections.sort(rangesMain, new RangeComparator(RangeComparator.NATURAL));
		for(Range itemRange :rangesMain){
			System.out.println(itemRange.mg + ", " + itemRange.startRange + ", " +itemRange.endRange + ", " +
								itemRange.fromDate + ", "  + itemRange.untilDate + ", "  +// itemRange.decisionDate + ", "  +
								itemRange.decisionNumber + ", "  + itemRange.operator);
		}
	}

}
