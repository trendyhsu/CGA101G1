package com.chatroomwebsocket.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

// 傳入forumNo
@ServerEndpoint("/chatroom/{userName}")
public class ChatRoomWebSocket {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	// 當開啟時
	// 這裡可以放Map 來區隔不同的遊戲討論區，彼此不會互相看到訊息
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, 連線進來了; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}
	
	// 當收到訊息時
	// 調用資料庫 存入資料
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("收到訊息了: " + message);
	}
	
	// 當連線關閉時
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}
	
	// 當發生錯誤時
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("發生錯誤了: " + e.toString());
	}

}
