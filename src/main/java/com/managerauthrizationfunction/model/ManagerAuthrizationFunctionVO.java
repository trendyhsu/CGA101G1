package com.managerauthrizationfunction.model;

import java.io.Serializable;


public class ManagerAuthrizationFunctionVO implements Serializable{
	private Integer managerAuthrizationFunctionNo;
	private String managerAuthrizationFunction;
	
	
	public Integer getManagerAuthrizationFunctionNo() {
		return managerAuthrizationFunctionNo;
	}
	public void setManagerAuthrizationFunctionNo(Integer managerAuthrizationFunctionNo) {
		this.managerAuthrizationFunctionNo = managerAuthrizationFunctionNo;
	}
	public String getManagerAuthrizationFunction() {
		return managerAuthrizationFunction;
	}
	public void setManagerAuthrizationFunction(String managerAuthrizationFunction) {
		this.managerAuthrizationFunction = managerAuthrizationFunction;
	}
}
