package com.forummsg.model;

import java.util.*;

public interface ForumMsgDAO_interface {

	public void insert(ForumMsgVO forumMsgVO);

// 修改討論區留言內容
	public void update(ForumMsgVO forumMsgVO);
	
// 修改討論區留言狀態
	public void updateForumMsgType(ForumMsgVO forumMsgVO);

//	public void delete(Integer forumMsgNo);沒有刪除

	public ForumMsgVO findByPrimaryKey(Integer forumMsgNo);

	public List<ForumMsgVO> getAll();
	
// 顯示單一文章所有留言
	public List<ForumMsgVO> findForumPostForumMsg(Integer forumPostNo);

	// 萬用複合查詢(傳入參數型態Map)(回傳 List))
	// public List<ForumMsgVO> getAll(Map<String, String[]> map);
}
