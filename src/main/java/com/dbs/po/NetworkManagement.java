package com.dbs.po;

import java.util.Date;

public class NetworkManagement {
	private Roominformation roominformation;
	public Roominformation getRoominformation() {
		return roominformation;
	}
	public void setRoominformation(Roominformation roominformation) {
		this.roominformation = roominformation;
	}
	private String n_serialnumber;
	private int n_roomnumber;
	private int n_customernumbernumber;
	private Date n_opentime;
	private Date n_closetime;
	public String getN_serialnumber() {
		return n_serialnumber;
	}
	public void setN_serialnumber(String n_serialnumber) {
		this.n_serialnumber = n_serialnumber;
	}
	public int getN_roomnumber() {
		return n_roomnumber;
	}
	public void setN_roomnumber(int n_roomnumber) {
		this.n_roomnumber = n_roomnumber;
	}
	public int getN_customernumbernumber() {
		return n_customernumbernumber;
	}
	public void setN_customernumbernumber(int n_customernumbernumber) {
		this.n_customernumbernumber = n_customernumbernumber;
	}
	public Date getN_opentime() {
		return n_opentime;
	}
	public void setN_opentime(Date n_opentime) {
		this.n_opentime = n_opentime;
	}
	public Date getN_closetime() {
		return n_closetime;
	}
	public void setN_closetime(Date n_closetime) {
		this.n_closetime = n_closetime;
	}
	



}
