/**
 *  OCP Java SE 7 Programmer II Certification Guide
 */
package rs.numbering.format;

import java.util.List;

/**
 * @author milosav.grubovic
 *
 */
public class ReadRangeWrite implements ReadRange {

	/* (non-Javadoc)
	 * @see com.ratel.numeracija.check.ReadRange#processRangeEntry(java.lang.String)
	 */
	@Override
	public void processRangeEntry(String line, List<Range> ranges){
		String [] data = line.split(";");
		Range range = new Range();
		range.mg= data[0];
		range.startRange = data[1];
		range.endRange = data[3];
		int start=0, end=0;
		try{
			start = Integer.parseInt(range.startRange);
			end = Integer.parseInt(range.endRange);
		}catch(NumberFormatException ex){
			System.out.println("Not number  " + ex.toString());
		}
		range.blockCount = (1+end-start)/1000; 
		ranges.add(range);
		
/*		za proveru da li je dobro obradjen fajl
 * 
 * 		System.out.println(" " + range.operator + " " + range.mg + " opseg pocinje sa " + range.startRange.charAt(0) + " " +
 *								range.startRange + " " + range.endRange);
*/
	}
}
