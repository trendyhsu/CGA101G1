package com.forummsgreport.model;

import java.util.List;

public class ForumMsgReportService {

	private ForumMsgReportDAO_interface dao;

	public ForumMsgReportService() {
		dao = new ForumMsgReportDAO();
	}

	public ForumMsgReportVO addForumMsgReport(Integer forumMsgNo, Integer memNo, Integer forumMsgReportType,
			String forumMsgReportWhy) {

		ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();

		forumMsgReportVO.setForumMsgNo(forumMsgNo);
		forumMsgReportVO.setMemNo(memNo);
		forumMsgReportVO.setForumMsgReportType(forumMsgReportType);
		forumMsgReportVO.setForumMsgReportWhy(forumMsgReportWhy);

		dao.insert(forumMsgReportVO);

		return forumMsgReportVO;
	}

	// 預留給 Struts 2 用的
	public void addForumMsgReport(ForumMsgReportVO forumMsgReportVO) {
		dao.insert(forumMsgReportVO);
	}

	public ForumMsgReportVO updateForumMsgReport(Integer forumMsgReportNo, Integer forumMsgReportType) {

		ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();

		forumMsgReportVO.setForumMsgReportNo(forumMsgReportNo);
		forumMsgReportVO.setForumMsgReportType(forumMsgReportType);

		dao.update(forumMsgReportVO);

		return dao.findByPrimaryKey(forumMsgReportNo);
	}

	// 預留給 Struts 2 用的
	public void updateForumMsgReport(ForumMsgReportVO forumMsgReportVO) {
		dao.update(forumMsgReportVO);
	}

	public ForumMsgReportVO getOneForumMsgReport(Integer forumMsgReportNo) {
		return dao.findByPrimaryKey(forumMsgReportNo);
	}

	public List<ForumMsgReportVO> getAll() {
		return dao.getAll();
	}

	public List<ForumMsgReportVO> getMyForumMsgReport(Integer memNo) {
		return dao.findMyForumMsgReport(memNo);
	}
}
