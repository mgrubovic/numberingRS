/**
 
 */
package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;
import rs.numbering.model.DataModelServlet;

/**
 * @author milosav.grubovic
 *
 */

public class SearchRanges {
	
	public String appendEnd = "+!!+";
	StringBuilder outputString;
	public static List<Range> rangesBig;
	public List <String> answerLines;
	public Range rangeInput;
	
	
	
	public SearchRanges() {
		answerLines = new ArrayList<String>();
		rangesBig=DataModelServlet.rangesMain;
	}
	

	public SearchRanges(List<Range> rangesBig) {
		answerLines = new ArrayList<String>();
		SearchRanges.rangesBig=rangesBig;
	}
	
	public void getAnswers(String mgRequest,String startRangeRequest, String endRangeRequest){
		
		boolean goodRange = true;
		goodRange = isNumberGood(startRangeRequest, "start");
		goodRange = isNumberGood(endRangeRequest, "end");
		
		if(goodRange){
			rangeInput = new Range();
			rangeInput.setMg(mgRequest);
			rangeInput.setStartRange(startRangeRequest); 
			rangeInput.setEndRange(endRangeRequest);
			
			List <Range> list1 = new ArrayList<>();
			list1.add(rangeInput);
			compareRanges(list1);
			int rangeIndex = binarySearch(rangesBig, rangeInput);
			prepareAnswers(rangeIndex, rangeInput);
			}
	}

