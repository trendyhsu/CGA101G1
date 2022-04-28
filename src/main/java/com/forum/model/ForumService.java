package com.forum.model;

import java.util.List;

public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}

	public ForumVO addForum(String forumName, Integer forumType, Integer memNo) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);
		forumVO.setMemNo(memNo);

		dao.insert(forumVO);

		return forumVO;
	}

	// 預留給 Struts 2 用的
	public void addForum(ForumVO forumVO) {
		dao.insert(forumVO);
	}

	public ForumVO addForumNoMem(String forumName, Integer forumType) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);

		dao.insertNoMem(forumVO);

		return forumVO;

	}

	// 預留給 Struts 2 用的
	public void addForumNoMem(ForumVO forumVO) {
		dao.insertNoMem(forumVO);
	}

	public ForumVO addForumImg(Integer forumNo, byte[] forumImg) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumNo(forumNo);
		forumVO.setForumImg(forumImg);

		dao.insertForumImg(forumVO);

		return forumVO;
	}

	// 預留給 Struts 2 用的
	public void addForumImg(ForumVO forumVO) {
		dao.insertForumImg(forumVO);
	}

	public ForumVO updateForum(Integer forumNo, String forumName, Integer forumType,
			Integer memNo/* ,byte[] forumImg */) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumNo(forumNo);
		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);
		forumVO.setMemNo(memNo);
//		forumVO.setForumImg(forumImg);

		dao.update(forumVO);

		return dao.findByPrimaryKey(forumNo);
	}

	// 預留給 Struts 2 用的
	public void updateForum(ForumVO forumVO) {
		dao.update(forumVO);
	}

	public ForumVO updateForumNoMem(Integer forumNo, String forumName, Integer forumType/* ,byte[] forumImg */) {

		ForumVO forumVO = new ForumVO();

		forumVO.setForumNo(forumNo);
		forumVO.setForumName(forumName);
		forumVO.setForumType(forumType);
//		forumVO.setForumImg(forumImg);

		dao.updateNoMem(forumVO);

		return dao.findByPrimaryKey(forumNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumNoMem(ForumVO forumVO) {
		dao.updateNoMem(forumVO);
	}

	public ForumVO getOneForum(Integer forumNo) {
		return dao.findByPrimaryKey(forumNo);
	}

	public ForumVO getOneForumByName(String forumCharacter) {
		return dao.findByOnlyName(forumCharacter);
	}

	public List<ForumVO> getAll() {
		return dao.getAll();
	}

	public List<ForumVO> getFindForumName(String forumCharacter) {
		return dao.findByForumName(forumCharacter);
	}

}
