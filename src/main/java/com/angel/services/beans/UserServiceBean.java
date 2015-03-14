package com.angel.services.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "User")
@XmlType(propOrder = { "id","userid", "lat", "lon" })
public class UserServiceBean {
	private int Id;
    private String Userid;
    private String Lat;
    private String Lon;
    
    public UserServiceBean() {} // JAXB needs this

    public UserServiceBean(int Id, String Userid,String Lat,String Lon) {
      this.Id = Id;
     this.Userid = Userid;
     this.Lat = Lat;
     this.Lon = Lon;
    }
    
    @XmlElement(name = "Id")
    public int getId() {
        return Id;
    }

    public void setId(int id) {
    	Id = id;
    }

    @XmlElement(name = "Userid")
    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
    	Userid = userid;
    }

    @XmlElement(name = "Lat")
    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
    	Lat = lat;
    }

    @XmlElement(name = "Lon")
    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
    	Lon = lon;
    }

}
