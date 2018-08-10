package rs.numbering.source;

import java.util.List;

import rs.numbering.format.Range;
import rs.numbering.format.ReadRange;

public interface SourceReader {
	
	public List <Range> takeData(String address, ReadRange formatReader);

}
