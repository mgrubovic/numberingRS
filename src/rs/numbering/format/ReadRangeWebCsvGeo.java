/**
 *  
 */
package rs.numbering.format;

import java.util.List;

/**
 * @author milosav.grubovic
 * 	This class parse data taken from RATEL official numbering site for geographic numbers
 *  Format of the data is  
 *	"Telekom Srbija a.d., Beograd"	"35 414000"	"35 415999"	"10.06.2017"	"10.06.2027"
 */
public class ReadRangeWebCsvGeo implements ReadRange {

	/* (non-Javadoc)
	 * @see com.ratel.numeracija.check.ReadRange#processRangeEntry(java.lang.String, java.util.List)
	 * 
	 * "Telekom Srbija a.d., Beograd"	"35 414000"	"35 415999"	"10.06.2017"	"10.06.2027"
	 */
	@Override
	public void processRangeEntry(String line, List<Range> ranges){
		String [] data = line.split("\"");
		Range range = new Range();
		if(data.length>4){
			range.operator = data[1];
			//range.mg= data[1];
			String mgStartRange =data[3];
	
			range.mg = mgStartRange.substring(0, mgStartRange.indexOf(" ") );
			range.startRange= mgStartRange.substring(mgStartRange.indexOf(" ")+1 ); 
	
			String mgEndRange =data[5];
			range.endRange = mgEndRange.substring(mgEndRange.indexOf(" ")+1);
		}else{
			System.out.println("Wrong format readaer for the line:" + line);
		}


		if(data.length>6){
			range.fromDate = data[7];
		}
		if(data.length >8){
			range.untilDate = data[9];
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
