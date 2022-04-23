package com.forummsgreport.model;

import java.sql.Timestamp;

public class ForumMsgReportVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer forumMsgReportNo;
	private Integer forumMsgNo;
	private Integer memNo;
	private Integer forumMsgReportType;
	private String forumMsgReportWhy;
	private Timestamp forumMsgReportTime;

	public Integer getForumMsgReportNo() {
		return forumMsgReportNo;
	}

	public void setForumMsgReportNo(Integer forumMsgReportNo) {
		this.forumMsgReportNo = forumMsgReportNo;
	}

	public Integer getForumMsgNo() {
		return forumMsgNo;
	}

	public void setForumMsgNo(Integer forumMsgNo) {
		this.forumMsgNo = forumMsgNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getForumMsgReportType() {
		return forumMsgReportType;
	}

	public void setForumMsgReportType(Integer forumMsgReportType) {
		this.forumMsgReportType = forumMsgReportType;
	}

	public String getForumMsgReportWhy() {
		return forumMsgReportWhy;
	}

	public void setForumMsgReportWhy(String forumMsgReportWhy) {
		this.forumMsgReportWhy = forumMsgReportWhy;
	}

	public Timestamp getForumMsgReportTime() {
		return forumMsgReportTime;
	}

	public void setForumMsgReportTime(Timestamp forumMsgReportTime) {
		this.forumMsgReportTime = forumMsgReportTime;
	}
}
