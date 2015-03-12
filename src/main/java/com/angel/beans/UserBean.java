package com.angel.beans;

import java.sql.Timestamp;

public class UserBean {

	private int id;
	private String userid;
	private String lat;
	private String lon;   
	private int status;

	public UserBean() {

	}

	public UserBean(String userId, String lat, String lon) {
		this.userid = userid;
		this.lat = lat;
		this.lon = lon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
