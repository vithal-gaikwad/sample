package com.angel.beans;

import java.util.Date;

public class UserBean {

	private int id;
	private String userId;
	private String lat;
	private String lon;  
	private Date lastUpdated;   
	private int status;

	public UserBean() {

	}

	public UserBean(String userId, String lat, String lon, Date lastUpdated) {
		this.userId = userId;
		this.lat = lat;
		this.lon = lon;
		this.lastUpdated =lastUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
