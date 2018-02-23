package rs.numbering.source;


import java.io.*;
import java.util.*;

import rs.numbering.format.*;

/**
 * @author milosav.grubovic
 *
 */

public class FileRangeReader implements SourceReader{


	/**
	 * @param args
	 */
	public List <Range> listRanges = new ArrayList<>();
	ReadRange readFileCsv ;
	public boolean goodPage=true;

	public  List <Range>  takeData(String fileName, ReadRange fromatReader){
		
		
		System.out.println("Fromat reader is :" + fromatReader);
		
		BufferedReader bigReader =  openFile(fileName);
		
		if(bigReader != null){
			try{
				String line = bigReader.readLine();
				while(line != null){
					fromatReader.processRangeEntry(line, listRanges);
					line = bigReader.readLine();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
			
			closeFile(bigReader);
		}else{
			System.out.println("Can not open file " + fileName);
		}


		return listRanges;
	}

	
	public  void takeData(BufferedReader bigReader, ReadRange fromatReader){
		
		
		System.out.println("Fromat reader is :" + fromatReader);
		
	

		try{
			String line = bigReader.readLine();
			while(line != null){
				fromatReader.processRangeEntry(line, listRanges);
				line = bigReader.readLine();
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		closeFile(bigReader);

	}


	
/*	
	public void takeData(String fileName, ReadRange readFileCsv){
		this.readFileCsv=readFileCsv;
		takeData(fileName);
	}
		
	
	public void takeData(String address){
		
		try{
			URL page = new URL(address);
			Charset UTF_8 = Charset.forName("UTF-8");
			Charset ISO_8859_5 = Charset.forName("ISO-8859-5");
			Charset Cp1252 = Charset.forName("Cp1252");
			
						
			PrintWriter printW = new PrintWriter("GeoNum2.txt", "UTF-16");// "UTF-16" je isto sto i "Unicode"
			// ukoliko se stavi ekstenzija .csv Excel ne prikazuje dobro srpsku latinicu
			// ukoliko se GeoNum2.txt naknadno snimi sa ekstenzijom .csv Excel dobro prikazuje latinicu

			HttpURLConnection conn = (HttpURLConnection)page.openConnection();
			System.out.println("Page encoding " + conn.getContentEncoding());
			
			conn.connect();
			
			
		//	nije prikazivalo dobre karaktere sa "UTF-8" "WINDOWS-1252" "WINDOWS-1251" "WINDOWS-1250"
			InputStreamReader in = new InputStreamReader( conn.getInputStream(), "Unicode");

			System.out.println("Encoding is :" + in.getEncoding());
			BufferedReader buff = new BufferedReader(in);
			
			String line;
			while((line = buff.readLine())!= null){
				//byte[] ptext = value.getBytes(Cp1252); 
				//String line = new String(ptext, UTF_8);
				readFileCsv.processRangeEntry(line, listRanges);
				System.out.println(line);
				String [] data = line.split("\"");
				for(int i=0; i<data.length; i++){
					System.out.println("data " + i + " " + data[i]);
				}
				
				//System.out.println("WINDOWS-1250  equivalent is: "+ new String(line.getBytes() , "WINDOWS-1250"));
				printW.write(line + System.lineSeparator());
			}
			printW.close();
		}catch(MalformedURLException urlexc){
			System.out.println("Bad URL: " + address);
			goodPage=false;
		}catch(IOException ioe){
			System.out.println("IO Error:" + ioe.getMessage());
			goodPage=false;
		}
	}
*/	
	public BufferedReader openFile(String file){
		BufferedReader reader = null;
		try {
			
			reader = new BufferedReader(new FileReader(file));
			//new InputStreamReader( new FileInputStream(file), "UTF-8")
		} catch (FileNotFoundException e) {
			System.out.println("file name is " + file);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}
	
	public void closeFile(BufferedReader br){
		try {
			br.close();
			System.out.println("Closing file" );

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
