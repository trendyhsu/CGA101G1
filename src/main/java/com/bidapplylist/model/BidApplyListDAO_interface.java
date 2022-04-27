package com.bidapplylist.model;

import java.util.List;

import com.connection.model.ConnectionDAO;

public interface BidApplyListDAO_interface extends ConnectionDAO {
	// BidApplyListDAO_interface 定義介面
	public void insert(BidApplyListVO bidApplyListVO);

	public void update(BidApplyListVO bidApplyListVO);

	public void delete(Integer bidApplyListNo);

	public List<BidApplyListVO> getAll();

	public BidApplyListVO findByPrimaryKey(Integer bidApplyListNo);

	// 使用 memNo 取得該會員的所有申請單
	public List<BidApplyListVO> findByMemNo(Integer memNo);

	// 使用 BidApplyListNo 更新 ApplyState 用於退貨 以及上架 更新狀態
	public void updateApplyState(BidApplyListVO bidApplyListVO);
	
	// 使用 gamePlatformNo 取得該遊戲平台的所有申請單
	public List<BidApplyListVO> findByGamePlatformNo(Integer gamePlatformNo);
	
	// 使用 gameTypeNo 取得該遊戲類別的所有申請單
	public List<BidApplyListVO> findByGameTypeNo(Integer gameTypeNo);
	
	// 使用 gameCompanyNo 取得該遊戲公司的所有申請單
	public List<BidApplyListVO> findByGameCompanyNo(Integer gameCompanyNo);
}
