package com.managerauth.model;

import java.io.Serializable;

import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;

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
	public ManagerAuthrizationFunctionVO getOneManagerAuthrizationFunctionVO() {
		com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService managerAuthrizationFunctionSvc = new ManagerAuthrizationFunctionService();
		ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO = managerAuthrizationFunctionSvc.getOneManagerAuthrizationFunction(managerAuthrizationFunctionNo);
		return managerAuthrizationFunctionVO;
	}
}
