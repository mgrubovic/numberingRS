/**
 *  
 */
package rs.numbering.format;

import java.util.List;

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
	private List <String> description;
	

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


	
	public List <String> getDescription() {
		return description;
	}

	public void setDescription(List <String> description) {
		this.description = description;
	}

	public String getColorCell() {
		return colorCell;
	}

	public void setColorCell(String colorCell) {
		this.colorCell = colorCell;
	}

	public static boolean isTelNumber(String inNumber){
		boolean isNumber = false;
		try{
			Integer.parseInt(inNumber);
			isNumber =  true;
			return isNumber;
		}catch(NumberFormatException  ex){
			System.out.println("Not a number " + inNumber);
			return isNumber;
		}

	}
	
	public static boolean isLengthGood(String inNumber, int min, int max){
		try{
			boolean goodLength= true;
			Integer.parseInt(inNumber);
			if(inNumber.length()< min || inNumber.length()> max ){
				goodLength =  false;
			}
			return goodLength;
		}catch(NumberFormatException  ex){
			return false;
		}
	}
	
}
