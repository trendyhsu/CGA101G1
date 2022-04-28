package com.forum.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;

@WebServlet("/forum/forumEditUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumMsg.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 **********************/
		ForumService forumSvc = new ForumService();
		ForumVO forumVO = new ForumVO();
		
		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());

		String forumName = request.getParameter("forumName").trim();
		String forumNameReg = "^[(\u4e00-\u9fa5)(\u00A0,\u0020,\u3000)(a-zA-Z0-9_)]{1,100}$";

		if (forumName == null || forumName.trim().length() == 0) {
			errorMsgs.put("forumName", "討論區名稱: 請勿空白");
		} else if (!forumName.trim().matches(forumNameReg)) {
			errorMsgs.put("forumName", "討論區名稱: 只能是中、英文字母、數字、空白建和_ , 且長度必需在1到100之間");
		}

		Integer forumType = Integer.valueOf(request.getParameter("forumType").trim());
		Integer memNo = Integer.valueOf(request.getParameter("memNo").trim());
		
		// 將取得圖片資料裝入 List<byte[]> 物件
				Collection<Part> list = request.getParts();
				List<byte[]> picList = new ArrayList<byte[]>();
				InputStream is = null;
				BufferedInputStream bis = null;
				byte[] forumImg = null;

				// 使用 (is.available() > 1024) 過濾一起帶過來的文字資料
				for (Part part : list) {
					is = part.getInputStream();
					bis = new BufferedInputStream(is);
					if (bis.available() > 1024) {
						forumImg = new byte[bis.available()];
						bis.read(forumImg);
						picList.add(forumImg);
					}
				}


		// 回傳錯誤訊息
		if (!errorMsgs.isEmpty()) {

			forumVO.setForumName(request.getParameter("forumName"));
			forumVO.setForumType(Integer.valueOf(request.getParameter("forumType")));
			forumVO.setMemNo(Integer.valueOf(request.getParameter("memNo")));

			request.setAttribute("forumVO", forumVO);

			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForum.jsp");
			failureView.forward(request, response);
			return; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/
		if (memNo != 0) {
			
			 forumVO = forumSvc.updateForum(forumNo, forumName, forumType, memNo);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

		} else {

			 forumVO = forumSvc.updateForumNoMem(forumNo, forumName, forumType);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

		}
		
		
		for (int i = 0; i < picList.size(); i++) {
			forumSvc.addForumImg(forumNo, picList.get(i));
		}
		
		request.setAttribute("forumVO", forumVO); // 資料庫update成功後,正確的的forumVO物件,存入request
		String url = "/backend/forum/listOneForum.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForum.jsp
		successView.forward(request, response);

	}

}
