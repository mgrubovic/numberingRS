package rs.numbering.format;

import java.util.List;

public class ReadRangeDB implements ReadRange {

	@Override
	public void processRangeEntry(String line, List<Range> ranges) {
		String [] data = line.split(";");
		Range range = new Range();
		try{
			range.id = Integer.parseInt(data[0]);
			range.mg= data[1];
			range.startRange = data[2];
			int startBlock = Integer.parseInt(data[2]);
			int blockType = Integer.parseInt(data[3]);
			int blockCount = Integer.parseInt(data[4]);
		
			range.endRange = "" + getEndRange(startBlock, blockType,  blockCount);
			range.fromDate = data[6];
			range.untilDate = data[7];
			range.decisionNumber = data[8];
			range.operator = data[13];

			ranges.add(range);
		}catch(NumberFormatException ex){
			System.out.println("Error "+ ex.toString());
		}
		
	}
	private int getEndRange(int startBlock, int blockType, int blockCount){
		int blockSize = 0;
		int endBlock=0;
		switch(blockType){
		case 1000:
			blockSize=1000;
			break;
		case 2000:
			blockSize=10000;
			break;
		case 3000:
			blockSize=100000;
			break;
		}
		endBlock = startBlock + blockSize*blockCount-1;
		return endBlock;
	}
}
