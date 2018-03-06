/**
 *  
 */
package rs.numbering.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import rs.numbering.format.Range;

import javax.xml.bind.annotation.XmlElement;



@XmlType(propOrder = {"mg", "startRange",  "endRange", "operator"})

@XmlRootElement(name="Range")
public class RangeJaxb {
//	public int id;
	private String mg;
	private String startRange;
	private String endRange;
	private String operator;
	
/*	public String fromDate;
	public String untilDate;

	public int blockCount;
	public String decisionNumber;
	public String decisionDate;
*/	
	//private String colorCell="pink";
	
	public RangeJaxb(){
		
	}
	public RangeJaxb(Range oldRange){
//		this.id = oldRange.id;
		this.mg = oldRange.mg;
		this.startRange = oldRange.startRange;
		this.endRange = oldRange.endRange;
		this.operator = oldRange.operator;
/*		this.fromDate = oldRange.fromDate;
		this.untilDate = oldRange.untilDate;
		this.decisionNumber = oldRange.decisionNumber;
		this.decisionDate = oldRange.decisionDate;
*/		
	}
	
	
	public String getMg() {
		return mg;
	}
	@XmlElement( name="Area_Code")
	public void setMg(String mg) {
		this.mg = mg;
	}
	
	public String getStartRange() {
		return startRange;
	}
	@XmlElement( name="Range_Start")
	public void setStartRange(String startRange) {
		this.startRange = startRange;
	}
	@XmlElement( name="Range_End")
	public String getEndRange() {
		return endRange;
	}
	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}
	@XmlElement( name="Operator")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String toString(){
		return new String(" " + mg + " /" + startRange + " - " + endRange +  " " + operator);
	}
}
