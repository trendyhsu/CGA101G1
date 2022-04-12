package com.bidrecord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class BidRecordVO implements Serializable{
	// 建立承接 bidrecord 表格的 Value Object
	Integer bidRecordNo;
	Integer bidProductNo;
	Integer memNo;
	Integer bidPrice;
	Timestamp bidTime;
	
	public BidRecordVO() {
		
	}
	
	public Integer getBidRecordNo() {
		return bidRecordNo;
	}
	public void setBidRecordNo(Integer bidRecordNo) {
		this.bidRecordNo = bidRecordNo;
	}
	public Integer getBidProductNo() {
		return bidProductNo;
	}
	public void setBidProductNo(Integer bidProductNo) {
		this.bidProductNo = bidProductNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Timestamp getBidTime() {
		return bidTime;
	}
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}

}
