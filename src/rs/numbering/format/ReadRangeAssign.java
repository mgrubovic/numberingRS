/**
 * 
 */
package rs.numbering.format;

import java.util.List;

/**
 * @author milosav.grubovic
 *
 */
public class ReadRangeAssign implements ReadRange {

	/* (non-Javadoc)
	 * @see com.ratel.numeracija.check.ReadRange#processRangeEntry(java.lang.String, java.util.List)
	 * Format file is 
	 * Telekom Srbija a.d., Beograd;10 2100000;10 2100999;10.6.2017;10.6.2027
	 */
	@Override
	public void processRangeEntry(String line, List<Range> ranges){
		String [] data = line.split(";");
		Range range = new Range();
		try{
			range.operator = data[0];
			range.mg= data[1];
			range.startRange = data[2];
			range.endRange = data[4];
			if(data.length>5){
				range.fromDate = data[5];
			}
			if(data.length >6){
				range.untilDate = data[6];
			}
		}catch(NumberFormatException ex){
			System.out.println("Error "+ ex.toString());
		}
		ranges.add(range);
		
		//System.out.println(" " + range.operator + " " + range.mg + " opseg pocinje sa " + range.startRange.charAt(0) + " " +
		//						range.startRange + " " + range.endRange);
	
}

}
