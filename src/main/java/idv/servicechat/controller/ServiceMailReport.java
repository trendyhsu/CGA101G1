package idv.servicechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.MailService;

@WebServlet("/service/mailform")
public class ServiceMailReport extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
	
			String receiver = req.getParameter("receiver");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String to = "cga101g1@gmail.com";
			//驗證信箱格式
			String regex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			Pattern p = Pattern.compile(regex);
			
			if(receiver.trim().length() == 0 || title.trim().length() == 0 || content.trim().length() == 0) {
				out.print("ERROR！每個欄位都得要填寫阿帕才能幫你寄出喔！");
			}else if (!p.matcher(receiver).find()){
				out.print("ERROR！亂填信箱是收不到信的喔！");
			}else {
				MailService mailService = new MailService();
				mailService.sendMail(to, title, "聯絡信箱: "+ receiver + "\r\n回報內容: "+content);
				out.print("阿帕已成功幫您寄出信件囉!");
			}
			
		
		
	}

}
