package com.forumpostreport.model;

import java.util.List;

public class ForumPostReportService {

	private ForumPostReportDAO_interface dao;

	public ForumPostReportService() {
		dao = new ForumPostReportDAO();
	}

	public ForumPostReportVO addForumPostReport(Integer forumPostNo, Integer memNo, Integer forumPostReportType,
			String forumPostReportWhy) {

		ForumPostReportVO forumPostReportVO = new ForumPostReportVO();

		forumPostReportVO.setForumPostNo(forumPostNo);
		forumPostReportVO.setMemNo(memNo);
		forumPostReportVO.setForumPostReportType(forumPostReportType);
		forumPostReportVO.setForumPostReportWhy(forumPostReportWhy);

		dao.insert(forumPostReportVO);

		return forumPostReportVO;
	}

	// 預留給 Struts 2 用的
	public void addForumPostReport(ForumPostReportVO forumPostReportVO) {
		dao.insert(forumPostReportVO);
	}


	public ForumPostReportVO updateForumPostReport(Integer forumPostReportNo, Integer forumPostReportType) {

		ForumPostReportVO forumPostReportVO = new ForumPostReportVO();

		forumPostReportVO.setForumPostReportNo(forumPostReportNo);
		forumPostReportVO.setForumPostReportType(forumPostReportType);

		dao.update(forumPostReportVO);

		return dao.findByPrimaryKey(forumPostReportNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumPostReport(ForumPostReportVO forumPostReportVO) {
		dao.update(forumPostReportVO);
	}

	public ForumPostReportVO getOneForumPostReport(Integer forumPostReportNo) {

		return dao.findByPrimaryKey(forumPostReportNo);
	}

	public List<ForumPostReportVO> getAll() {
		return dao.getAll();
	}

	public List<ForumPostReportVO> getMyForumPostReport(Integer memNo) {
		return dao.findMyForumPostReport(memNo);
	}
}
