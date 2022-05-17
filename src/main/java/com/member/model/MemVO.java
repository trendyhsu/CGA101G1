package com.member.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.core.javaBeans.Core;

import lombok.Data;
@Entity
@Data
@Table(name = "mem")
public class MemVO extends Core{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MemNo")
	private Integer memNo;
	@Column(name = "MemAccount")
	private String memAccount;
	@Column(name = "MemPassword")
	private String memPassword;
	@Column(name = "MemStatus")
	private Integer memStatus;
	@Column(name = "MemVrfed")
	private Integer memVrfed;
	@Column(name = "MemNoVrftime")
	private Date memNoVrftime;
	@Column(name = "MemName")
	private String memName;
	@Column(name = "MemMobile")
	private String memMobile;
	@Column(name = "MemCity")
	private String memCity;
	@Column(name = "MemDist")
	private String memDist;
	@Column(name = "MemAdd")
	private String memAdd;
	@Column(name = "MemEmail")
	private String memEmail;
	@Column(name = "MemBirth")
	private Date memBirth;
	@Column(name = "MemJoinTime")
	private Date memJoinTime;
	@Column(name = "CreditcardNo")
	private String creditcardNo;
	@Column(name = "CreditcardDate")
	private String creditcardDate;
	@Column(name = "CreditcardSecurityNo")
	private String creditcardSecurityNo;
	@Column(name = "BankAccount")
	private String bankAccount;
	@Column(name = "BankAccountOwner")
	private String bankAccountOwner;
	@Column(name = "UserStatus")
	private Integer userStatus;
	@Column(name = "MyPic")
	private byte[] myPic;
	@Column(name = "IsMute")
	private Integer isMute;
	
	public Integer getIsMute() {
		return isMute;
	}
	public void setIsMute(Integer isMute) {
		this.isMute = isMute;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public Integer getMemVrfed() {
		return memVrfed;
	}
	public void setMemVrfed(Integer memVrfed) {
		this.memVrfed = memVrfed;
	}
	public Date getMemNoVrftime() {
		return memNoVrftime;
	}
	public void setMemNoVrftime(Date memNoVrftime) {
		this.memNoVrftime = memNoVrftime;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemMobile() {
		return memMobile;
	}
	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}
	public String getMemCity() {
		return memCity;
	}
	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}
	public String getMemDist() {
		return memDist;
	}
	public void setMemDist(String memDist) {
		this.memDist = memDist;
	}
	public String getMemAdd() {
		return memAdd;
	}
	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public Date getMemJoinTime() {
		return memJoinTime;
	}
	public void setMemJoinTime(Date memJoinTime) {
		this.memJoinTime = memJoinTime;
	}
	public String getCreditcardNo() {
		return creditcardNo;
	}
	public void setCreditcardNo(String creditcardNo) {
		this.creditcardNo = creditcardNo;
	}
	public String getCreditcardDate() {
		return creditcardDate;
	}
	public void setCreditcardDate(String creditcardDate) {
		this.creditcardDate = creditcardDate;
	}
	public String getCreditcardSecurityNo() {
		return creditcardSecurityNo;
	}
	public void setCreditcardSecurityNo(String creditcardSecurityNo) {
		this.creditcardSecurityNo = creditcardSecurityNo;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAccountOwner() {
		return bankAccountOwner;
	}
	public void setBankAccountOwner(String bankAccountOwner) {
		this.bankAccountOwner = bankAccountOwner;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public byte[] getMyPic() {
		return myPic;
	}
	public void setMyPic(byte[] myPic) {
		this.myPic = myPic;
	}
	
	
	
	
}
