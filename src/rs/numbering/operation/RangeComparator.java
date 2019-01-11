/**
 *  OCP Java SE 7 Programmer II Certification Guide
 */
package rs.numbering.operation;

import java.util.Comparator;

import rs.numbering.format.Range;

/**
 * @author milosav.grubovic
 * @param <T>
 *
 */
public class RangeComparator implements Comparator<Range> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	
	final public static int NATURAL=1;
	final public static int OPERATOR=2;
	final public static int FROM_DATE=3;

	int selector;
	
	/**
	 * @param selector
	 */
	public RangeComparator(int selector) {
		super();
		this.selector = selector;
	}
	public RangeComparator(){
		super();
		selector=NATURAL;
	}
	@Override
	public int compare(Range range1, Range range2) {
		if(selector == NATURAL){
			return compareNatural(range1, range2) ;
		}else if(selector == OPERATOR){
			return range1.operator.compareToIgnoreCase(range2.operator);
					
		}else if(selector == FROM_DATE){
			return range1.fromDate.compareTo(range2.fromDate);
		}
		return 0;
				
/*		
		int compareNumber = range1.mg.compareTo(range2.mg);
		if(compareNumber==0){
			compareNumber = range1.startRange.compareTo(range2.startRange);
			return compareNumber;
		}else{
			return compareNumber;
		}
*/		
	}

	public int compareNatural(Range range1, Range range2) {
		int compareNumber = range1.mg.compareTo(range2.mg);
		if(compareNumber==0){
			compareNumber = range1.startRange.compareTo(range2.startRange);
			return compareNumber;
		}else{
			return compareNumber;
		}
	}

/*	public int compareNaturalComplete(Range range1, Range range2) {
		int compareNumber = range1.mg.compareTo(range2.mg);
		if(compareNumber==0){
			int compareNumberStart = range1.startRange.compareTo(range2.startRange);
			int compareNumberEnd = range1.endRange.compareTo(range2.startRange);
		    return compareNumberStart < compareNumberEnd ? compareNumberStart : compareNumberEnd;

		}else{
			return compareNumber;
		}
	}
*/	
	
	public int compareNaturalExistance(Range existing, Range checking ) {
	int compareNumber = existing.mg.compareTo(checking.mg);
	if(compareNumber==0){
		int compareNumberStart = existing.startRange.compareTo(checking.startRange);
		int compareNumberEnd = existing.endRange.compareTo(checking.startRange);
		System.out.println("*** compareNumberStart" + compareNumberStart + " compareNumberEnd" +compareNumberEnd);

		if( compareNumberStart<0 && compareNumberEnd>0){
			System.out.println("*** " + checking + " in");
			System.out.println("*** " + existing + " ");

		}
	    return compareNumberStart < compareNumberEnd ? compareNumberStart : compareNumberEnd;

	}else{
		return compareNumber;
	}
}
	
}
