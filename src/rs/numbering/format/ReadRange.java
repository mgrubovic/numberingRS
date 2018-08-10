/**
 *  OCP Java SE 7 Programmer II Certification Guide
 */
package rs.numbering.format;

import java.util.List;

/**
 * @author milosav.grubovic
 *
 */
public interface ReadRange {
	public void processRangeEntry(String line, List<Range> ranges);
}
