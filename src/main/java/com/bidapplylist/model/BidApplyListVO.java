package com.bidapplylist.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class BidApplyListVO implements Serializable{
	// 建立承接 bidapplylist 表格的 Value Object
	Integer bidApplyListNo;
	Integer memNo;
	String bidName;
	String bidProdDescription;
	Integer gameCompanyNo;
	Integer gameTypeNo;
	Integer gamePlatformNo;
	Integer initialPrice;
	Integer bidPriceIncrement;
	String upcNum;
	Timestamp bidLaunchedTime;
	Timestamp bidSoldTime;
	Integer applyState;
	public BidApplyListVO() {
		
	}
	
	public Integer getBidApplyListNo() {
		return bidApplyListNo;
	}
	public void setBidApplyListNo(Integer bidApplyListNo) {
		this.bidApplyListNo = bidApplyListNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getBidName() {
		return bidName;
	}
	public void setBidName(String bidName) {
		this.bidName = bidName;
	}
	public String getBidProdDescription() {
		return bidProdDescription;
	}
	public void setBidProdDescription(String bidProdDescription) {
		this.bidProdDescription = bidProdDescription;
	}
	public Integer getGameCompanyNo() {
		return gameCompanyNo;
	}
	public void setGameCompanyNo(Integer gameCompanyNo) {
		this.gameCompanyNo = gameCompanyNo;
	}
	public Integer getGameTypeNo() {
		return gameTypeNo;
	}
	public void setGameTypeNo(Integer gameTypeNo) {
		this.gameTypeNo = gameTypeNo;
	}
	public Integer getGamePlatformNo() {
		return gamePlatformNo;
	}
	public void setGamePlatformNo(Integer gamePlatformNo) {
		this.gamePlatformNo = gamePlatformNo;
	}
	public Integer getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(Integer initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Integer getBidPriceIncrement() {
		return bidPriceIncrement;
	}
	public void setBidPriceIncrement(Integer bidPriceIncrement) {
		this.bidPriceIncrement = bidPriceIncrement;
	}
	public String getUpcNum() {
		return upcNum;
	}
	public void setUpcNum(String upcNum) {
		this.upcNum = upcNum;
	}
	public Timestamp getBidLaunchedTime() {
		return bidLaunchedTime;
	}
	public void setBidLaunchedTime(Timestamp bidLaunchedTime) {
		this.bidLaunchedTime = bidLaunchedTime;
	}
	public Timestamp getBidSoldTime() {
		return bidSoldTime;
	}
	public void setBidSoldTime(Timestamp bidSoldTime) {
		this.bidSoldTime = bidSoldTime;
	}
	public Integer getApplyState() {
		return applyState;
	}
	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}
}
