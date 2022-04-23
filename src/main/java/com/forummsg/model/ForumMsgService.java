package com.forummsg.model;

import java.util.List;

public class ForumMsgService {

	private ForumMsgDAO_interface dao;

	public ForumMsgService() {
		dao = new ForumMsgDAO();
	}

	public ForumMsgVO addForumMsg(Integer memNo, Integer forumPostNo, Integer forumMsgType, String forumMsg) {

		ForumMsgVO forumMsgVO = new ForumMsgVO();

		forumMsgVO.setMemNo(memNo);
		forumMsgVO.setForumPostNo(forumPostNo);
		forumMsgVO.setForumMsgType(forumMsgType);
		forumMsgVO.setForumMsg(forumMsg);

		dao.insert(forumMsgVO);

		return forumMsgVO;
	}

	// 預留給 Struts 2 用的
	public void addForumMsg(ForumMsgVO forumMsgVO) {
		dao.insert(forumMsgVO);
	}

	public ForumMsgVO updateForumMsg(Integer forumMsgNo, String forumMsg) {

		ForumMsgVO forumMsgVO = new ForumMsgVO();

		forumMsgVO.setForumMsgNo(forumMsgNo);
		forumMsgVO.setForumMsg(forumMsg);

		dao.update(forumMsgVO);

		return dao.findByPrimaryKey(forumMsgNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumMsg(ForumMsgVO forumMsgVO) {
		dao.update(forumMsgVO);
	}

	public ForumMsgVO updateMsgType(Integer forumMsgNo, Integer forumMsgType) {

		ForumMsgVO forumMsgVO = new ForumMsgVO();

		forumMsgVO.setForumMsgNo(forumMsgNo);
		forumMsgVO.setForumMsgType(forumMsgType);

		dao.updateForumMsgType(forumMsgVO);

		return dao.findByPrimaryKey(forumMsgNo);
	}

	// 預留給 Struts 2 用的
	public void updateMsgType(ForumMsgVO forumMsgVO) {
		dao.updateForumMsgType(forumMsgVO);
	}

	public ForumMsgVO getOneForumMsg(Integer forumMsgNo) {
		return dao.findByPrimaryKey(forumMsgNo);
	}

	public List<ForumMsgVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumMsgVO> getOnePostAllMsg(Integer forumPostNo) {
		return dao.findForumPostForumMsg(forumPostNo);
	}
	
}
