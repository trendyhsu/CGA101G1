package com.manager.model;

import java.io.Serializable;

import com.core.javaBeans.Core;
import com.managerauth.model.ManagerAuthService;
import com.managerauth.model.ManagerAuthVO;

public class ManagerVO extends Core implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer managerNo;
	private String managerAccount;
	private String managerPassword;
	private String managerName;
	private String managerPhone;
	private byte[] myManagerPic;
	private Integer managerState;
	
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public byte[] getMyManagerPic() {
		return myManagerPic;
	}
	public void setMyManagerPic(byte[] myManagerPic) {
		this.myManagerPic = myManagerPic;
	}
	public Integer getManagerState() {
		return managerState;
	}
	public void setManagerState(Integer managerState) {
		this.managerState = managerState;
	}
	public com.managerauth.model.ManagerAuthVO getOneMangerAuthVO(){
		com.managerauth.model.ManagerAuthService managerAuthSvc = new ManagerAuthService();
		ManagerAuthVO managerAuthVO = managerAuthSvc.getOneManagerAuth(managerNo);
		return managerAuthVO;
	}
}
