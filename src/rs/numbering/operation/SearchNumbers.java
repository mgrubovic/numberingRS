package rs.numbering.operation;

import java.util.*;

import rs.numbering.format.Range;
import rs.numbering.model.DataModelServlet;

public class SearchNumbers {
	
	public String appendEnd = "+!!+";
	StringBuilder outputString;
	public List<Range> rangesBig;// value initialized in DataModelServlet
	List <Range> lookForList;
	public List <String> answerLines;
	
	
	
	public SearchNumbers() {
		answerLines = new ArrayList<String>();
		rangesBig=DataModelServlet.rangesMain;
	}
	
	
	public void makeList(String requestString){
		String outputLine = null;
		lookForList = new ArrayList<>();
		
		String [] data = requestString.split("!!!");// get numbers from hidden parameter ;
		if( data.length>0)
		{
			
			for(int i=0; i< data.length; i++){
				Range rangeHidden = new Range();
				String [] innerData = data[i].split(";");
				try{
					rangeHidden.mg= innerData[0];
					if(innerData.length >1){
						rangeHidden.startRange = innerData[1];
						String subscriberRequest = rangeHidden.startRange;

						if(rangeHidden.isTelNumber(subscriberRequest)){
							if(rangeHidden.isLengthGood(subscriberRequest, 5, 7)){
								lookForList.add(rangeHidden);
							}else{
								outputLine = "<p>Number length is invalid, it should be between 5 and 7 digits   "+ subscriberRequest + "</p>";
							}
						}else{
							outputLine = "<p>You entered invalid value for the subscriber number  "+ subscriberRequest + "</p>";
						}
					}else if(data.length>1){
						outputLine = "<p>You didn't enter the number  " + rangeHidden.mg + "</p>";
					} // end of if(innerData.length >1) ... else if

				}catch(NumberFormatException ex){
					System.out.println("Error "+ ex.toString());
				}
			}// end of for(int i=0;....
			if(outputLine != null){
				answerLines.add(outputLine);
			}

		}
	}
	
	
	public void makeAnswer(){
		
		String outputLine;
		for(Range toCompare: lookForList){// Za svaki element iz manje tabele koja se koristi da se proveri stanje u vecoj tabeli
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
			}//end of inner loop for(Range inRanges: unutrašnja petlja koja prolazi kroz veliku tabelu dodeljenih brojeva
			answerLines.add(outputLine);
		}// end of outer loop for(Range toCompare: lookForList
	}

/*	public StringBuilder findNumbers(List<Range> rangesBig, List<Range> rangesSmall){
		
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
			//System.out.println( outputLine);
			outputString.append(outputLine);
		}
		return outputString;
	}
	*/
	
	
}


