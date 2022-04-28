package com.chatroomrecord.model;

import java.util.List;

public interface ChatRoomRecordDAO_interface {
	public void insert(ChatRoomRecordVO chatRoomRecordVO);
    public void update(ChatRoomRecordVO chatRoomRecordVO);
    public void delete(Integer chatRoomRecordNo);
    public ChatRoomRecordVO findByPrimaryKey(Integer chatRoomrRecordNo);
    public List<ChatRoomRecordVO> getAll();
    public List<ChatRoomRecordVO> findByMemNoRecord(Integer MemNo);
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
