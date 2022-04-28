package com.chatroomrecord.model;

import java.io.Serializable;
import java.sql.Timestamp;

//import javax.websocket.Decoder.Text;


public class ChatRoomRecordVO implements Serializable{
	private Integer chatRoomRecordNo;
	private Integer memNo;
	private Integer forumNo;
	private String chatRoomContent;
	private Timestamp chatTime;
	
	public Integer getChatRoomRecordNo() {
		return chatRoomRecordNo;
	}
	public void setChatRoomRecordNo(Integer chatRoomRecordNo) {
		this.chatRoomRecordNo = chatRoomRecordNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getForumNo() {
		return forumNo;
	}
	public void setForumNo(Integer forumNo) {
		this.forumNo = forumNo;
	}
	
	public String getChatRoomContent() {
		return chatRoomContent;
	}
	public void setChatRoomContent(String chatRoomContent) {
		this.chatRoomContent = chatRoomContent;
	}
	public Timestamp getChatTime() {
		return chatTime;
	}
	public void setChatTime(Timestamp chatTime) {
		this.chatTime = chatTime;
	}
}

