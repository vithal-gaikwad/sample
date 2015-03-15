package com.angel.services.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlRootElement(name = "User")
@XmlType(propOrder = { "id","userId", "lat", "lon", "lastUpdated" })
public class UserServiceBean {
	private int id;
    private String userId;
    private String lat;
    private String lon;
    private Date lastUpdated;
    
    public UserServiceBean() {} // JAXB needs this

    public UserServiceBean(int id, String userId,String lat,String lon, Date lastUpdated) {
      this.id = id;
     this.userId = userId;
     this.lat = lat;
     this.lon =lon;
     this.lastUpdated =lastUpdated;
    }
    
    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
    	this.id = id;
    }

    @XmlElement(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
    	this.userId = userId;
    }

    @XmlElement(name = "lat")
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
    	this.lat = lat;
    }

    @XmlElement(name = "lon")
    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
    	this.lon = lon;
    }
    
    @XmlElement(name = "lastUpdated")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
