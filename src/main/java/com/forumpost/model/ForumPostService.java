package com.forumpost.model;

import java.util.List;
import java.util.Map;

public class ForumPostService {

	private ForumPostDAO_interface dao;

	public ForumPostService() {
		dao = new ForumPostDAO();
	}

	public Integer addForumPost(Integer forumNo, Integer forumPostType, Integer memNo, Integer forumPostState,
			String forumPostTitle, String forumPostContent, Integer forumPostFeatured) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumNo(forumNo);
		forumPostVO.setForumPostType(forumPostType);
		forumPostVO.setMemNo(memNo);
		forumPostVO.setForumPostState(forumPostState);
		forumPostVO.setForumPostTitle(forumPostTitle);
		forumPostVO.setForumPostContent(forumPostContent);
		forumPostVO.setForumPostFeatured(forumPostFeatured);

		Integer nextForumPostNo = dao.insert(forumPostVO);

		return nextForumPostNo;
	}

	// 預留給 Struts 2 用的
	public Integer addForumPost(ForumPostVO forumPostVO) {
		return dao.insert(forumPostVO);
	}

	public Integer addForumMasterPost(Integer forumNo, Integer forumPostType, Integer managerNo, Integer forumPostState,
			String forumPostTitle, String forumPostContent, Integer forumPostFeatured) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumNo(forumNo);
		forumPostVO.setForumPostType(forumPostType);
		forumPostVO.setManagerNo(managerNo);
		forumPostVO.setForumPostState(forumPostState);
		forumPostVO.setForumPostTitle(forumPostTitle);
		forumPostVO.setForumPostContent(forumPostContent);
		forumPostVO.setForumPostFeatured(forumPostFeatured);

		Integer nextForumPostNo = dao.insertAdmin(forumPostVO);

		return nextForumPostNo;
	}

	// 預留給 Struts 2 用的
	public Integer addForumMasterPost(ForumPostVO forumPostVO) {
		return dao.insertAdmin(forumPostVO);
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

	public ForumPostVO updateAdminPostEdit(Integer forumPostNo, Integer forumPostState, String forumPostTitle,
			String forumPostContent) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setForumPostNo(forumPostNo);
		forumPostVO.setForumPostState(forumPostState);
		forumPostVO.setForumPostTitle(forumPostTitle);
		forumPostVO.setForumPostContent(forumPostContent);

		dao.updateAdminPost(forumPostVO);

		return dao.findByPrimaryKey(forumPostNo);
	}

	// 預留給 Struts 2 用的
	public void updateAdminPostEdit(ForumPostVO forumPostVO) {
		dao.updateAdminPost(forumPostVO);
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

	public List<ForumPostVO> findTopMemAllPost(Integer forumNo) {

		return dao.findByForumNoTopMem(forumNo);
	}

	public List<ForumPostVO> findMyPost(Integer memNo) {
		return dao.findByMemNo(memNo);
	}

	public List<ForumPostVO> findPostTypeName(Integer forumtNo, Integer forumPostType, String postCharacter) {
		return dao.findByPostTypeName(forumtNo, forumPostType, postCharacter);
	}

	public List<ForumPostVO> getAllMemPost() {
		return dao.getAllMemPost();
	}

	public List<ForumPostVO> getAllMasterPost() {
		return dao.getAllMasterPost();
	}

	public List<ForumPostVO> getAllPowerSearch(Map<String, String[]> map) {
		return dao.getPowerAll(map);
	}

}
