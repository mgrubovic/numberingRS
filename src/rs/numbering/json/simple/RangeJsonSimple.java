package rs.numbering.json.simple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import rs.numbering.format.Range;

public class RangeJsonSimple {
	public static void main(String[] args) {
		
		RangeJsonSimple jr= new RangeJsonSimple();
		
	    // creating JSONObject 
		JSONObject jo = jr.prepareJson();
		// writing JSON to file: RANGES.json 
		jr.jsonToFile(jo, "C:\\Rad\\RANGES1.json");
		
		jo = jr.fileToJson("C:\\Rad\\RANGES.json");
		jr.readJson(jo);
    } 
	
	public JSONObject prepareJson(List<Range> rangeList){
        JSONObject jo = new JSONObject(); 
        // for many Ranges create JSONArray  
        JSONArray ja = new JSONArray(); 
        
        for(Range item: rangeList){
            // for mg startRange, endRange and operator create LinkedHashMap 
            Map<String, Object> m = new LinkedHashMap<>(4); 
            m.put("mg", item.mg); 
            m.put("startRange", item.startRange); 
            m.put("endRange", item.endRange); 
            m.put("operator", item.operator); 
            // adding map to list 
            ja.add(m); 
            
        }
        jo.put("ranges", ja);
        System.out.println(jo.toJSONString());
        return jo;
	}
	
	public JSONObject prepareJson(){
        JSONObject jo = new JSONObject(); 
        // for many Ranges create JSONArray  
        JSONArray ja = new JSONArray(); 
        
        
        // for mg startRange, endRange and operator create LinkedHashMap 
        Map<String, Object> m = new LinkedHashMap<>(4); 
        m.put("mg", "21"); 
        m.put("startRange", "3180000"); 
        m.put("endRange", "3180999"); 
        m.put("operator", "RINGTEL"); 
        // adding map to list 
        ja.add(m); 
        
        Map<String, Object> mh = new LinkedHashMap<>(); 
        mh.put("mg", "11"); 
        mh.put("startRange", "4180000"); 
        mh.put("endRange", "4184999"); 
        mh.put("operator", "RINGTEL"); 
        // adding map to list 
        ja.add(mh); 
          
        // putting list (ranges) to JSONObject 
        jo.put("ranges", ja);
        
        return jo;
	}
	
	public void readJson(JSONObject jo){
		JSONArray ja = (JSONArray) jo.get("ranges");
	
        Iterator itr1 = ja.iterator(); 
        
        while (itr1.hasNext())  
        { 
        	Map<String, Object> mh  = (Map<String, Object>) itr1.next();
        	Iterator itr2 = mh.entrySet().iterator();
        	while(itr2.hasNext()){
        		Map.Entry<String, Object> pair = (Entry<String, Object>) itr2.next();
        		System.out.println(pair.getKey() + " : " + pair.getValue());
        	}
            System.out.println(mh); 
           
        } 
 
	}
	
	
	public JSONObject fileToJson(String fileName){
		// parsing file fileName
		JSONObject jo=null;
		try {
			Object obj = new JSONParser().parse(new FileReader(fileName));
		    // typecasting obj to JSONObject 
	        jo = (JSONObject) obj; 			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} 
		return jo; 
	}
	
	public void  jsonToFile(JSONObject jo, String fileName){
        PrintWriter pw;
		try {
			pw = new PrintWriter(fileName);

        pw.write(jo.toJSONString()); 
        System.out.println(jo.toJSONString());
          
        pw.flush(); 
        pw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

}

