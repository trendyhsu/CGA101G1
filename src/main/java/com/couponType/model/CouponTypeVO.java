package com.couponType.model;

import java.sql.Date;

public class CouponTypeVO {

	private Integer couponTypeNo;
	private String couponName;
	private Integer discountPrice;
	private Date couponDeadline;
	private Integer couponQuantity;
	private String couponDescription;
	
	public Integer getCouponTypeNo() {
		return couponTypeNo;
	}
	public void setCouponTypeNo(Integer couponTypeNo) {
		this.couponTypeNo = couponTypeNo;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Date getCouponDeadline() {
		return couponDeadline;
	}
	public void setCouponDeadline(Date couponDeadline) {
		this.couponDeadline = couponDeadline;
	}
	public Integer getCouponQuantity() {
		return couponQuantity;
	}
	public void setCouponQuantity(Integer couponQuantity) {
		this.couponQuantity = couponQuantity;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	
	
	
}
