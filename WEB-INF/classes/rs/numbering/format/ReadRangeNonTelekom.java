/**
 *  OCP Java SE 7 Programmer II Certification Guide
 */
package rs.numbering.format;

import java.util.List;

/**
 * @author milosav.grubovic
 *
 */
public class ReadRangeNonTelekom implements ReadRange {

	@Override
	public void processRangeEntry(String line, List<Range> ranges) {
		String [] data = line.split("/");
		Range range = new Range();
		range.mg= data[0];
		range.startRange = data[1];

		ranges.add(range);
		
		System.out.println(range.mg + " opseg pocinje sa " + range.startRange + " " );
	}

}
