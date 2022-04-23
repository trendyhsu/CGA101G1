package com.forumpostpic.model;

import java.util.List;

public class ForumPostPicService {

	private ForumPostPicDAO_interface dao;

	public ForumPostPicService() {
		dao = new ForumPostPicDAO();
	}

	public ForumPostPicVO addForumPostPic(Integer forumPostNo, byte[] forumPic) {

		ForumPostPicVO forumPostPicVO = new ForumPostPicVO();

		forumPostPicVO.setForumPostNo(forumPostNo);
		forumPostPicVO.setForumPic(forumPic);

		dao.insert(forumPostPicVO);

		return forumPostPicVO;
	}

	// 預留給 Struts 2 用的
	public void addForumPostPic(ForumPostPicVO forumPostPicVO) {
		dao.insert(forumPostPicVO);
	}

	public ForumPostPicVO updateForumPostPic(Integer forumPostPicNo, byte[] forumPic) {

		ForumPostPicVO forumPostPicVO = new ForumPostPicVO();

//		private Integer forumPostPicNo;
//		private Integer forumPostNo;
//		private byte[] forumPic;

		forumPostPicVO.setForumPostPicNo(forumPostPicNo);
		forumPostPicVO.setForumPic(forumPic);

		dao.update(forumPostPicVO);

		return dao.findByPrimaryKey(forumPostPicNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumPostPic(ForumPostPicVO forumPostPicVO) {
		dao.update(forumPostPicVO);
	}

	public void deleteForumPostPic(Integer forumPostPicNo) {
		dao.delete(forumPostPicNo);
	}

	public ForumPostPicVO getOneForumPostPic(Integer forumPostPicNo) {
		return dao.findByPrimaryKey(forumPostPicNo);
	}

	public List<ForumPostPicVO> getAll() {
		return dao.getAll();
	}

	public List<ForumPostPicVO> getOneForumTotalPostPic(Integer forumPostNo) {
		return dao.findOneForumTotalPostPic(forumPostNo);
	}
}
