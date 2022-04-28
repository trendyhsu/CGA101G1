package com.chatroombanlist.model;

import java.util.List;

public interface ChatRoomBanListDAO_interface {
	public void insert(ChatRoomBanListVO chatRoomRecordBanListVO);
    public void update(ChatRoomBanListVO chatRoomRecordBanListVO);
    public void delete(Integer memNo,Integer memNo_Baned);
    public List<ChatRoomBanListVO> findByOneMemBanList(Integer memNO);
    public List<ChatRoomBanListVO> getAll();
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
