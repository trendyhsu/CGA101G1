package com.bidproduct.model;

import java.sql.Timestamp;
import java.util.List;

public class BidProductService {
	private BidProductDAO_interface dao;

	public BidProductService() {
		dao = new BidProductJDBCDAO();
	}

	public BidProductVO addBidProduct(Integer bidApplyListNo, Integer productNo, String bidName,
			String bidProdDescription, Integer sellerNo, Integer initialPrice, Integer bidState,
			Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer bidPriceIncrement) {

		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidApplyListNo(bidApplyListNo);
		bidProductVO.setProductNo(productNo);
		bidProductVO.setBidName(bidName);
		bidProductVO.setBidProdDescription(bidProdDescription);
		bidProductVO.setSellerNo(sellerNo);
		bidProductVO.setInitialPrice(initialPrice);
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidLaunchedTime(bidLaunchedTime);
		bidProductVO.setBidSoldTime(bidSoldTime);
		bidProductVO.setBidPriceIncrement(bidPriceIncrement);
		dao.insert(bidProductVO);

		return bidProductVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addBidProduct(BidProductVO bidProductVO) {
		dao.insert(bidProductVO);
	}

	public BidProductVO updateBidProduct(Integer bidProductNo, Integer bidApplyListNo, Integer productNo, String bidName,
			String bidProdDescription, Integer buyerNo, Integer sellerNo, Integer initialPrice, Integer bidState,
			Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer bidWinnerPrice, Integer bidPriceIncrement,
			Integer orderState, Integer pickupMethod, Integer shippingFree, String trackingNum, Timestamp pickupTime,
			String receiverName, String receiverAddress, String receiverPhone) {
		
		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidProductNo(bidProductNo);
		bidProductVO.setBidApplyListNo(bidApplyListNo);
		bidProductVO.setProductNo(productNo);
		bidProductVO.setBidName(bidName);
		bidProductVO.setBidProdDescription(bidProdDescription);
		bidProductVO.setBuyerNo(buyerNo);
		bidProductVO.setSellerNo(sellerNo);
		bidProductVO.setInitialPrice(initialPrice);
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidLaunchedTime(bidLaunchedTime);
		bidProductVO.setBidSoldTime(bidSoldTime);
		bidProductVO.setBidWinnerPrice(bidWinnerPrice);
		bidProductVO.setBidPriceIncrement(bidPriceIncrement);
		bidProductVO.setOrderState(orderState);
		bidProductVO.setPickupMethod(pickupMethod);
		bidProductVO.setShippingFree(shippingFree);
		bidProductVO.setTrackingNum(trackingNum);
		bidProductVO.setReceiverName(receiverName);
		bidProductVO.setReceiverAddress(receiverAddress);
		bidProductVO.setReceiverPhone(receiverPhone);
		
		dao.update(bidProductVO);
		
		return dao.findByPrimaryKey(bidProductNo);
	}
	
	// 預留給 Struct 2 用的
	public void updateBidProduct(BidProductVO bidProductVO) {
		dao.update(bidProductVO);
	}
	
	public void deleteBidProduct(Integer bidProductNo) {
		dao.delete(bidProductNo);
	}
	
	public BidProductVO getOneBid(Integer bidProductNo) {
		return dao.findByPrimaryKey(bidProductNo);
	}
	
	public List<BidProductVO> getAll(){
		return dao.getAll();

	}
	// 使用 buyerNo 取得所有競標商品
	public List<BidProductVO> getAllByMemNo(Integer buyerNo){
		return dao.findByBuyerNo(buyerNo);
	}
	
	public List<BidProductVO> getAllByBidName(String BidName){
		return dao.findByBidName(BidName);
	}

}
