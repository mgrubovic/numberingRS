package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;

public class SearchNumbers {
	
	private List<Range> rangesBig;
	private List <Range> lookForList;
	public List <String> answerLines;
	
	
	
	public SearchNumbers(List<Range> rangesMain) {
		answerLines = new ArrayList<String>();
		this.rangesBig=rangesMain;
	}
	
	public List<String> getAnswers(String requestString){
		makeList(requestString);
		//makeAnswer(); for test only
		for(Range toCompare: lookForList){
			int rangeIndex = binarySearch(rangesBig, toCompare);
			prepareAnswers(rangeIndex, toCompare);
		}
		return answerLines;
	}
	
	/*
	 *  requestString is parameter  passed by ControllerServlet it is the format of
	 *  areaCode1;subcriberNumber1!!!areaCode2;subcriberNumber2!!!...
	 *  this method  parses the  requestString 
	 *  checks if the subscriber number is valid by calling isNumberGood(subscriberRequest)
	 *  and turns it into variable lookForList of ArrayList<Range>
	 *  
	 *  
	 */
	public void makeList(String requestString){
		lookForList = new ArrayList<>();

		String [] data = requestString.split("!!!");

		for(int i=0; i< data.length; i++){
			Range rangeHidden = new Range();
			String [] innerData = data[i].split(";");
			rangeHidden.mg= innerData[0];
			if(innerData.length >1){
				String subscriberRequest = innerData[1];
				if(isNumberGood(subscriberRequest)){
					rangeHidden.startRange = subscriberRequest;
					lookForList.add(rangeHidden);
				}
			}else{
				answerLines.add("<p>You didn't enter the subscriber number </p>");
			} 
		}
	}
	
	// Obsolete method, it has only test function  
	public void makeAnswer(){
		
		String outputLine;
		for(Range toCompare: lookForList){
			int smallStartRange = Integer.parseInt(toCompare.startRange);
			outputLine =" Subscriber number " + toCompare.mg +   "/" + smallStartRange + " does not exist.";
			for(Range inRanges: rangesBig){
					
				if(toCompare.mg.equals(inRanges.mg)){
	
					int bigStartRange = Integer.parseInt(inRanges.startRange);
					int bigEndRange = Integer.parseInt(inRanges.endRange);
	
					if( bigStartRange <= smallStartRange && smallStartRange<=bigEndRange){
						outputLine =" Subscriber number " + toCompare.mg +   "/" + smallStartRange +
						" exists. <br/>It is in the range " + inRanges.mg +"/" + bigStartRange + " - "  +
						 bigEndRange  + " and belongs to " + inRanges.operator;
					}
	
				}
			}//end of inner loop for(Range inRanges: 
			answerLines.add(outputLine);
		}// end of outer loop for(Range toCompare: lookForList
	}

	public boolean isNumberGood(String rangeNumber){
		boolean goodRequest=true;
		if(Range.isTelNumber(rangeNumber)){
			if(Range.isLengthGood(rangeNumber, 5, 7)){
				return goodRequest;
			}else{
				answerLines.add("<p>Subscriber number length is invalid, it should be 5, 6 or 7 digits long  "+ rangeNumber + "</p>");
				goodRequest=false;
			}			
		}else{
			answerLines.add("<p>You put invalid value for subscriber number  "+ rangeNumber + "</p>");
			goodRequest=false;
		}
		return goodRequest;
	}
	
	
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
	
	public void prepareAnswers(int rangeIndex, Range checkRange){
		String outputLine=null;
		
		Range refRange = rangesBig.get(rangeIndex);
		String refArea = refRange.getMg();
		int refStart = Integer.parseInt(refRange.getStartRange());
		int refEnd = Integer.parseInt(refRange.getEndRange());
		

		int checkStart = Integer.parseInt(checkRange.getStartRange());
		
		boolean goodAnswer = true;

		if( refArea.equals(checkRange.getMg()) ){
			if( refStart <= checkStart && refEnd >= checkStart){
				outputLine = "Subscriber number " + checkRange.getMg() +   "/" + checkStart +
						" exists. <br/>It is in the range " + refArea +"/" + refStart + " - "  +
						refEnd  + " and belongs to " + refRange.operator;
				answerLines.add(outputLine);
			}else{
				goodAnswer = false;
			}
		}else{
			goodAnswer = false;
		}
		
		if(!goodAnswer){
			outputLine ="<div class='errInput'> Subscriber number " + checkRange.getMg() +   "/" + checkStart + " does not exist.</div>";
			answerLines.add(outputLine);
		}

	}

}


