package com.forumpost.model;

import java.util.List;

public class ForumPostService {

	private ForumPostDAO_interface dao;

	public ForumPostService() {
		dao = new ForumPostDAO();
	}

	public ForumPostVO addForumPost(Integer forumNo, Integer forumPostType, Integer memNo, Integer forumPostState,
			String forumPostTitle, String forumPostContent, Integer forumPostFeatured) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumNo(forumNo);
		forumPostVO.setForumPostType(forumPostType);
		forumPostVO.setMemNo(memNo);
		forumPostVO.setForumPostState(forumPostState);
		forumPostVO.setForumPostTitle(forumPostTitle);
		forumPostVO.setForumPostContent(forumPostContent);
		forumPostVO.setForumPostFeatured(forumPostFeatured);

		dao.insert(forumPostVO);

		return forumPostVO;
	}

	// 預留給 Struts 2 用的
	public void addForumPost(ForumPostVO forumPostVO) {
		dao.insert(forumPostVO);
	}

	public ForumPostVO updateForumPost(Integer forumPostNo, Integer forumPostType, String forumPostTitle,
			String forumPostContent) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumPostNo(forumPostNo);
		forumPostVO.setForumPostType(forumPostType);
		forumPostVO.setForumPostTitle(forumPostTitle);
		forumPostVO.setForumPostContent(forumPostContent);

		dao.update(forumPostVO);

		return dao.findByPrimaryKey(forumPostNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumPost(ForumPostVO forumPostVO) {
		dao.update(forumPostVO);
	}

	public ForumPostVO updateMemPostState(Integer forumPostNo, Integer forumPostState) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumPostNo(forumPostNo);
		forumPostVO.setForumPostState(forumPostState);

		dao.updateForumPostState(forumPostVO);

		return dao.findByPrimaryKey(forumPostNo);
	}

	// 預留給 Struts 2 用的
	public void updateMemPostState(ForumPostVO forumPostVO) {
		dao.updateForumPostState(forumPostVO);
	}

	public ForumPostVO updateMasterPostFeatured(Integer forumPostNo, Integer forumPostFeatured) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumPostNo(forumPostNo);
		forumPostVO.setForumPostFeatured(forumPostFeatured);

		dao.updateMaster(forumPostVO);

		return dao.findByPrimaryKey(forumPostNo);
	}

	// 預留給 Struts 2 用的
	public void updateMasterPostFeatured(ForumPostVO forumPostVO) {
		dao.updateMaster(forumPostVO);
	}

	public ForumPostVO updateAdminPost(Integer forumPostNo, Integer forumPostType, Integer forumPostState) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumPostNo(forumPostNo);
		forumPostVO.setForumPostType(forumPostType);
		forumPostVO.setForumPostState(forumPostState);

		dao.updateAdmin(forumPostVO);

		return dao.findByPrimaryKey(forumPostNo);
	}

	// 預留給 Struts 2 用的
	public void updateAdminPost(ForumPostVO forumPostVO) {
		dao.updateAdmin(forumPostVO);
	}

	public ForumPostVO getOneForumPost(Integer forumPostNo) {
		return dao.findByPrimaryKey(forumPostNo);
	}

	public List<ForumPostVO> getAll() {
		return dao.getAll();
	}

	public List<ForumPostVO> findOneForumAllPost(Integer forumNo) {

		return dao.findByForumNo(forumNo);
	}

	public List<ForumPostVO> findMyPost(Integer memNo) {
		return dao.findByMemNo(memNo);
	}

	public List<ForumPostVO> findPostTypeName(Integer forumtNo, Integer forumPostType, String postCharacter) {
		return dao.findByPostTypeName(forumtNo, forumPostType, postCharacter);
	}

}
