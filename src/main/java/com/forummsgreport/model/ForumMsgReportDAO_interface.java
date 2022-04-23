package com.forummsgreport.model;

import java.util.*;

public interface ForumMsgReportDAO_interface {

	public void insert(ForumMsgReportVO forumMsgReportVO);

//	修改留言檢舉狀態
	public void update(ForumMsgReportVO forumMsgReportVO);

//	public void delete(Integer forumMsgReportNo);沒有刪除

	public ForumMsgReportVO findByPrimaryKey(Integer forumMsgReportNo);

	public List<ForumMsgReportVO> getAll();

//	顯示自己所有的留言檢舉
	public List<ForumMsgReportVO> findMyForumMsgReport(Integer memNo);

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ForumMsgReportVO> getAll(Map<String, String[]> map);
}
