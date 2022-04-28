package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.utils.MailService;
import com.member.model.MemVO;
import static com.core.utils.JSONTrans.json2Pojo;
import static com.core.utils.JSONTrans.writePojo2Json;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/mem/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		MemVO memVO = json2Pojo(request, MemVO.class);
		//寄信驗證
		String subject = "歡迎加入POPGAME";
		String messageText = "Hello!! " + memVO.getMemName() + "  以下是您的驗證網址: "
				  +"\n" +request.getScheme()+"://" +request.getServerName() +":"+request.getServerPort()+ request.getContextPath()+"/mem"+"/MemVerify";
		MailService mail= new MailService();
		mail.sendMail(memVO.getMemEmail(), subject, messageText);
		//調用service
		memVO = MEM_SERVICE.register(memVO);
		//成功即設定session屬性
		if(memVO.isSuccessful()) {
			if(request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("memVO", memVO);
		}
		writePojo2Json(response, memVO);
	}

}
