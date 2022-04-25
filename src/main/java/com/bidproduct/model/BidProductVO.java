package com.bidproduct.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.bidapplylist.model.BidApplyListService;
import com.gameplatformtype.model.GamePlatformTypeService;

public class BidProductVO implements Serializable {
	// 建立承接 bidproduct 表格的 Value Object
	
	public BidProductVO() {
		
	}
	
	private Integer bidProductNo;
	private Integer bidApplyListNo;
	private Integer productNo;
	private String bidName;
	private String bidProdDescription;
	private Integer buyerNo;
	private Integer sellerNo;
	private Integer initialPrice;
	private Integer bidState;
	private Timestamp bidLaunchedTime;
	private Timestamp bidSoldTime;
	private Integer bidWinnerPrice;
	private Integer bidPriceIncrement;
	private Integer orderState;
	private Integer pickupMethod;
	private Integer shippingFree;
	private String trackingNum;
	private Timestamp pickupTime;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	
	public Integer getBidProductNo() {
		return bidProductNo;
	}
	public void setBidProductNo(Integer bidProductNo) {
		this.bidProductNo = bidProductNo;
	}
	public Integer getBidApplyListNo() {
		return bidApplyListNo;
	}
	public void setBidApplyListNo(Integer bidApplyListNo) {
		this.bidApplyListNo = bidApplyListNo;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
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
	public Integer getBuyerNo() {
		return buyerNo;
	}
	public void setBuyerNo(Integer buyerNo) {
		this.buyerNo = buyerNo;
	}
	public Integer getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(Integer sellerNo) {
		this.sellerNo = sellerNo;
	}
	public Integer getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(Integer initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Integer getBidState() {
		return bidState;
	}
	public void setBidState(Integer bidState) {
		this.bidState = bidState;
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
	public Integer getBidWinnerPrice() {
		return bidWinnerPrice;
	}
	public void setBidWinnerPrice(Integer bidWinnerPrice) {
		this.bidWinnerPrice = bidWinnerPrice;
	}
	public Integer getBidPriceIncrement() {
		return bidPriceIncrement;
	}
	public void setBidPriceIncrement(Integer bidPriceIncrement) {
		this.bidPriceIncrement = bidPriceIncrement;
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
	public Integer getShippingFree() {
		return shippingFree;
	}
	public void setShippingFree(Integer shippingFree) {
		this.shippingFree = shippingFree;
	}
	public String getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	public Timestamp getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Timestamp pickupTime) {
		this.pickupTime = pickupTime;
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
    public com.bidapplylist.model.BidApplyListVO getBidApplyListVO() {
    	com.bidapplylist.model.BidApplyListService bidApplyListSvc = new BidApplyListService();
    	com.bidapplylist.model.BidApplyListVO bidApplyListVO = bidApplyListSvc.getOneBidApplyList(bidApplyListNo);
	    return bidApplyListVO;
    }
	
}
