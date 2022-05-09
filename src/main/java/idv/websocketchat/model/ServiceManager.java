package idv.websocketchat.model;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import idv.servicechat.controller.ServiceWS;


public class ServiceManager {
	//成員變數
	public Map<String, Session> manager = new HashMap<>();

	//建構子
	public ServiceManager(Map<String, Session> manager) {
		this.manager = manager;
	}
	
	
	//getter&setter
	public  Map<String, Session> getManager() {
		return manager;
	}

	public void setManager(Map<String, Session> manager) {
		this.manager = manager;
	}


	
	
}
