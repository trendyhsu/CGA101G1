package com.bidproduct.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BidProductService {
	private BidProductDAO_interface dao;

	public BidProductService() {
		dao = new BidProductJDBCDAO();
	}

	public Integer addBidProduct(Integer bidApplyListNo, Integer productNo, String bidName, String bidProdDescription,
			Integer sellerNo, Integer initialPrice, Integer bidState, Timestamp bidLaunchedTime, Timestamp bidSoldTime,
			Integer bidPriceIncrement, Integer orderState) {

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

		Integer nextBidProductNo = dao.insert(bidProductVO);
		return nextBidProductNo;
	}
	
	public Integer addBidProductWithoutProduct(Integer bidApplyListNo, String bidName, String bidProdDescription,
			Integer sellerNo, Integer initialPrice, Integer bidState, Timestamp bidLaunchedTime, Timestamp bidSoldTime,
			Integer bidPriceIncrement, Integer orderState) {

		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidApplyListNo(bidApplyListNo);
		bidProductVO.setBidName(bidName);
		bidProductVO.setBidProdDescription(bidProdDescription);
		bidProductVO.setSellerNo(sellerNo);
		bidProductVO.setInitialPrice(initialPrice);
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidLaunchedTime(bidLaunchedTime);
		bidProductVO.setBidSoldTime(bidSoldTime);
		bidProductVO.setBidPriceIncrement(bidPriceIncrement);
		bidProductVO.setOrderState(orderState);

		Integer nextBidProductNo = dao.insertWithoutProduct(bidProductVO);
		return nextBidProductNo;
	}

	// ????????? Struts 2 ??? Spring MVC ???
//	public Integer addBidProduct(BidProductVO bidProductVO) {
//		return dao.insert(bidProductVO);
//	}

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
	
	public BidProductVO updateBidProductWithoutProduct(Integer bidProductNo, Integer bidApplyListNo,
			String bidName, String bidProdDescription, Integer buyerNo, Integer sellerNo, Integer initialPrice,
			Integer bidState, Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer bidWinnerPrice,
			Integer bidPriceIncrement, Integer orderState, String receiverName, String receiverAddress,
			String receiverPhone) {

		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidProductNo(bidProductNo);
		bidProductVO.setBidApplyListNo(bidApplyListNo);
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

		dao.updateWithoutProduct(bidProductVO);

		return dao.findByPrimaryKey(bidProductNo);
	}


	// ????????? Struct 2 ??????
//	public void updateBidProduct(BidProductVO bidProductVO) {
//		dao.update(bidProductVO);
//	}

	public void deleteBidProduct(Integer bidProductNo) {
		dao.delete(bidProductNo);
	}

	public BidProductVO getOneBid(Integer bidProductNo) {
		return dao.findByPrimaryKey(bidProductNo);
	}

	public List<BidProductVO> getAll() {
		return dao.getAll();

	}

	// ?????? bidApplyListNo ????????????
	public BidProductVO getByBidApplyListNo(Integer bidApplyListNo) {
		return dao.findByBidApplyListNo(bidApplyListNo);
	}

	// ?????? buyerNo ????????????????????????
	public List<BidProductVO> getAllByBuyNo(Integer buyerNo) {
		return dao.findByBuyerNo(buyerNo);
	}

	// ?????? sellerNo ????????????????????????
	public List<BidProductVO> getAllBySellerNo(Integer sellerNo) {
		return dao.findBySellerNo(sellerNo);
	}

	// ?????? bidName ???????????? ?????? bidName ?????????
	public List<BidProductVO> getAllByBidName(String BidName) {
		return dao.findByBidName(BidName);
	}

	// ?????????????????? BidState ?????? 0 (?????????) ?????? ?????????????????????????????? ( ?????????????????? )
	public List<BidProductVO> getByBidStateAndSoldTime() {
		return dao.findByBidStateAndSoldTime();
	}

	// ?????????????????? BidState ?????? 1 (?????????????????????????????????)
	public List<BidProductVO> getByBidState() {
		return dao.findByBidState();
	}

	// ?????? bidProductNo ??????????????????
	public void updateBidState(Integer bidState, Integer bidProductNo) {
		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidProductNo(bidProductNo);
		dao.updateBidState(bidProductVO);
	}

	// ?????? bidProductNo ?????????????????? ( ????????? )
	public void updateBidStateHaveBuyer(Integer bidState, Integer buyerNo, Integer bidWinnerPrice,
			Integer bidProductNo) {
		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidState(bidState);
		bidProductVO.setBuyerNo(buyerNo);
		bidProductVO.setBidWinnerPrice(bidWinnerPrice);
		bidProductVO.setBidProductNo(bidProductNo);
		dao.updateBidStateHaveBuyer(bidProductVO);
	}

	// ??????orderState???????????? ?????? ?????? ???????????? ?????? ?????? ?????? ?????? ?????????
	public void updateOrderState(Integer orderState, Integer bidProductNo) {
		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setOrderState(orderState);
		bidProductVO.setBidProductNo(bidProductNo);
		dao.updateOrderStateGetbackAndRelist(bidProductVO);
	}

	// ?????????????????????????????????
	public void updateReceiverAndOrderState(Integer orderState, String receiverName, String receiverAddress,
			String receiverPhone, Integer bidProductNo) {
		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setOrderState(orderState);
		bidProductVO.setReceiverName(receiverName);
		bidProductVO.setReceiverAddress(receiverAddress);
		bidProductVO.setReceiverPhone(receiverPhone);
		bidProductVO.setBidProductNo(bidProductNo);

		dao.updateReceiverAndOrderState(bidProductVO);
	}

	// ??????????????? ( BidState = 1 ) 30????????????????????? ??? BidState ?????? ??????
	public List<BidProductVO> getByBidStateAndOrderState() {
		return dao.findByBidStateAndOrderState();
	}

	// ????????????????????????
	public void updateByBackend(Integer bidProductNo, Integer bidApplyListNo, Integer productNo, String bidName,
			String bidProdDescription, Integer initialPrice, Integer bidState, Integer bidPriceIncrement,
			Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer orderState, String receiverName,
			String receiverAddress, String receiverPhone) {

		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidProductNo(bidProductNo);
		bidProductVO.setBidApplyListNo(bidApplyListNo);
		bidProductVO.setProductNo(productNo);
		bidProductVO.setBidName(bidName);
		bidProductVO.setBidProdDescription(bidProdDescription);
		bidProductVO.setInitialPrice(initialPrice);
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidPriceIncrement(bidPriceIncrement);
		bidProductVO.setBidLaunchedTime(bidLaunchedTime);
		bidProductVO.setBidSoldTime(bidSoldTime);
		bidProductVO.setOrderState(orderState);
		bidProductVO.setReceiverName(receiverName);
		bidProductVO.setReceiverAddress(receiverAddress);
		bidProductVO.setReceiverPhone(receiverPhone);

		dao.updateByBackend(bidProductVO);
	}
	// ????????????????????????
	public void updateByBackendWithoutProduct(Integer bidProductNo, Integer bidApplyListNo, String bidName,
			String bidProdDescription, Integer initialPrice, Integer bidState, Integer bidPriceIncrement,
			Timestamp bidLaunchedTime, Timestamp bidSoldTime, Integer orderState, String receiverName,
			String receiverAddress, String receiverPhone) {

		BidProductVO bidProductVO = new BidProductVO();
		bidProductVO.setBidProductNo(bidProductNo);
		bidProductVO.setBidApplyListNo(bidApplyListNo);
		bidProductVO.setBidName(bidName);
		bidProductVO.setBidProdDescription(bidProdDescription);
		bidProductVO.setInitialPrice(initialPrice);
		bidProductVO.setBidState(bidState);
		bidProductVO.setBidPriceIncrement(bidPriceIncrement);
		bidProductVO.setBidLaunchedTime(bidLaunchedTime);
		bidProductVO.setBidSoldTime(bidSoldTime);
		bidProductVO.setOrderState(orderState);
		bidProductVO.setReceiverName(receiverName);
		bidProductVO.setReceiverAddress(receiverAddress);
		bidProductVO.setReceiverPhone(receiverPhone);

		dao.updateByBackendWithoutProduct(bidProductVO);
	}

	public List<BidProductVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
