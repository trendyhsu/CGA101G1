package com.bidrecord.model;

import java.sql.Timestamp;
import java.util.List;

public class BidRecordService {
	
	private BidRecordDAO_interface dao;

	public BidRecordService() {
		dao = new BidRecordJDBCDAO();
	}

	// 新增 BidRecord
	public BidRecordVO addBidRecord(Integer bidProductNo, Integer memNo, Integer bidPrice, Timestamp bidTime) {
		BidRecordVO bidRecordVO = new BidRecordVO();

		bidRecordVO.setBidProductNo(bidProductNo);
		bidRecordVO.setMemNo(memNo);
		bidRecordVO.setBidPrice(bidPrice);
		bidRecordVO.setBidTime(bidTime);
		dao.insert(bidRecordVO);

		return bidRecordVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addBidRecord(BidRecordVO bidRecordVO) {
		dao.insert(bidRecordVO);
	}

	public BidRecordVO updateBidRecord(Integer bidRecordNo, Integer bidProductNo, Integer memNo, Integer bidPrice,
			Timestamp bidTime) {
		BidRecordVO bidRecordVO = new BidRecordVO();

		bidRecordVO.setBidRecordNo(bidRecordNo);
		bidRecordVO.setBidProductNo(bidProductNo);
		bidRecordVO.setMemNo(memNo);
		bidRecordVO.setBidPrice(bidPrice);
		bidRecordVO.setBidTime(bidTime);
		dao.update(bidRecordVO);

		return dao.findByPrimaryKey(bidRecordNo);
	}

	// 預留給 Struts 2 用的
//	public void updateBidRecord(BidRecordVO bidRecordVO) {
//		dao.update(bidRecordVO);
//	}

	public void deleteBidRecord(Integer bidRecordNo) {
		dao.delete(bidRecordNo);
	}

	// 用 BidRecordNo 取得一筆資料
	public BidRecordVO getOneBidRecord(Integer bidRecordNo) {
		return dao.findByPrimaryKey(bidRecordNo);
	}

	// 使 BidProductNo 取得 全部資料
	public List<BidRecordVO> getAllBidRecordByBidProductNo(Integer bidProductNo) {
		return dao.findByBidProductNo(bidProductNo);
	}

	// 用 BidProductNo 取得 最高出價
	public BidRecordVO getHighestByBidProductNo(Integer bidProductNo) {
		return dao.findByBidProductNoHighestPrice(bidProductNo);
	}

	// 取得所有 BidRecord
	public List<BidRecordVO> getAll() {
		return dao.getAll();
	}

	// 使用 memNo 取得該會員的所有出價紀錄
	public List<BidRecordVO> getByMemNo(Integer memNo) {
		return dao.findByMemNo(memNo);
	}

}
