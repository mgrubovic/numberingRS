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
@XmlRootElement(name="List_Area_Codes")
public class ListAreaCodeJaxb {

	@XmlElement(name = "Area_Code")
	public ArrayList <AreaCodeJaxb> areaCodeJaxb = new ArrayList<>();
    
    //@XmlElement(name = "Range")
    public void setRangesJaxb(ArrayList <AreaCodeJaxb> areaCodeJaxb){
        this.areaCodeJaxb =  areaCodeJaxb;
    }

    public ArrayList <AreaCodeJaxb> getRangesJaxb() {
        return areaCodeJaxb;
    }

    
    public void add(AreaCodeJaxb rangeJaxb){
         this.areaCodeJaxb.add(rangeJaxb);
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(AreaCodeJaxb item: areaCodeJaxb){
            str.append(item);
            str.append("\n");
        }
        return str.toString();
    }
}

