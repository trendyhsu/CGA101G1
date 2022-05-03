package com.bidproduct.model;

import java.util.List;

import com.bidproduct.model.BidProductVO;
import com.connection.model.ConnectionDAO;

public interface BidProductDAO_interface extends ConnectionDAO {
	// BidProductDAO_interface 定義介面
	public Integer insert(BidProductVO bidProductVO);

	public void update(BidProductVO bidProductVO);

	public void delete(Integer bidProductNo);

	public List<BidProductVO> getAll();

	public BidProductVO findByPrimaryKey(Integer bidProductNo);

	// 使用 bidApplyListNo 查詢商品
	public BidProductVO findByBidApplyListNo(Integer bidApplyListNo);

	// 使用 buyerNo 查詢所有 buyerNo 得標商品
	public List<BidProductVO> findByBuyerNo(Integer buyerNo);

	// 使用 sellerNo 查詢所有 sellerNo 得標商品
	public List<BidProductVO> findBySellerNo(Integer sellerNo);

	// 使用 bidName 查詢所有 符合 bidName 的商品
	public List<BidProductVO> findByBidName(String bidName);

	// 查詢競標商品 BidState 等於 0 (競標中) 而且 截標時間小於目前時間 ( 為了改成流標 )
	public List<BidProductVO> findByBidStateAndSoldTime();

	// 查詢競標商品 BidState 等於 1 (結帳結束進入訂單處理中)
	public List<BidProductVO> findByBidState();

	// 使用 bidProductNo 更新競標狀態
	public void updateBidState(BidProductVO bidProductVO);

	// 使用 bidProductNo 更新競標狀態(有出價者 需要更新 買家 最高出價 以及 競標狀態)
	public void updateBidStateHaveBuyer(BidProductVO bidProductVO);

	// 更改orderState用來更新取回以及重新上架的狀態
	public void updateOrderStateGetbackAndRelist(BidProductVO bidProductVO);

	// 更改收件資訊與商品狀態
	public void updateReceiverAndOrderState(BidProductVO bidProductVO);

	// 查詢已截標 ( BidState = 1 ) 30分鐘後沒有付款 將 BidState 改為 棄標
	public List<BidProductVO> findByBidStateAndOrderState();

	// 後臺更新競標資訊
	public void updateByBackend(BidProductVO bidProductVO);

}
