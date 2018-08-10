package rs.numbering.format;

import java.util.List;

public class ReadRangeWebCsvNotGeo implements ReadRange {

		/* (non-Javadoc)
		 * @see com.ratel.numeracija.check.ReadRange#processRangeEntry(java.lang.String, java.util.List)
		 */
		@Override
		public void processRangeEntry(String line, List<Range> ranges){
			String [] data = line.split("\"");
			Range range = new Range();
			range.operator = data[1];
			//range.mg= data[1];
			String mgStartRange =data[3];
			
			int mgStart=mgStartRange.indexOf(" ");
			if(mgStart==-1){
				mgStart=3;
			}
			
			range.mg = mgStartRange.substring(0, mgStart );
			range.startRange= mgStartRange.substring(mgStart ); 

			String mgEndRange =data[5];
			range.endRange = mgEndRange.substring(mgStart);


			if(data.length>6){
				range.fromDate = data[7];
			}
			if(data.length >8){
				range.untilDate = data[9];
			}
			ranges.add(range);
			
			System.out.println(" " + range.operator + " " + range.mg + " opseg pocinje sa " + range.startRange.charAt(0) + " " +
									range.startRange + " " + range.endRange+ " " + range.fromDate + " " + range.untilDate);
		
	}
	
}
