package rs.numbering.model;

import rs.numbering.format.ReadRange;
import rs.numbering.format.ReadRangeDB;
import rs.numbering.format.ReadRangeFileCsvGeo;
import rs.numbering.format.ReadRangeWebCsvGeo;
import rs.numbering.format.ReadRangeWebCsvNotGeo;
import rs.numbering.format.ReadRangeWrite;
import rs.numbering.source.*;

public class ReadRangeFactory {
	
	private ReadRange formatReader;
	private SourceReader sourceReader;

	public ReadRangeFactory(String fileFormat){

		if(fileFormat.equals("fileCsvGeo")){
			System.out.println("Method is " + "officialSite");
			setFormatReader(new ReadRangeFileCsvGeo());
		}
/*		
		if(fileFormat.equals("officialSite")){
			System.out.println("Method is " + "officialSite");
			setFormatReader(new ReadRangeAssign());

		}
		else if(fileFormat.equals("databaseOutput")){
			System.out.println("Method is " + "databaseOutput");
			setFormatReader(new ReadRangeDBfull());
		}
*/		
		else if(fileFormat.equals("dbShort")){
			System.out.println("Method is " + "dbShort");
			setFormatReader(new ReadRangeDB());
		}
		else if(fileFormat.equals("shortForm")){
			System.out.println("Method is " + "shortForm");
			setFormatReader(new ReadRangeWrite());
		}else if(fileFormat.equals("webCsvGeo")){
			System.out.println("Method is " + "webCsvGeo");
			setFormatReader(new ReadRangeWebCsvGeo());
		}else if(fileFormat.equals("webCsvNotGeo")){
			System.out.println("Method is " + "webCsvNotGeo");
			setFormatReader(new ReadRangeWebCsvNotGeo());
		}
		
		if(fileFormat.equals("fileCsvGeo")){
			System.out.println("Source is " + "file");
			setSourceReader(new FileRangeReader());
		}else if(fileFormat.equals("webCsvGeo")){
			System.out.println("Source is " + "web");
			setSourceReader(new WebReader());
		}
		else if(fileFormat.equals("dbShort")){
			System.out.println("Method is " + "dbShort");
			setSourceReader(new FileRangeReader());
		}
	}

	public ReadRange getFormatReader() {
		return formatReader;
	}

	public void setFormatReader(ReadRange formatReader) {
		this.formatReader = formatReader;
	}

	public SourceReader getSourceReader() {
		return sourceReader;
	}

	public void setSourceReader(SourceReader sourceReader) {
		this.sourceReader = sourceReader;
	}
	
	
}
