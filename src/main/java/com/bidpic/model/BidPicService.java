package com.bidpic.model;

import java.util.List;

import com.bidpic.model.BidPicDAO;
import com.bidpic.model.BidPicDAO_interface;
import com.bidpic.model.BidPicVO;

public class BidPicService {

	private BidPicDAO_interface dao;

	public BidPicService() {
		dao = new BidPicJDBCDAO();
	}
	
	// 新增圖片
	public BidPicVO addBidPic(Integer bidProductNo, byte[] bidProdPicContent) {
		BidPicVO bidPicVO = new BidPicVO();
		
		bidPicVO.setBidProductNo(bidProductNo);
		bidPicVO.setBidProdPicContent(bidProdPicContent);
		dao.insert(bidPicVO);
		
		return bidPicVO;
		
	}
	// 取得所有圖片
	public List<BidPicVO> getAll() {
		return dao.getAll();
	}
	
	// 預留給 Struts 2 或 Spring MVC 用
	public void addBidPic(BidPicVO bidPicVO) {
		dao.insert(bidPicVO);
	}
	
	
	public BidPicVO updateBidPic(Integer bidProdPicNo, Integer bidProductNo, byte[] bidProdPicContent) {
		BidPicVO bidPicVO = new BidPicVO();
		
		bidPicVO.setBidProdPicNo(bidProdPicNo);
		bidPicVO.setBidProductNo(bidProductNo);
		bidPicVO.setBidProdPicContent(bidProdPicContent);
		dao.update(bidPicVO);
		
		return dao.findByPrimaryKey(bidProdPicNo);
	}
	
	// 預留給 Struts 2 用的
	public void updateBidPic(BidPicVO bidPicVO) {
		dao.update(bidPicVO);
	}
	
	public void deleteBidPic(Integer bidProdPicNo) {
		dao.delete(bidProdPicNo);
	}
	
	
	// 用 BidProductPicNo 取得一張圖片
	public BidPicVO getOneBidPic(Integer bidProductPicNo) {
		return dao.findByPrimaryKey(bidProductPicNo);
	}
	
	// 用 BidProductNo 取得所有圖片
	public List<BidPicVO> getAllBidPicByBidProductNo(Integer bidProductNo) {
		return dao.findByBidProductNo(bidProductNo);
	}
	
	// 用BidProductNo 取得封面圖片
	public BidPicVO getMainBidPicByBidProductNo(Integer bidProductNo) {
		return dao.findFirstPicByBidProductNo(bidProductNo);
	}

}
