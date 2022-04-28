package com.chatroomrecord.model;

import java.util.List;



public class ChatRoomRecordService {
private ChatRoomRecordDAO_interface dao;
	
	public ChatRoomRecordService() {
		// TODO Auto-generated constructor stub
		dao = new ChatRoomRecordJDBCDAO ();
	}

	public void addChatRecord(Integer memNo, Integer forumNo, String chatRoomContent) {

		ChatRoomRecordVO chatRoomRecordVO = new ChatRoomRecordVO();
		chatRoomRecordVO.setMemNo(memNo);
		chatRoomRecordVO.setForumNo(forumNo);
		chatRoomRecordVO.setChatRoomContent(chatRoomContent);
		dao.insert(chatRoomRecordVO);
	
	}
	//修改，基本上聊天紀錄不能改，會有竄改嫌疑
	public ChatRoomRecordVO updateChatRecord(Integer chatRoomRecordNo, String chatRoomContent) {
		return dao.findByPrimaryKey(chatRoomRecordNo);
	}
	
	public void deleteChatRecord(Integer chatRoomRecordNo) {
		dao.delete(chatRoomRecordNo);
	}
	//取得單一聊天紀錄
	public ChatRoomRecordVO getOneRecord(Integer chatRoomRecordNo) {
		return dao.findByPrimaryKey(chatRoomRecordNo);
	}
	//取得所有會員，聊天紀錄
	public List<ChatRoomRecordVO> getAll() {
		return dao.getAll();

	}
	//取得單一會員所有聊天紀錄
	public List<ChatRoomRecordVO> getOneMemRecord(Integer memNo) {
		return dao.findByMemNoRecord(memNo);
	}
}