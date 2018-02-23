package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;

public class SearchRanges {
	
	public String appendEnd = "+!!+";
	StringBuilder outputString;
	public static List<Range> rangesBig;
	
	public StringBuilder compareRanges(List<Range> rangesBig, List<Range> rangesSmall){
		
		outputString = new StringBuilder();
		String outputLine;
		boolean freeRange=true;
		for(Range toCompare: rangesSmall){// Za svaki element iz manje tabele koja se koristi da se proveri stanje u vecoj tabeli
			
			int smallStartRange = Integer.parseInt(toCompare.startRange);
			int smallEndRange = Integer.parseInt(toCompare.endRange);
			int subractRange = smallEndRange - smallStartRange+1;

			
			if(subractRange<=0){
				outputLine = "!!! Error start  "  +toCompare.mg + "/" + smallStartRange  +
						 	  " is greater then end " + toCompare.mg + "/" + smallEndRange +appendEnd;
				System.out.println( outputLine);
				outputString.append(outputLine);
				freeRange=false;
			}
/*		not relevant for this purpose
	  		
	  		if(subractRange%1000 !=0){
					outputLine = "!!! Greska opseg " + toCompare.mg + "/" +
							smallStartRange + " - " + smallEndRange  + " nije odgovarajuce velicine " + subractRange + appendEnd;
					System.out.println( outputLine);
					outputString.append(outputLine);
				}
*/
			if(subractRange>10000){
				outputLine = "!!! Warrning  " + toCompare.mg + "/" +
						smallStartRange + " - " + smallEndRange  + " is very big range " + subractRange + appendEnd;
				System.out.println( outputLine);
				outputString.append(outputLine);
			}
			
			// Ispituje se svaki element u velikoj zvanicnoj tabeli da li se novi opseg nalazi u okviru postojeceg
			// ukoliko se pocetak  ili kraj novog opsega (toComapare) nalazi unutar postojeceg prijavljuje gresku
			for(Range inRanges: rangesBig){
					
				if(toCompare.mg.equals(inRanges.mg)){

					int bigStartRange = Integer.parseInt(inRanges.startRange);
					int bigEndRange = Integer.parseInt(inRanges.endRange);

					if(smallStartRange>=bigStartRange && smallStartRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" + smallStartRange + " - " 
								+ smallEndRange + " start is within  range " +  toCompare.mg +  "/" + bigStartRange  
								+ " - " + bigEndRange  + appendEnd;
						System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
					if(smallEndRange>=bigStartRange && smallEndRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" +  smallStartRange + " - " 
									+ smallEndRange + " end is within range " + toCompare.mg +  "/" 	+ bigStartRange  
									+ " - " + bigEndRange  + appendEnd;
						System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
				}
			}//for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
		}
		
		if(	freeRange){
			outputLine = "Range you entered is free for assignment " +  appendEnd;
		System.out.println( outputLine);
		outputString.append(outputLine);	
		}
		return outputString;
	}
/*	public boolean withinRange(String mg, int num1, int num2, List<Range> rangesBig ){ 
		//num1 kraj prethodnog opsega
		//num2 pocetak narednog opsega
		boolean inRange=false;
		for(Range inRanges: rangesBig){// Ispituje se svaki element u velikoj zvanicnoj tabeli
			int bigStartRange = Integer.parseInt(inRanges.startRange);
			int bigEndRange = Integer.parseInt(inRanges.endRange);
			
			if(mg.equals(inRanges.mg)){
				if(num1>=bigStartRange && num1<=bigEndRange && !(num2>=bigStartRange && num2<bigEndRange)){
				
					System.out.println("!!! Nemoj da spajas pocetak " + inRanges.mg + "/" + num1 
								+ " sa krajem " + num2 + " - " + bigStartRange
								+ "! " + bigEndRange + " start in existing  range " );
					inRange=true;
					return inRange;
				}
				
			}// if(mg.equals(...
		}//for(Range inRanges: ...
		return inRange;
	}
*/
}
