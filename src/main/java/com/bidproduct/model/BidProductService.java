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
			Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer bidPriceIncrement, Integer orderState) {

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
		bidProductVO.setOrderState(orderState);
		dao.insert(bidProductVO);

		return bidProductVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public Integer addBidProduct(BidProductVO bidProductVO) {
		return dao.insert(bidProductVO);
	}

	public BidProductVO updateBidProduct(Integer bidProductNo, Integer bidApplyListNo, Integer productNo,
			String bidName, String bidProdDescription, Integer buyerNo, Integer sellerNo, Integer initialPrice,
			Integer bidState, Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer bidWinnerPrice,
			Integer bidPriceIncrement, Integer orderState, String receiverName, String receiverAddress,
			String receiverPhone) {

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

	public List<BidProductVO> getAll() {
		return dao.getAll();

	}

	// 使用 buyerNo 取得所有競標商品
	public List<BidProductVO> getAllByMemNo(Integer buyerNo) {
		return dao.findByBuyerNo(buyerNo);
	}

	// 使用 bidName 查詢所有 符合 bidName 的商品
	public List<BidProductVO> getAllByBidName(String BidName) {
		return dao.findByBidName(BidName);
	}
	// 查詢競標商品 BidState 等於 0 (競標中) 而且 截標時間小於目前時間 ( 為了改成流標 )
	public List<BidProductVO> getByBidStateAndSoldTime(){
		return dao.findByBidStateAndSoldTime();
	}
	// 使用 bidProductNo 更新競標狀態
	public void updateBidState(BidProductVO bidProductVO) {
		dao.updateBidState(bidProductVO);
	}
	// 更改收件資訊與商品狀態
	public void updateReceiverAndOrderState(BidProductVO bidProductVO) {
		dao.updateReceiverAndOrderState(bidProductVO);
	}
	// 查詢已截標 ( BidState = 1 ) 30分鐘後沒有付款 將 BidState 改為 棄標
	public List<BidProductVO> getByBidStateAndOrderState(){
		return dao.findByBidStateAndOrderState();
	}
	// 後臺更新競標資訊
	public void updateByBackend(BidProductVO bidProductVO) {
		dao.updateByBackend(bidProductVO);
	}
	

}
