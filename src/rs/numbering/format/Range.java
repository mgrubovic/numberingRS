/**
 *  
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
	public String endRange;
	public int blockCount;
	public int amountRange;
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
		amountRange = oldRange.amountRange;
		
	}
	 
	@Override
	public String toString() {
		return "Range [mg=" + mg + ", startRange=" + startRange + ", endRange=" + endRange + ", operator=" + operator + "]";
				//+ ", fromDate=" + fromDate + ", untilDate=" + untilDate + ", decisionNumber=" + decisionNumber
				//+ ", decisionDate=" + decisionDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

	public String getStartRange() {
		return startRange;
	}

	public void setStartRange(String startRange) {
		this.startRange = startRange;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public String getEndRange() {
		return endRange;
	}

	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}

	public int getAmountRange() {
		return amountRange;
	}

	public void setAmountRange(int amountRange) {
		this.amountRange = amountRange;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(String untilDate) {
		this.untilDate = untilDate;
	}

	public String getDecisionNumber() {
		return decisionNumber;
	}

	public void setDecisionNumber(String decisionNumber) {
		this.decisionNumber = decisionNumber;
	}

	public String getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(String decisionDate) {
		this.decisionDate = decisionDate;
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
