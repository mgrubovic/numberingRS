package rs.numbering.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import javax.xml.bind.annotation.XmlElement;



@XmlType(propOrder = {"code", "cityCyr",  "cityLat", "cityAnsi", "nonTS"})

@XmlRootElement(name="Area_Code")
public class AreaCodeJaxb {
	
	private String code;
	private String cityCyr;
	private String cityLat;
	private String cityAnsi;
	private int nonTS;
	
	public String getCode() {
		return code;
	}
	@XmlElement( name="Code")
	public void setCode(String code) {
		this.code = code;
	}
	public String getCityCyr() {
		return cityCyr;
	}
	@XmlElement( name="City_cyr")
	public void setCityCyr(String cityCyr) {
		this.cityCyr = cityCyr;
	}
	
	public String getCityLat() {
		return cityLat;
	}
	@XmlElement( name="City_lat")
	public void setCityLat(String cityLat) {
		this.cityLat = cityLat;
	}
	
	public String getCityAnsi() {
		return cityAnsi;
	}
	@XmlElement( name="City_ansi")
	public void setCityAnsi(String cityAnsi) {
		this.cityAnsi = cityAnsi;
	}
	
	public int getNonTS() {
		return nonTS;
	}
	@XmlElement( name="Non_TS")
	public void setNonTS(int nonTS) {
		this.nonTS = nonTS;
	}
	
	public String toString(){
		return (code + " cyr " + cityCyr + " lat " + cityLat );
	}

}
