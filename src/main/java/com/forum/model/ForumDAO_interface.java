package com.forum.model;

import java.util.*;

public interface ForumDAO_interface {

	public void insert(ForumVO forumVO);
	
	public void insertNoMem(ForumVO forumVO);

	public void insertForumImg(ForumVO forumVO);

	public void update(ForumVO forumVO);

	public void updateNoMem(ForumVO forumVO);

//	public void delete(Integer forumNo); 沒有刪除

	public ForumVO findByPrimaryKey(Integer forumNo);
	
	public ForumVO findByOnlyName(String forumCharacter);

	public List<ForumVO> getAll();

//	用文章標題收尋
	public List<ForumVO> findByForumName(String forumCharacter);

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ForumVO> getAll(Map<String, String[]> map);
}
