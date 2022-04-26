package com.memCoupon.model;

import java.sql.Date;

public class MemCouponVO {

	private Integer memCouponNo;
	private Integer couponTypeNo;
	private Integer memNo;
	private Integer couponState;
	private Date couponDate;
	
	public Date getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(Date couponDate) {
		this.couponDate = couponDate;
	}
	public Integer getMemCouponNo() {
		return memCouponNo;
	}
	public void setMemCouponNo(Integer memCouponNo) {
		this.memCouponNo = memCouponNo;
	}
	public Integer getCouponTypeNo() {
		return couponTypeNo;
	}
	public void setCouponTypeNo(Integer couponTypeNo) {
		this.couponTypeNo = couponTypeNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getCouponState() {
		return couponState;
	}
	public void setCouponState(Integer couponState) {
		this.couponState = couponState;
	}
	
	
}
