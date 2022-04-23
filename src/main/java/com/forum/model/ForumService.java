package com.forum.model;

import java.util.List;

public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}

	public ForumVO addForum(String forumName, Integer forumType, Integer memNo, byte[] forumImg) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);
		forumVO.setMemNo(memNo);
		forumVO.setForumImg(forumImg);
	
		dao.insert(forumVO);

		return forumVO;
	}

	// 預留給 Struts 2 用的
	public void addForum(ForumVO forumVO) {
		dao.insert(forumVO);
	}

	public ForumVO updateForum(Integer forumNo, String forumName,Integer forumType, Integer memNo,byte[] forumImg) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumNo(forumNo);
		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);
		forumVO.setMemNo(memNo);
		forumVO.setForumImg(forumImg);

		dao.update(forumVO);

		return dao.findByPrimaryKey(forumNo);
	}

	// 預留給 Struts 2 用的
	public void updateForum(ForumVO forumVO) {
		dao.update(forumVO);
	}

	public ForumVO getOneForum(Integer forumNo) {
		return dao.findByPrimaryKey(forumNo);
	}

	public List<ForumVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumVO> getFindForumName(String forumCharacter){
		return dao.findByForumName(forumCharacter);
	}
	
}
