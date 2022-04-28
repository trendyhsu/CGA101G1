package com.managerauth.model;

import java.io.Serializable;

public class ManagerAuthVO implements Serializable{
	private Integer managerNo;
	private Integer managerAuthrizationFunctionNo;
	
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public Integer getManagerAuthrizationFunctionNo() {
		return managerAuthrizationFunctionNo;
	}
	public void setManagerAuthrizationFunctionNo(Integer managerAuthrizationFunctionNo) {
		this.managerAuthrizationFunctionNo = managerAuthrizationFunctionNo;
	}
}
