package com.chatroombanlist.model;

import java.io.Serializable;

import com.member.model.MemService;
import com.member.model.MemVO;

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
	public com.member.model.MemVO getMemVOBymemNo() {
		com.member.model.MemService memSvc = new MemService();
		MemVO memVO = memSvc.getMemVObyMemNo(memNo);
		return memVO;
	}

	public com.member.model.MemVO getMemVOByMemNoBaned() {
		com.member.model.MemService memSvc = new MemService();
		MemVO memVO = memSvc.getMemVObyMemNo(memNo_Baned);
		return memVO;
	}

}

