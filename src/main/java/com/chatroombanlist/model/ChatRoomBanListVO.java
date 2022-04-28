package com.chatroombanlist.model;

import java.io.Serializable;

//import javax.websocket.Decoder.Text;


public class ChatRoomBanListVO implements Serializable{
	private Integer banListNo;
	private Integer memNo;
	private Integer memNo_Baned;

	
	public Integer getBanListNo() {
		return banListNo;
	}
	public void setBanListNo(Integer banListNo) {
		this.banListNo = banListNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getMemNo_Baned() {
		return memNo_Baned;
	}
	public void setMemNo_Baned(Integer memNo_Baned) {
		this.memNo_Baned = memNo_Baned;
	}
}

