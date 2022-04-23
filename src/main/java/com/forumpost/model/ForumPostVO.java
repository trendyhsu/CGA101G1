package com.forumpost.model;

import java.sql.Timestamp;

public class ForumPostVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer forumPostNo;
	private Integer forumNo;
	private Integer forumPostType;
	private Integer memNo;
	private Integer forumPostState;
	private String forumPostTitle;
	private String forumPostContent;
	private Timestamp forumPostTime;
	private Integer forumPostFeatured;

	public Integer getForumPostNo() {
		return forumPostNo;
	}

	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}

	public Integer getForumNo() {
		return forumNo;
	}

	public void setForumNo(Integer forumNo) {
		this.forumNo = forumNo;
	}

	public Integer getForumPostType() {
		return forumPostType;
	}

	public void setForumPostType(Integer forumPostType) {
		this.forumPostType = forumPostType;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getForumPostState() {
		return forumPostState;
	}

	public void setForumPostState(Integer forumPostState) {
		this.forumPostState = forumPostState;
	}

	public String getForumPostTitle() {
		return forumPostTitle;
	}

	public void setForumPostTitle(String forumPostTitle) {
		this.forumPostTitle = forumPostTitle;
	}

	public String getForumPostContent() {
		return forumPostContent;
	}

	public void setForumPostContent(String forumPostContent) {
		this.forumPostContent = forumPostContent;
	}

	public Timestamp getForumPostTime() {
		return forumPostTime;
	}

	public void setForumPostTime(Timestamp forumPostTime) {
		this.forumPostTime = forumPostTime;
	}

	public Integer getForumPostFeatured() {
		return forumPostFeatured;
	}

	public void setForumPostFeatured(Integer forumPostFeatured) {
		this.forumPostFeatured = forumPostFeatured;
	}
}
