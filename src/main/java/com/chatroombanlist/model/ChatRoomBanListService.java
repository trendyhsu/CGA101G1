package com.chatroombanlist.model;

import java.util.List;

public class ChatRoomBanListService {
	private ChatRoomBanListDAO_interface dao;

	public ChatRoomBanListService() {
		// TODO Auto-generated constructor stub
		dao = new ChatRoomBanListJDBCDAO();
	}
	//新增
	public void addBanList(Integer memNo, Integer memNo_Baned) {

		ChatRoomBanListVO chatRoomBanListVO = new ChatRoomBanListVO();
		chatRoomBanListVO.setMemNo(memNo);
		chatRoomBanListVO.setMemNo_Baned(memNo_Baned);
		dao.insert(chatRoomBanListVO);
	}
	//修改，應該用不到
	public List<ChatRoomBanListVO> updateBanList(Integer memNo) {
		return dao.findByOneMemBanList(memNo);
	}
	//刪除，會員單一忽略對象
	public void deleteOneMemBanList(Integer memNo,Integer memNo_Baned) {
		dao.delete(memNo,memNo_Baned);
	}
	//查詢，單一會員多個忽略
	public List<ChatRoomBanListVO> getOneMemBanList(Integer memNo) {
		return dao.findByOneMemBanList(memNo);
	}
	//查詢，all
	public List<ChatRoomBanListVO> getAll() {
		return dao.getAll();

	}

}