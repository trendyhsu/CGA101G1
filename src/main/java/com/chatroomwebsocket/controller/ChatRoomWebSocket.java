package com.chatroomwebsocket.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import com.chatroombanlist.model.ChatRoomBanListService;
import com.chatroombanlist.model.ChatRoomBanListVO;
import com.chatroomrecord.model.ChatRoomRecordService;
import com.member.model.MemVO;

// 傳入forumNo 以區隔是哪個討論區的聊天室
@ServerEndpoint(value = "/chatroom/{forumNo}", configurator = ServletAwareConfig.class)
public class ChatRoomWebSocket {
	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private EndpointConfig config;
	
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-
	 * httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	// 當開啟時
	// 這裡可以放Map 來區隔不同的遊戲討論區，彼此不會互相看到訊息
	@OnOpen
	public void onOpen(@PathParam("forumNo") String forumNo, Session userSession, EndpointConfig config) throws IOException {
		this.config = config;
		// 將這個連線放進去session中，之後可以廣播
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, 連線進來了; forumNo = %s", userSession.getId(), forumNo);
//		System.out.println(text);
	}

	// 當收到訊息時
	// 調用資料庫 存入資料
	@OnMessage
	public void onMessage(@PathParam("forumNo") String forumNo, Session userSession, String message) {
		
		// 從 config 中獲取 httpsession
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        
        // 再從httpsession去取得連進來的會員
        MemVO memVO = (MemVO)httpSession.getAttribute("memVO");
        String memName = null;
        if(memVO != null) {
        	memName = memVO.getMemName();       	
        }
        
        // 調用service 存入資料庫
//		ChatRoomRecordService chatRoomRecordSvc = new ChatRoomRecordService();
//		chatRoomRecordSvc.addChatRecord(memVO.getMemNo(), Integer.valueOf(forumNo), message);
        
		System.out.println("收到訊息了: " + message);
		
		// 準備好傳到前台的文字
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(memName);
		stringBuilder.append(" 對大家說: ");
		stringBuilder.append(message);
		JSONObject chatAll = new JSONObject();
//		System.out.println(stringBuilder.toString());
		chatAll.put("sendMemNo", memVO.getMemNo());
		chatAll.put("message", stringBuilder.toString());
		
		// 傳送所有被ban名單
		ChatRoomBanListService chatRoomBanListSvc = new ChatRoomBanListService();
		List<ChatRoomBanListVO> chatRoomBanListVOs = chatRoomBanListSvc.getAll();
		JSONArray jsonArray = new JSONArray(chatRoomBanListVOs);
		chatAll.put("chatRoomBanListVOs", jsonArray);
		
//		System.out.println(chatAll.toString());
		
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(chatAll.toString());
		}
	}

	// 當連線關閉時
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, 連線離開了; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
	}

	// 當發生錯誤時
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("發生錯誤了: " + e.toString());
	}

}
