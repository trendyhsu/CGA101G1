package com.forumpostcollection.model;

import java.util.*;

public interface ForumPostCollectionDAO_interface {

	public void insert(ForumPostCollectionVO forumPostCollectionVO);

//	沒有修改
	public void delete(Integer memNo, Integer forumPostNo);

	public ForumPostCollectionVO findByPrimaryKey(Integer memNo, Integer forumPostNo);

//	個人收藏文章列表
	public List<ForumPostCollectionVO> getAll(Integer memNo);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ForumPostCollectionVO> getAll(Map<String, String[]> map);
}
