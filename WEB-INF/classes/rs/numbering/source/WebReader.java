/**
 *  
 */
package rs.numbering.source;

import java.io.*;
import java.util.*;

import rs.numbering.format.*;

import java.net.*;
/**
 * @author milosav.grubovic
 *
 */
public class WebReader implements SourceReader {

	/**
	 * @param args
	 */
	public List <Range> listRanges = new ArrayList<>();
	ReadRange readWebCsv ;
	public boolean goodPage=true;
		
/*		String url ="http://registar.ratel.rs/cyr/reg202?action=table&vrsta=1000&filter=&operator=JP+Po%C5%A1ta+Srbije+Beograd+-+RADNA+JEDINICA+PO%C5%A0TA+NET&net=&broj=&format=csv";
		String url_latinica = "http://registar.ratel.rs/sr/reg202?action=table&vrsta=1000&filter=&operator=ABA+TEL&net=&broj=&format=csv";
		String url_engleski = "http://registar.ratel.rs/en/reg202?action=table&vrsta=1000&filter=&operator=ABA+TEL&net=&broj=&format=csv";

		
		String url_geo = "http://registar.ratel.rs/sr/reg202?action=table&vrsta=1000&filter=&operator=&net=&broj=&format=csv";
		wr.readWebCsv = new ReadRangeWebCsvGeo();
		wr.takeData(url);
		//wr.getDataScanner(url_engleski);
*/		

	public List <Range>  takeData(String address, ReadRange readWebCsv){
		this.readWebCsv=readWebCsv;

		try{
			URL page = new URL(address);

			HttpURLConnection conn = (HttpURLConnection)page.openConnection();
			System.out.println("Page encoding " + conn.getContentEncoding());
			
			conn.connect();
			
			
			//	nije prikazivalo dobre karaktere sa "UTF-8" "WINDOWS-1252" "WINDOWS-1251" "WINDOWS-1250"
			// "UTF-16" je isto sto i "Unicode"
			InputStreamReader in = new InputStreamReader( conn.getInputStream(), "Unicode");

			System.out.println("Encoding is :" + in.getEncoding());
			BufferedReader buff = new BufferedReader(in);
			
			String line;
			while((line = buff.readLine())!= null){
				//byte[] ptext = value.getBytes(Cp1252); 
				//String line = new String(ptext, UTF_8);
				readWebCsv.processRangeEntry(line, listRanges);
/*		
 * 		
 * 				System.out.println(line);
				String [] data = line.split("\"");
				for(int i=0; i<data.length; i++){
					System.out.println("data " + i + " " + data[i]);
				}
*/				

			}
		}catch(MalformedURLException urlexc){
			System.out.println("Bad URL: " + address);
			goodPage=false;
		}catch(IOException ioe){
			System.out.println("IO Error:" + ioe.getMessage());
			goodPage=false;
		}
		return listRanges;

		
	}

/* Another way to read data from web
 * 	
	public void getDataScanner(String address){
		try{
			URL page = new URL(address);
			
			Scanner scan = new Scanner(page.openStream(), "UTF-8");
			while(scan.hasNext()){
				System.out.println(scan.nextLine());			
			}
			scan.close();
			
		}catch(MalformedURLException urlexc){
			System.out.println("Bad URL: " + address);
		}catch(IOException ioe){
			System.out.println("IO Error:" + ioe.getMessage());
		}
	}
*/
}
