package com.bidproduct.model;

import java.util.List;

import com.bidproduct.model.BidProductVO;
import com.connection.model.ConnectionDAO;

public interface BidProductDAO_interface extends ConnectionDAO {
	// BidProductDAO_interface 定義介面
	public void insert(BidProductVO bidProductVO);
	public void update(BidProductVO bidProductVO);
	public void delete(Integer bidProductNo);

	public List<BidProductVO> getAll();
	
	public BidProductVO findByPrimaryKey(Integer bidProductNo);
	
	// 使用 buyerNo 查詢所有 buyerNo 得標商品
	public List<BidProductVO> findByBuyerNo(Integer buyerNo);
	
	// 使用 bidName 查詢所有 符合 bidName 的商品
	public List<BidProductVO> findByBidName(String bidName);
	
}
