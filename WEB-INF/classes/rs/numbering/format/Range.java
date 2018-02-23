/**
 *  OCP Java SE 7 Programmer II Certification Guide
 */
package rs.numbering.format;

/**
 * @author milosav.grubovic
 *
 */
 public class Range {
	public int id;
	public String mg;
	public String startRange;
	public int blockCount;
	public String endRange;
	public String operator;
	public String fromDate;
	public String untilDate;
	public String decisionNumber;
	public String decisionDate;
	private String colorCell="pink";
	
	public Range(){}
	
	public Range(Range oldRange){
		id = oldRange.id;
		mg = oldRange.mg;
		startRange = oldRange.startRange;
		endRange = oldRange.endRange;
		operator = oldRange.operator;
		fromDate = oldRange.fromDate;
		untilDate = oldRange.untilDate;
		decisionNumber = oldRange.decisionNumber;
		decisionDate = oldRange.decisionDate;
		
	}
	
	public String getColorCell() {
		return colorCell;
	}

	public void setColorCell(String colorCell) {
		this.colorCell = colorCell;
	}
	
	public boolean isTelNumber(String inNumber, String outNumber){
		try{
			Integer.parseInt(inNumber);
			outNumber=inNumber;
			return true;
		}catch(NumberFormatException  ex){
			return false;
		}
	}
	public boolean isTelNumber(String inNumber){
		try{
			Integer.parseInt(inNumber);
			
			return true;
		}catch(NumberFormatException  ex){
			System.out.println("Not a number " + inNumber);
			return false;
		}
	}
	
	public boolean isLengthGood(String inNumber, int min, int max){
		try{
			Integer.parseInt(inNumber);
			if(inNumber.length()< min){
				return false;
			}else if(inNumber.length()> max){
				return false;
			}
			
			return true;
		}catch(NumberFormatException  ex){
			System.out.println("Not a number " + inNumber);
			return false;
		}
	}
	
}
