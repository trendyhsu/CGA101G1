package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gamecompany.model.GameCompanyService;
import com.gamecompany.model.GameCompanyVO;
import com.gameplatformtype.model.GamePlatformTypeService;
import com.gameplatformtype.model.GamePlatformTypeVO;
import com.gametype.model.GameTypeService;
import com.gametype.model.GameTypeVO;

@WebServlet("/bid/bidProductGetGame")
public class BidProductGetGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Writer out = response.getWriter();
		
		// 取得所有遊戲類別
		GameTypeService gameTypeSvc = new GameTypeService();
		List<GameTypeVO> gameTypeVOs = gameTypeSvc.getAll();
		JSONArray jsonObject1 = new JSONArray(gameTypeVOs);
		
		// 取得所有遊戲公司
		GameCompanyService gameCompanySvc = new GameCompanyService();
		List<GameCompanyVO> gameCompanyVOs = gameCompanySvc.getAll();
		JSONArray jsonObject2 = new JSONArray(gameCompanyVOs);
		
		// 取得所有遊戲平台
		GamePlatformTypeService gamePlatformTypeSvc = new GamePlatformTypeService();
		List<GamePlatformTypeVO> gamePlatformTypeVOs = gamePlatformTypeSvc.getAll();
		JSONArray jsonObject3 = new JSONArray(gamePlatformTypeVOs);
		
		// 創建JSONObject物件 裝入上述物件
		JSONObject gameAll = new JSONObject();

		gameAll.put("gameTypeVOs", jsonObject1);
		gameAll.put("gameCompanyVOs", jsonObject2);
		gameAll.put("gamePlatformTypeVOs", jsonObject3);

		out.write(gameAll.toString());
	}
	
}

