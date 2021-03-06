/**
 
 */
package rs.numbering.jaxb;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import rs.numbering.format.Range;



/**
 * @author milosav.grubovic
 *
 */
public class OperationJaxb {
	

	 public static void rangeToXml(RangeJaxb rangeJaxb) {

		 try{
		   JAXBContext jaxbContext = JAXBContext.newInstance(RangeJaxb.class);
		   Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		        
		   jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	       jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16" );
		        
		   File writeRanges = new File("Ranges.xml");
		        
		   FileWriter fileWriteContry = new FileWriter(writeRanges,true);
		   jaxbMarshaller.marshal(rangeJaxb, fileWriteContry);
		   
		   //jaxbMarshaller.marshal(rangeJaxb, System.out);
		   //System.out.println(rangeJaxb.getMg() + "/ " + rangeJaxb.getStartRange() + "-" + rangeJaxb.getEndRange() );
		        
		   }catch(IOException ex){  System.out.print( "File proplem "+ ex.toString());
		   }catch(JAXBException ex){ System.out.println(" " + ex.toString());
		   }
	 }	
	
	 public static void listRangeToXml(ListRangeJaxb listRangeJaxb) {

	     try{
	         JAXBContext jaxbContext = JAXBContext.newInstance(ListRangeJaxb.class);
	         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	         
	         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	         jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16" );
         
	         File writeRange = new File("RangesList.xml");
	         
	         //!!! this was the problem of strange behavior of xml file
	         //FileWriter fileWriteRange = new FileWriter(writeRange);
	         
	         jaxbMarshaller.marshal(listRangeJaxb, writeRange);
		        
		   }catch(JAXBException ex){ 
			   System.out.println(" " + ex.toString());
			   ex.printStackTrace();
		   }
	 }	
	 
	 public static void xmlToListRange() {
	       try {
	           File file = new File ("RangesList.xml");//C:\Rad\eclipse\numberingRS
	           JAXBContext jaxbContext = JAXBContext.newInstance(ListRangeJaxb.class);
	           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	           //jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16"); doesn't work with this
	           ListRangeJaxb report =(ListRangeJaxb)jaxbUnmarshaller.unmarshal(file);

/*	            another way to read the xml file
	            InputStream inputStream = new FileInputStream("RangesList.xml");
	            Reader reader = new InputStreamReader(inputStream, "UTF-16");
	            ListRangeJaxb report =(ListRangeJaxb)jaxbUnmarshaller.unmarshal(reader);
*/
	            
	            System.out.println(report);
	        } catch (JAXBException ex) {
	        	//System.out.println(ex.toString());
	        	//ex.printStackTrace();
	           Logger.getLogger(OperationJaxb.class.getName()).log(Level.SEVERE, null, ex);
	        } 
 /*
  * 		this is in the case of using another way with InputStream
	       	catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			
	 }

	 public static void xmlToAreaCode() {
	       try {
	           File file = new File ("C:\\Rad\\data\\MreznaGrupaSingl.xml");
	           JAXBContext jaxbContext = JAXBContext.newInstance(AreaCodeJaxb.class);
	           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	           //jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //doesn't work with this
	           FileReader fr = new FileReader(file);
	           AreaCodeJaxb report =(AreaCodeJaxb)jaxbUnmarshaller.unmarshal(fr);

	            //another way to read the xml file
/*	            InputStream inputStream = new FileInputStream(file);
	            Reader reader = new InputStreamReader(inputStream, "UTF-8");
	            AreaCodeJaxb report =(AreaCodeJaxb)jaxbUnmarshaller.unmarshal(reader);
*/
	            
	            System.out.println(report.getCode() +
	            		" cyr is " + report.getCityCyr() +
	            		" lat is " + report.getCityLat());
	        } catch (JAXBException ex) {
	        	//System.out.println(ex.toString());
	        	//ex.printStackTrace();
	           Logger.getLogger(OperationJaxb.class.getName()).log(Level.SEVERE, null, ex);
	        } 

 		//this is in the case of using another way with InputStream
	       	catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
/*	       catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			
			
	 }
	 
	 public static void xmlToListAreaCode() {
	       try {
	           File file = new File ("C:\\Rad\\data\\MreznaGrupa.xml");
	           JAXBContext jaxbContext = JAXBContext.newInstance(ListAreaCodeJaxb.class);
	           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	           //jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16"); doesn't work with this
	           ListAreaCodeJaxb report =(ListAreaCodeJaxb)jaxbUnmarshaller.unmarshal(file);

/*	            another way to read the xml file
	            InputStream inputStream = new FileInputStream("RangesList.xml");
	            Reader reader = new InputStreamReader(inputStream, "UTF-16");
	            ListRangeJaxb report =(ListRangeJaxb)jaxbUnmarshaller.unmarshal(reader);
*/
	            
	            System.out.println(report);
	        } catch (JAXBException ex) {
	        	//System.out.println(ex.toString());
	        	//ex.printStackTrace();
	           Logger.getLogger(OperationJaxb.class.getName()).log(Level.SEVERE, null, ex);
	        } 
/*
* 		this is in the case of using another way with InputStream
	       	catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			
	 }

	 public static ListAreaCodeJaxb xmlToListAreaCode(String filePath) {
		 ListAreaCodeJaxb report = null;
	     try {
	           File file = new File (filePath);
	           //File file = new File ("C:\\Rad\\data\\MreznaGrupa.xml");
	           JAXBContext jaxbContext = JAXBContext.newInstance(ListAreaCodeJaxb.class);
	           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	           //jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16"); doesn't work with this
	           report =(ListAreaCodeJaxb)jaxbUnmarshaller.unmarshal(file);
	            
	            //System.out.println(report);
	            
	     }catch (JAXBException ex) {
	        	//System.out.println(ex.toString());
	        	//ex.printStackTrace();
	           Logger.getLogger(OperationJaxb.class.getName()).log(Level.SEVERE, null, ex);
	     }
	     return report; 
	 }
	 
	 public static void listRangeToXml(List <Range> rangesMain, File writeRange) {
			
		 ListRangeJaxb listRangeJaxb = new ListRangeJaxb();
			for(Range rangeItem: rangesMain){
				RangeJaxb rangeJaxb = new RangeJaxb(rangeItem);
				listRangeJaxb.add(rangeJaxb);
			}
	     try{
	         JAXBContext jaxbContext = JAXBContext.newInstance(ListRangeJaxb.class);
	         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	         
	         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	         //jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-16" );
         
	         //File writeRange = new File("C:\\Rad\\data\\RangesList02032018.xml");
	         
	         //!!! this was the problem of strange behavior of xml file
	         //FileWriter fileWriteRange = new FileWriter(writeRange);
	         
	         jaxbMarshaller.marshal(listRangeJaxb, writeRange);
		        
		   }catch(JAXBException ex){ 
			   System.out.println(" " + ex.toString());
			   ex.printStackTrace();
		   }
	 }
}
