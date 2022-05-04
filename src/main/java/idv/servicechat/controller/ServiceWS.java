package idv.servicechat.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/servicews")
public class ServiceWS {
	private final static Set<Session> sessionSet = Collections.synchronizedSet(new HashSet<>());
	
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println(userSession);
		sessionSet.add(userSession);
	}

	@OnMessage
	public void onMessage(Session session,String message) {
		
		synchronized (sessionSet) {
			for (Session s : sessionSet) {
				if (s.isOpen() && s != session) {
						s.getAsyncRemote().sendText(message);						
				} 
				
				if(!s.isOpen()) {
					sessionSet.remove(s);
				}
			}
		}		
	
	}

	@OnError
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@OnClose
	public void onClose() {
		
	}
}
