package com.bidapplylist.model;

import java.sql.Timestamp;
import java.util.List;

public class BidApplyListService {
	private BidApplyListDAO_interface dao;

	public BidApplyListService() {
		dao = new BidApplyListJDBCDAO();
	}

	// 新增 BidApplyList
	public BidApplyListVO addBidApplyList(Integer memNo, String bidName, String bidProdDescription,
			Integer gameCompanyNo, Integer gameTypeNo, Integer gamePlatformNo, Integer initialPrice,
			Integer bidPriceIncrement, String upcNum, Timestamp bidLaunchedTime, Timestamp bidSoldTime,
			Integer applyState) {
		BidApplyListVO bidApplyListVO = new BidApplyListVO();
		bidApplyListVO.setMemNo(memNo);
		bidApplyListVO.setBidName(bidName);
		bidApplyListVO.setBidProdDescription(bidProdDescription);
		bidApplyListVO.setGameCompanyNo(gameCompanyNo);
		bidApplyListVO.setGameTypeNo(gameTypeNo);
		bidApplyListVO.setGamePlatformNo(gamePlatformNo);
		bidApplyListVO.setInitialPrice(initialPrice);
		bidApplyListVO.setBidPriceIncrement(bidPriceIncrement);
		bidApplyListVO.setUpcNum(upcNum);
		bidApplyListVO.setBidLaunchedTime(bidLaunchedTime);
		bidApplyListVO.setBidSoldTime(bidSoldTime);
		bidApplyListVO.setApplyState(applyState);
		dao.insert(bidApplyListVO);

		return bidApplyListVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
//	public void addBidApplyList(BidApplyListVO bidApplyListVO) {
//		dao.insert(bidApplyListVO);
//	}

	public BidApplyListVO updateBidApplyList(Integer bidApplyListNo, Integer memNo, String bidName,
			String bidProdDescription, Integer gameCompanyNo, Integer gameTypeNo, Integer gamePlatformNo,
			Integer initialPrice, Integer bidPriceIncrement, String upcNum, Timestamp bidLaunchedTime,
			Timestamp bidSoldTime, Integer applyState) {
		BidApplyListVO bidApplyListVO = new BidApplyListVO();
		bidApplyListVO.setBidApplyListNo(bidApplyListNo);
		bidApplyListVO.setMemNo(memNo);
		bidApplyListVO.setBidName(bidName);
		bidApplyListVO.setBidProdDescription(bidProdDescription);
		bidApplyListVO.setGameCompanyNo(gameCompanyNo);
		bidApplyListVO.setGameTypeNo(gameTypeNo);
		bidApplyListVO.setGamePlatformNo(gamePlatformNo);
		bidApplyListVO.setInitialPrice(initialPrice);
		bidApplyListVO.setBidPriceIncrement(bidPriceIncrement);
		bidApplyListVO.setUpcNum(upcNum);
		dao.update(bidApplyListVO);

		return dao.findByPrimaryKey(bidApplyListNo);
	}

	// 預留給 Struts 2 用的
//	public void updateBidApplyList(BidApplyListVO bidApplyListVO) {
//		dao.update(bidApplyListVO);
//	}

	public void deleteBidApplyList(Integer bidApplyListNo) {
		dao.delete(bidApplyListNo);
	}

	// 用 BidApplyListNo 取得一筆資料
	public BidApplyListVO getOneBidApplyList(Integer bidApplyListNo) {
		return dao.findByPrimaryKey(bidApplyListNo);
	}

	// 用 MemNo 取得所有資料
	public List<BidApplyListVO> getAllBidApplyListByMemNo(Integer memNo) {
		return dao.findByMemNo(memNo);
	}

	// 取得所有 BidApplyList
	public List<BidApplyListVO> getAll() {
		return dao.getAll();
	}

	public void updateApplyState(Integer bidApplyListNo, Integer applyState) {
		BidApplyListVO bidApplyListVO = new BidApplyListVO();
		
		// 設定申請單編號 及 退貨狀態
		bidApplyListVO.setBidApplyListNo(bidApplyListNo);
		bidApplyListVO.setApplyState(applyState);
		
		dao.updateApplyState(bidApplyListVO);
	}

}
