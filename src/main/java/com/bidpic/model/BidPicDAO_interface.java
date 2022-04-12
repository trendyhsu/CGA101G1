package com.bidpic.model;

import java.util.List;

import com.bidpic.model.BidPicVO;
import com.connection.model.ConnectionDAO;

public interface BidPicDAO_interface extends ConnectionDAO{
	// BidPicDAO_interface 定義介面
	public void insert(BidPicVO bidPicVO);
	public void update(BidPicVO bidPicVO);
	public void delete(Integer bidProdPicNo);

	public List<BidPicVO> getAll();
	
	public BidPicVO findByPrimaryKey(Integer bidProdPicNo);
	// 使用 bidproductno 取得所有 bidproductno 的照片
	public List<BidPicVO> findByBidProductNo(Integer bidProductNo);
	// 使用 bidproductno 取得封面(第一張) bidproductno 的照片
	public BidPicVO findFirstPicByBidProductNo(Integer bidProductNo);
	
}
