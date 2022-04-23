package com.forumpostreport.model;

import java.sql.Timestamp;

public class ForumPostReportVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer forumPostReportNo;
	private Integer forumPostNo;
	private Integer memNo;
	private Integer forumPostReportType;
	private String forumPostReportWhy;
	private Timestamp forumPostReportTime;

	public Integer getForumPostReportNo() {
		return forumPostReportNo;
	}

	public void setForumPostReportNo(Integer forumPostReportNo) {
		this.forumPostReportNo = forumPostReportNo;
	}

	public Integer getForumPostNo() {
		return forumPostNo;
	}

	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getForumPostReportType() {
		return forumPostReportType;
	}

	public void setForumPostReportType(Integer forumPostReportType) {
		this.forumPostReportType = forumPostReportType;
	}

	public String getForumPostReportWhy() {
		return forumPostReportWhy;
	}

	public void setForumPostReportWhy(String forumPostReportWhy) {
		this.forumPostReportWhy = forumPostReportWhy;
	}

	public Timestamp getForumPostReportTime() {
		return forumPostReportTime;
	}

	public void setForumPostReportTime(Timestamp forumPostReportTime) {
		this.forumPostReportTime = forumPostReportTime;
	}
}
