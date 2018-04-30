/**
 
 */
package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;

/**
 * @author milosav.grubovic
 *
 */

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
				//System.out.println( outputLine);
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
				//System.out.println( outputLine);
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
						//System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
					if(smallEndRange>=bigStartRange && smallEndRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" +  smallStartRange + " - " 
									+ smallEndRange + " end is within range " + toCompare.mg +  "/" 	+ bigStartRange  
									+ " - " + bigEndRange  + appendEnd;
						//System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
				}
			}//for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
		}
		
		if(	freeRange){
			outputLine = "Range you entered is free for assignment " +  appendEnd;
		//System.out.println( outputLine);
		outputString.append(outputLine);	
		}
		return outputString;
	}
	
	public StringBuilder findRanges(List<Range> rangesBig, List<Range> rangesSmall){
		
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
				//System.out.println( outputLine);
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
				//System.out.println( outputLine);
				outputString.append(outputLine);
			}
			
			// Ispituje se svaki element u velikoj zvanicnoj tabeli da li se novi opseg nalazi u okviru postojeceg
			// ukoliko se pocetak  ili kraj novog opsega (toComapare) nalazi unutar postojeceg prijavljuje gresku
			Range closestRange = binarySearch( rangesBig, toCompare);
	        //System.out.println( "closestRange is " + closestRange);
			int index = rangesBig.indexOf(closestRange);
	        System.out.println( "previous range is " + rangesBig.get(index-1));
	        System.out.println( "closest range is  " + closestRange);
	        System.out.println( "------- range is  " + toCompare + "-----");
	        System.out.println( "next range is     " + rangesBig.get(index+1));



			for(Range inRanges: rangesBig){
				if(toCompare.mg.equals(inRanges.mg)){

					int bigStartRange = Integer.parseInt(inRanges.startRange);
					int bigEndRange = Integer.parseInt(inRanges.endRange);

					if(smallStartRange>=bigStartRange && smallStartRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" + smallStartRange + " - " 
								+ smallEndRange + " start is within  range " +  toCompare.mg +  "/" + bigStartRange  
								+ " - " + bigEndRange  + appendEnd;
						//System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
					if(smallEndRange>=bigStartRange && smallEndRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" +  smallStartRange + " - " 
									+ smallEndRange + " end is within range " + toCompare.mg +  "/" 	+ bigStartRange  
									+ " - " + bigEndRange  + appendEnd;
						//System.out.println( outputLine);
						outputString.append(outputLine);
						freeRange=false;
					}
				}
			}//for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
		}
		
		if(	freeRange){
			outputLine = "Range you entered is free for assignment " +  appendEnd;
		//System.out.println( outputLine);
		outputString.append(outputLine);	
		}
		return outputString;
	}

	public Range binarySearch(List<Range> rangesBig, Range toCompare){
		RangeComparator rangeComparator = new RangeComparator(RangeComparator.NATURAL);
		Collections.sort(rangesBig, rangeComparator);
		Range closestRange=null;
		int lo=0;
		int hi=rangesBig.size()-1;
	       while (lo < hi) {
	            int mid = (hi + lo) / 2;
		        //System.out.println( "rangesBig.get(mid) " + rangesBig.get(mid));

				int comparationResult = rangeComparator.compareNatural(rangesBig.get(mid), toCompare);

	            if (comparationResult<0) {
	                lo = mid + 1;

	            } else if (comparationResult>0) {
	            	hi = mid - 1;
	            } else {
	            	closestRange =  rangesBig.get(mid);
	            	return closestRange;
	            }
			    //System.out.println( "lo is " + lo + "hi is " + hi + "comparationResult " + comparationResult) ;
		        //System.out.println( "closestRange is " + closestRange);

	        }
	       // return (a[lo] - value) < (value - a[hi]) ? a[lo] : a[hi];
	       
	        if(rangeComparator.compareNatural(rangesBig.get(lo), toCompare) < rangeComparator.compareNatural(toCompare,rangesBig.get(hi))){
	        	closestRange =  rangesBig.get(lo);
	        }else{
	        	closestRange =  rangesBig.get(hi);
	        }
			int index = rangesBig.indexOf(closestRange);

	        rangeComparator.compareNaturalExistance(rangesBig.get(index-1), toCompare);
	        rangeComparator.compareNaturalExistance(rangesBig.get(index), toCompare);
	        rangeComparator.compareNaturalExistance(rangesBig.get(index+1), toCompare);
	        compareNaturalExistance(rangesBig, toCompare, index);



	        System.out.println( "closestRange is " + closestRange);
	        return closestRange;
	}
	
	public void compareNaturalExistance(List <Range> existing, Range checking, int index ) {
		int startIndex=index-1;
		int endIndex=index+1;
	
		if(index==0){
			startIndex = index;
		}
		if(index==existing.size()-1){
			endIndex=index;
		}
		int countIndex = startIndex;
		for(int i= startIndex; i<= endIndex ;i++){
			Range closestRange = existing.get(i);
			int compareNumber = closestRange.mg.compareTo(checking.mg);
			if(compareNumber==0){
				int compareNumberStart = closestRange.startRange.compareTo(checking.startRange);
				int compareNumberEnd = closestRange.endRange.compareTo(checking.startRange);
				System.out.println("*** compareNumberStart" + compareNumberStart + " compareNumberEnd" +compareNumberEnd);
	
				if( compareNumberStart<0 && compareNumberEnd>0){
					System.out.println("*** " + checking + " in");
					System.out.println("*** " + existing + " ");
	
				}
			    //return compareNumberStart < compareNumberEnd ? compareNumberStart : compareNumberEnd;
	
			}else{
				//return compareNumber;
			}
		}// end of for(int i= startIndex; i< endIndex; i++){
	}

}

