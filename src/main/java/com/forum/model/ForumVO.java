package com.forum.model;

public class ForumVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer forumNo;
	private String forumName;
	private Integer forumType;
	private Integer memNo;
	private byte[] forumImg;

	public Integer getForumNo() {
		return forumNo;
	}

	public void setForumNo(Integer forumNo) {
		this.forumNo = forumNo;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public Integer getForumType() {
		return forumType;
	}

	public void setForumType(Integer forumType) {
		this.forumType = forumType;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public byte[] getForumImg() {
		return forumImg;
	}

	public void setForumImg(byte[] forumImg) {
		this.forumImg = forumImg;
	}
	// for join memName from memNo
		public com.member.model.MemVO getMemVO() {
			com.member.model.MemJDBCDAO memDAO = new com.member.model.MemJDBCDAO();
			com.member.model.MemVO memVO = memDAO.getOne(memNo);
			return memVO;
		}
}
