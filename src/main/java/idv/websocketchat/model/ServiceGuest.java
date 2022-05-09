package idv.websocketchat.model;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class ServiceGuest {
	//成員變數
	public Map<String, Session> guest = new HashMap<>();
	
	//建構子
	public ServiceGuest(Map<String, Session> guest) {
		this.guest = guest;
	}
	
	
	//getter&setter
	public Map<String, Session> getGuest() {
		return guest;
	}


	public void setGuest(Map<String, Session> guest) {
		this.guest = guest;
	}
	
}
