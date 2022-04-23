package com.forummsg.model;
import java.sql.Timestamp;

public class ForumMsgVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer forumMsgNo;
	private Integer memNo;
	private Integer forumPostNo;
	private Integer forumMsgType;
	private String forumMsg;
	private Timestamp forumMsgTime;
	
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
	public Integer getForumPostNo() {
		return forumPostNo;
	}
	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}
	public Integer getForumMsgType() {
		return forumMsgType;
	}
	public void setForumMsgType(Integer forumMsgType) {
		this.forumMsgType = forumMsgType;
	}
	public String getForumMsg() {
		return forumMsg;
	}
	public void setForumMsg(String forumMsg) {
		this.forumMsg = forumMsg;
	}
	public Timestamp getForumMsgTime() {
		return forumMsgTime;
	}
	public void setForumMsgTime(Timestamp forumMsgTime) {
		this.forumMsgTime = forumMsgTime;
	}
}
