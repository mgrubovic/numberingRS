package rs.numbering.format;

import java.util.List;

/*
* 	This class parse data taken from RATEL official numbering site for geographic numbers
*  Format of the data is  
*	Telekom Srbija a.d., Beograd;10 2100000;10 2100999;10.6.2017;10.6.2027
*/

public class ReadRangeFileCsvGeo implements ReadRange {
	/* (non-Javadoc)
	 * @see com.ratel.numeracija.check.ReadRange#processRangeEntry(java.lang.String, java.util.List)
	 * 
	 * Telekom Srbija a.d., Beograd;10 2100000;10 2100999;10.6.2017;10.6.2027
	 */
	@Override
	public void processRangeEntry(String line, List<Range> ranges){
		String [] data = line.split(";");
		Range range = new Range();
		range.operator = data[0];
		//range.mg= data[1];
		String mgStartRange =data[1];

		range.mg = mgStartRange.substring(0, mgStartRange.indexOf(" ") );
		range.startRange= mgStartRange.substring(mgStartRange.indexOf(" ")+1 ); 

		String mgEndRange =data[2];
		range.endRange = mgEndRange.substring(mgEndRange.indexOf(" ")+1);


		if(data.length>2){
			range.fromDate = data[3];
		}
		if(data.length >3){
			range.untilDate = data[4];
		}
		
		// only valid ranges could be added to Array 
		boolean goodRange = true;
		if(!range.isTelNumber(range.mg)){
			goodRange=false;
		}
		if(!range.isTelNumber(range.startRange)){
			goodRange=false;
		}
		if(!range.isTelNumber(range.endRange)){
			goodRange=false;
		}
		if(goodRange){
			ranges.add(range);
		}
		
//		System.out.println(" " + range.operator + " " + range.mg + " opseg pocinje sa " + range.startRange.charAt(0) + " " +
//								range.startRange + " " + range.endRange+ " " + range.fromDate + " " + range.untilDate);
	
}


}