	public boolean isNumberGood(String rangeNumber, String position){
		boolean goodRequest=true;
		if(Range.isTelNumber(rangeNumber)){
			if(Range.isLengthGood(rangeNumber, 5, 7)){
				return goodRequest;
			}else{
				answerLines.add("<p>"+ position + " range length is invalid, it should be between 5 and 7 digits   "+ rangeNumber + "</p>");
				goodRequest=false;
			}			
		}else{
			answerLines.add("<p>You put invalid value for " + position + " range  "+ rangeNumber + "</p>");
			goodRequest=false;
		}
		return goodRequest;
	}

	
	public void compareRanges(List<Range> rangesSmall){
		String outputLine;
		
		boolean freeRange=true;
		for(Range toCompare: rangesSmall){// Za svaki element iz manje tabele koja se koristi da se proveri stanje u vecoj tabeli
			
			int smallStartRange = Integer.parseInt(toCompare.startRange);
			int smallEndRange = Integer.parseInt(toCompare.endRange);
			int subractRange = smallEndRange - smallStartRange+1;

			
			if(subractRange<=0){
				outputLine = "!!! Error start  "  +toCompare.mg + "/" + smallStartRange  +
						 	  " is greater then end " + toCompare.mg + "/" + smallEndRange;
				answerLines.add(outputLine);
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
						smallStartRange + " - " + smallEndRange  + " is very big range " + subractRange;
				answerLines.add(outputLine);
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
								+ " - " + bigEndRange  ;
						answerLines.add(outputLine);
						freeRange=false;
					}
					if(smallEndRange>=bigStartRange && smallEndRange<=bigEndRange){
						outputLine = "!!! Error " + inRanges.mg + "/" +  smallStartRange + " - " 
									+ smallEndRange + " end is within range " + toCompare.mg +  "/" 	+ bigStartRange  
									+ " - " + bigEndRange;
						answerLines.add(outputLine);
						freeRange=false;
					}
				}
			}//for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
		}
		
		if(	freeRange){
			outputLine = "Range you entered is free for assignment ";
			answerLines.add(outputLine);
		}
		
	}
	
	public void prepareAnswers(int rangeIndex, Range toCompare){
		Range refRange = rangesBig.get(rangeIndex);
		if(refRange.getEndRange().compareTo(toCompare.getStartRange())>0){
			System.out.println( "Wrong request is within existing range          " + refRange);
		}
		if(rangesBig.get(rangeIndex+1).getStartRange().compareTo(toCompare.getEndRange())<0){
			System.out.println( "Overlaping the request with exising range(s)   " + rangesBig.get(rangeIndex+1));
		}
	}
	
	// binarySearch method returns index of the Range that is first range bellow range that is checked (toCompare)
	// comparison is perform first by Range.mg after that by  Range.startRange property 
	// rangeComparator.compareNatural(rangesBig.get(mid), toCompare) is method for comparison  
	public int binarySearch(List<Range> rangesBig, Range toCompare){
		RangeComparator rangeComparator = new RangeComparator(RangeComparator.NATURAL);
		Collections.sort(rangesBig, rangeComparator);
		int lo=0;
		int hi=rangesBig.size()-1;
		int mid = 0;
	       while (lo < hi) {
	    	   mid = (hi + lo) / 2;
		       System.out.println( "mid " + mid + ", lo " + lo + ", hi " + hi );
		       System.out.println( "mid " + rangesBig.get(mid).getMg() +rangesBig.get(mid).getStartRange() +
		    		   ", lo " + rangesBig.get(lo).getMg() + rangesBig.get(lo).getStartRange()+
		    		   ", hi " + rangesBig.get(hi).getMg() + rangesBig.get(hi).getStartRange());

				int comparationResult = rangeComparator.compareNatural(rangesBig.get(mid), toCompare);

	            if (comparationResult<0) {
	                if(lo != mid){
	                	lo = mid;
	                }else{
	                	break;
	                }

	            } else if (comparationResult>0) {
	                if(hi != mid){
	                	hi = mid;
	                }else{
	                	break;
	                }
	            } else {
                	break;
	            }

	        }
	        System.out.println( "closestRange = rangesBig.get(mid) first bellow is: " + rangesBig.get(mid));
	        System.out.println( "Range to compare                                   " + toCompare);
	        System.out.println( "First above rangesBig.get(mid+1) first above is:   " + rangesBig.get(mid+1));
	        
	        return mid;
	}
	
	public Range binarySearchOld(List<Range> rangesBig, Range toCompare){
		RangeComparator rangeComparator = new RangeComparator(RangeComparator.NATURAL);
		Collections.sort(rangesBig, rangeComparator);
		Range closestRange=null;
		int lo=0;
		int hi=rangesBig.size()-1;
		int mid = 0;
	       while (lo < hi) {
	    	   mid = (hi + lo) / 2;
		       System.out.println( "mid " + mid + ", lo " + lo + ", hi " + hi );
		       System.out.println( "mid " + rangesBig.get(mid).getMg() +rangesBig.get(mid).getStartRange() +
		    		   ", lo " + rangesBig.get(lo).getMg() + rangesBig.get(lo).getStartRange()+
		    		   ", hi " + rangesBig.get(hi).getMg() + rangesBig.get(hi).getStartRange());

				int comparationResult = rangeComparator.compareNatural(rangesBig.get(mid), toCompare);

	            if (comparationResult<0) {
	                if(lo != mid){
	                	lo = mid;
	                }else{
	                	break;
	                }

	            } else if (comparationResult>0) {
	                if(hi != mid){
	                	hi = mid;
	                }else{
	                	break;
	                }
	            } else {
	            	closestRange =  rangesBig.get(mid);
	            	return closestRange;
	            }
			    //System.out.println( "lo is " + lo + "hi is " + hi + "comparationResult " + comparationResult) ;

	        }
	       // return (a[lo] - value) < (value - a[hi]) ? a[lo] : a[hi];
	        System.out.println( "closestRange rangesBig.get(mid) first bellow is: " + rangesBig.get(mid));

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
		
		for(int i= startIndex; i<= endIndex ;i++){
			Range closestRange = existing.get(i);
			int compareNumber = closestRange.mg.compareTo(checking.mg);
			if(compareNumber==0){
				int compareNumberStart = closestRange.startRange.compareTo(checking.startRange);
				int compareNumberEnd = closestRange.endRange.compareTo(checking.startRange);
				System.out.println("*** compareNumberStart" + compareNumberStart + " compareNumberEnd" +compareNumberEnd);
				System.out.println("*** compareNumberStart" + closestRange.startRange + " compareNumberEnd" +closestRange.endRange);

				if( compareNumberStart<0 && compareNumberEnd>0){
					System.out.println("*** " + checking + " exists ");
	
				}
			    //return compareNumberStart < compareNumberEnd ? compareNumberStart : compareNumberEnd;
	
			}else{
				//return compareNumber;
			}
		}// end of for(int i= startIndex; i< endIndex; i++){
	}


}

