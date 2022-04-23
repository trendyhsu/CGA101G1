package com.forumpostreport.model;

import java.util.*;


public interface ForumPostReportDAO_interface {
	
	public void insert(ForumPostReportVO forumPostReportVO);

//	修改留言檢舉狀態
	public void update(ForumPostReportVO forumPostReportVO);

//	public void delete(Integer forumPostReportNo); 沒有刪除

	public ForumPostReportVO findByPrimaryKey(Integer forumPostReportNo);

	public List<ForumPostReportVO> getAll();
	
//	顯示自己所有的文章檢舉
	public List<ForumPostReportVO> findMyForumPostReport(Integer memNo);
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ForumPostReportVO> getAll(Map<String, String[]> map);
}
