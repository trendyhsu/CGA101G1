package com.core.javaBeans;

import java.io.Serializable;
public class Core implements Serializable {
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;
	private String message;
	private String initlocation;

	public Core() {
	}
	public String getInitlocation() {
		return initlocation;
	}
	
	public void setInitlocation(String initlocation) {
		this.initlocation = initlocation;
	}

	public Core(boolean successful, String message) {
		this.successful = successful;
		this.message = message;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
