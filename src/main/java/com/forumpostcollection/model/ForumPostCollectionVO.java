package com.forumpostcollection.model;

import java.sql.Timestamp;

public class ForumPostCollectionVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer memNo;
	private Integer forumPostNo;
	private Timestamp forumPostCollectionTime;

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getForumPostNo() {
		return forumPostNo;
	}

	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}

	public Timestamp getForumPostCollectionTime() {
		return forumPostCollectionTime;
	}

	public void setForumPostCollectionTime(Timestamp forumPostCollectionTime) {
		this.forumPostCollectionTime = forumPostCollectionTime;
	}

	// for join forumPostTitle from forumPostNo
	public com.forumpost.model.ForumPostVO getForumPostVO() {
		com.forumpost.model.ForumPostService forumPostSvc = new com.forumpost.model.ForumPostService();
		com.forumpost.model.ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);
		return forumPostVO;
	}
}
