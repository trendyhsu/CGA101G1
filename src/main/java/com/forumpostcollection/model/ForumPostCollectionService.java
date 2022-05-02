package com.forumpostcollection.model;

import java.util.List;

public class ForumPostCollectionService {

	private ForumPostCollectionDAO_interface dao;

	public ForumPostCollectionService() {
		dao = new ForumPostCollectionDAO();
	}

	public ForumPostCollectionVO addForumPostCollection(Integer memNo, Integer forumPostNo) {

		ForumPostCollectionVO forumPostCollectionVO = new ForumPostCollectionVO();

		forumPostCollectionVO.setMemNo(memNo);
		forumPostCollectionVO.setForumPostNo(forumPostNo);

		dao.insert(forumPostCollectionVO);

		return forumPostCollectionVO;
	}

	// 預留給 Struts 2 用的
	public void addForumPostCollection(ForumPostCollectionVO forumPostCollectionVO) {
		dao.insert(forumPostCollectionVO);
	}

	public void deleteForumPostCollection(Integer memNo, Integer forumPostNo) {
		dao.delete(memNo, forumPostNo);

	}
	
	public ForumPostCollectionVO getOnePostCollection(Integer memNo, Integer forumPostNo) {
		
		return dao.findByPrimaryKey(memNo, forumPostNo);
	}

	public List<ForumPostCollectionVO> getOwenrAllPostCollection(Integer memNo) {
		return dao.getAll(memNo);
	}
}
