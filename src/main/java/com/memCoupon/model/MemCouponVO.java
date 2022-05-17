package com.memCoupon.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.couponType.model.CouponTypeVO;
import com.member.model.MemVO;

@Entity
@Table(name = "memcoupon")
public class MemCouponVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MemCouponNo")
	private Integer memCouponNo;
	@Column(name = "CouponTypeNo")
	private Integer couponTypeNo;
	@Column(name = "MemNo")
	private Integer memNo;
	@Column(name = "CouponState", insertable = false)
	private Integer couponState;
	@Column(name = "CouponDate")
	private Date couponDate;
	@ManyToOne
	@JoinColumn(name = "CouponTypeNo", 
				insertable = false, updatable = false)
	private CouponTypeVO couponTypeVO;
	@ManyToOne
	@JoinColumn(name = "MemNo",
				insertable = false, updatable = false)
	private MemVO memVO;
	
	
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
//	public CouponTypeVO getCouponTypeVO() {
//		return couponTypeVO;
//	}
	public void setCouponTypeVO(CouponTypeVO couponTypeVO) {
		this.couponTypeVO = couponTypeVO;
	}
//	public MemVO getMemVO() {
//		return memVO;
//	}
	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}
	
	// join 
	public com.member.model.MemVO getMemVO(){
		com.member.model.MemService memService=new com.member.model.MemService();
		com.member.model.MemVO memVO =memService.getMemVObyMemNo(memNo);
		return memVO;
	}
	
	public com.couponType.model.CouponTypeVO getCouponTypeVO(){
		com.couponType.model.CouponTypeService couponTypeService=new com.couponType.model.CouponTypeService();
		com.couponType.model.CouponTypeVO couponTypeVO=couponTypeService.listOneCouponType(couponTypeNo);
		return couponTypeVO;
	}
	
}
