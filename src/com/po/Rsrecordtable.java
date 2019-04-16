package com.po;

public class Rsrecordtable {
	private int id;
	private int id_r;
	private int id_s;
	private String email_r;
	private String email_s;
	private int id_email;
	private int isopen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_s() {
		return id_s;
	}
	public void setId_s(int id_s) {
		this.id_s = id_s;
	}
	
	public int getId_r() {
		return id_r;
	}
	public void setId_r(int id_r) {
		this.id_r = id_r;
	}
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public String getEmail_r() {
		return email_r;
	}
	public void setEmail_r(String email_r) {
		this.email_r = email_r;
	}
	public int getId_email() {
		return id_email;
	}
	public void setId_email(int id_email) {
		this.id_email = id_email;
	}
	public String getEmail_s() {
		return email_s;
	}
	public void setEmail_s(String email_s) {
		this.email_s = email_s;
	}
}
