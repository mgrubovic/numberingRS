package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;

public class SearchNumbers {
	
	public String appendEnd = "+!!+";
	StringBuilder outputString;
	public static List<Range> rangesBig;
	
	public StringBuilder findNumbers(List<Range> rangesBig, List<Range> rangesSmall){
		
		outputString = new StringBuilder();
		String outputLine;
		for(Range toCompare: rangesSmall){// Za svaki element iz manje tabele koja se koristi da se proveri stanje u vecoj tabeli
			int smallStartRange = Integer.parseInt(toCompare.startRange);
			//int smallEndRange = Integer.parseInt(toCompare.endRange);
			outputLine =" Subscriber number " + toCompare.mg +   "/" + smallStartRange + " does not exist." + appendEnd;
			for(Range inRanges: rangesBig){
					
				if(toCompare.mg.equals(inRanges.mg)){

					int bigStartRange = Integer.parseInt(inRanges.startRange);
					int bigEndRange = Integer.parseInt(inRanges.endRange);

					if( bigStartRange <= smallStartRange && smallStartRange<=bigEndRange){
						outputLine =" Subscriber number " + toCompare.mg +   "/" + smallStartRange 
								+" exists. <br/>It is in the range " 
								+ inRanges.mg +"/" + bigStartRange + " - "  + bigEndRange  + " and belongs to " 
								+ inRanges.operator + appendEnd;

					}

				}
			}//for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
			System.out.println( outputLine);
			outputString.append(outputLine);
		}
		return outputString;
	}

}


