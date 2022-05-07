package com.forumpost.model;

import java.util.*;

public interface ForumPostDAO_interface {

	public Integer insert(ForumPostVO forumPostVO);

//	管理員新增文章
	public Integer insertAdmin(ForumPostVO forumPostVO);

//	一般會員修改文章
	public void update(ForumPostVO forumPostVO);

//	一般會員刪除文章
	public void updateForumPostState(ForumPostVO forumPostVO);

//	版主修改文章精選
	public void updateMaster(ForumPostVO forumPostVO);

//	管理員修改文章
	public void updateAdmin(ForumPostVO forumPostVO);

//	修改管理員文章

	public void updateAdminPost(ForumPostVO forumPostVO);

//	public void delete(Integer forumPostNo);沒有刪除

	public ForumPostVO findByPrimaryKey(Integer forumPostNo);

	public List<ForumPostVO> getAll();

//	單一討論區所有文章
	public List<ForumPostVO> findByForumNo(Integer forumNo);

//	版主單一討論區所有文章
	public List<ForumPostVO> findByForumNoTopMem(Integer forumNo);

//	會員個人發表文章列表
	public List<ForumPostVO> findByMemNo(Integer memNo);

//	用文章標題&分類收尋
	public List<ForumPostVO> findByPostTypeName(Integer forumtNo, Integer forumPostType, String postCharacter);

//	收尋討論區

//	一般全文章
	public List<ForumPostVO> getAllMemPost();

//  管理員全文章
	public List<ForumPostVO> getAllMasterPost();

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<ForumPostVO> getPowerAll(Map<String, String[]> map);
}
