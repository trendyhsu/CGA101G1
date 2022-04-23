package com.forumpostpic.model;

import java.util.*;

public interface ForumPostPicDAO_interface {
	
	public void insert(ForumPostPicVO forumPostPicVO);

	public void update(ForumPostPicVO forumPostPicVO);

	public void delete(Integer forumPostPicNo);

	public ForumPostPicVO findByPrimaryKey(Integer forumPostPicNo);

	public List<ForumPostPicVO> getAll();
	
	public List<ForumPostPicVO> findOneForumTotalPostPic(Integer forumPostNo);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ForumPostPicVO> getAll(Map<String, String[]> map);
}
