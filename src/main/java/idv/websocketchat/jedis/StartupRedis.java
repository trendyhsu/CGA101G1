package idv.websocketchat.jedis;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet(value = "/initializeResources", loadOnStartup = 1)
public class StartupRedis extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		try {
			Process exec = Runtime.getRuntime().exec("redis-server C:\\Redis\\redis.windows.conf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			Process exec = Runtime.getRuntime().exec("redis-cli shutdown");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
