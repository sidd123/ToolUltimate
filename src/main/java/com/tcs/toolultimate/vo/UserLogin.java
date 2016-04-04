package com.tcs.toolultimate.vo;

import java.io.Serializable;

public class UserLogin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6099863630528486645L;
	
	private String username;
	private String password;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
