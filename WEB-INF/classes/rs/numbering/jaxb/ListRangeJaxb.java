package rs.numbering.jaxb;

import javax.xml.bind.annotation.XmlRootElement;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import java.util.List;
import java.util.ArrayList;

/**
 * @author milosav.grubovic
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Ranges")
public class ListRangeJaxb {

	@XmlElement(name = "Range")
	public ArrayList <RangeJaxb> rangesJaxb = new ArrayList<>();
    
    //@XmlElement(name = "Range")
    public void setRangesJaxb(ArrayList <RangeJaxb> rangesJaxb){
        this.rangesJaxb =  rangesJaxb;
    }

    public ArrayList <RangeJaxb> getRangesJaxb() {
        return rangesJaxb;
    }

    
    public void add(RangeJaxb rangeJaxb){
         this.rangesJaxb.add(rangeJaxb);
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(RangeJaxb item: rangesJaxb){
            str.append(item);
            str.append("\n");
        }
        return str.toString();
    }
}

