package com.forumpostpic.model;

public class ForumPostPicVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer forumPostPicNo;
	private Integer forumPostNo;
	private byte[] forumPic;

	public Integer getForumPostPicNo() {
		return forumPostPicNo;
	}

	public void setForumPostPicNo(Integer forumPostPicNo) {
		this.forumPostPicNo = forumPostPicNo;
	}

	public Integer getForumPostNo() {
		return forumPostNo;
	}

	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}

	public byte[] getForumPic() {
		return forumPic;
	}

	public void setForumPic(byte[] forumPic) {
		this.forumPic = forumPic;
	}
}
