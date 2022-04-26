package com.order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.orderdetail.model.OrderDetailDAO;
import com.orderdetail.model.OrderDetailDAO_interface;
import com.orderdetail.model.OrderDetailVO;

public class OrderVO implements java.io.Serializable{
	private Integer orderNo;
	private Integer memNo;
	private Integer memCouponNo;
	private Timestamp tranTime;
	private Integer orderTotalPrice;
	private Integer orderState;
	private Integer pickupMethod;
	private Integer shippingFee;
	private String trackingNum;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private Date pickupTime;

	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getMemCouponNo() {
		return memCouponNo;
	}
	public void setMemCouponNo(Integer memCouponNo) {
		this.memCouponNo = memCouponNo;
	}
	public Timestamp getTranTime() {
		return tranTime;
	}
	public void setTranTime(Timestamp tranTime) {
		this.tranTime = tranTime;
	}
	public Integer getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(Integer orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getPickupMethod() {
		return pickupMethod;
	}
	public void setPickupMethod(Integer pickupMethod) {
		this.pickupMethod = pickupMethod;
	}
	public Integer getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(Integer shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public Date getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}
	public List<OrderDetailVO> GetAllDetailByOrderNo(Integer orderNo){
		OrderDetailDAO_interface dao = new OrderDetailDAO();	
		return dao.findAllDetailByOrderNo(orderNo);
	}
}

