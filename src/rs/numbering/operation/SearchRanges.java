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
	
	//StringBuilder outputString;
	public List<Range> rangesBig;
	public List <String> answerLines;
	
	public SearchRanges(List<Range> rangesBig) {
		answerLines = new ArrayList<String>();
		this.rangesBig=rangesBig;
	}
	
	public List <String> getAnswers(String mgRequest,String startRangeRequest, String endRangeRequest){
		getRequestLabel(mgRequest,startRangeRequest, endRangeRequest);
		if( isGoodRequest(startRangeRequest, endRangeRequest)){
			Range rangeInput = new Range();
			rangeInput.setMg(mgRequest);
			rangeInput.setStartRange(startRangeRequest); 
			rangeInput.setEndRange(endRangeRequest);
			
			List <Range> list1 = new ArrayList<>();
			list1.add(rangeInput);
			compareRanges(list1);
			
			int rangeIndex = binarySearch(rangesBig, rangeInput);
			prepareAnswers(rangeIndex, rangeInput);
		}
		return answerLines;

	}

	public void getRequestLabel(String mgRequest,String startRangeRequest, String endRangeRequest){
		answerLines.add("<table><tr><th>Area code</th><th>Start of the range</th><th>End of the range</th></tr>");	
		answerLines.add("<tr><td>" + mgRequest +"</td><td>" + startRangeRequest + "</td><td>" + endRangeRequest + "</td></tr>");
		answerLines.add("</table>");
	}
	
	public boolean isGoodRequest(String startRangeRequest, String endRangeRequest){
		boolean goodRequest = true;
		
		boolean goodStartRange = isNumberGood(startRangeRequest, "start");
		boolean goodEndRange = isNumberGood(endRangeRequest, "end");
		
		if(!(goodStartRange && goodEndRange)){
			goodRequest = false;
			return goodRequest;
		}
		
		if(startRangeRequest.length() != endRangeRequest.length() ){
		
			answerLines.add("<p>Start and end range must be same in length  </p>");
			goodRequest = false;
			return goodRequest;
		}
		
		if(startRangeRequest.compareTo(endRangeRequest) >= 0 ){
			answerLines.add("<p>End range must be greater then start range</p>");
			goodRequest = false;
			return goodRequest;
		}
		return goodRequest;
	}
	public boolean isNumberGood(String rangeNumber, String position){
		boolean goodRequest=true;
		if(Range.isTelNumber(rangeNumber)){
			if(Range.isLengthGood(rangeNumber, 5, 7)){
				return goodRequest;
			}else{
				answerLines.add("<p>"+ position + " range length is invalid, it should be 5, 6 or 7 digits long  "+ rangeNumber + "</p>");
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
	
	public void prepareAnswers(int rangeIndex, Range checkRange){
		String outputLine=null;
		
		Range refRange = rangesBig.get(rangeIndex);
		String refArea = refRange.getMg();
		int refStart = Integer.parseInt(refRange.getStartRange());
		int refEnd = Integer.parseInt(refRange.getEndRange());
		
		String refNextArea = rangesBig.get(rangeIndex+1).getMg();
		int refNextStart = Integer.parseInt(rangesBig.get(rangeIndex+1).getStartRange());
		
		int checkStart = Integer.parseInt(checkRange.getStartRange());
		int checkEnd = Integer.parseInt(checkRange.getEndRange());
		
		boolean goodAnswer = true;

		if( refArea.equals(checkRange.getMg()) ){
			if( refStart <= checkStart && refEnd >= checkStart){
				outputLine = "Wrong start range is within existing range          " + refRange;
				System.out.println( outputLine);
				answerLines.add(outputLine);
				goodAnswer = false;
			}
			if( refStart <= checkEnd && refEnd >= checkEnd){
				outputLine = "Wrong end range is within existing range          " + refRange;
				System.out.println( outputLine);
				answerLines.add(outputLine);
				goodAnswer = false;
			}
		}
		if( refNextArea.equals(checkRange.getMg()) ){
			if(refNextStart<checkEnd){
				outputLine = "Overlaping the end range with exising range(s)   " + rangesBig.get(rangeIndex+1);
				System.out.println( outputLine);
				answerLines.add(outputLine);
				goodAnswer = false;
			}
		}
		
		if(goodAnswer){
			outputLine = "The range you entered is free for asignment";
			System.out.println( outputLine);
			answerLines.add(outputLine);
		}
	}

	
	// binarySearch method returns index of the Range that is first range bellow number we are checking (toCompare)
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

}

