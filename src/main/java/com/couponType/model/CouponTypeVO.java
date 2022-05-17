package com.couponType.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupontype")
public class CouponTypeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CouponTypeNo")
	private Integer couponTypeNo;
	@Column(name = "CouponName")
	private String couponName;
	@Column(name = "DiscountPrice")
	private Integer discountPrice;
	@Column(name = "CouponDeadline")
	private Date couponDeadline;
	@Column(name = "CouponQuantity")
	private Integer couponQuantity;
	@Column(name = "CouponDescription")
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
