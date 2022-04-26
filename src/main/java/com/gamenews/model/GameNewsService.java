package com.gamenews.model;

import java.util.List;
import java.util.Map;

import com.fqkeyword.model.FQKeyWordVO;

public class GameNewsService {

	private GameNewsDAOImpl dao;
	
	public GameNewsService() {
		dao = new GameNewsDAOImpl();
	}
	
	public GameNewsVO addGameNews(Integer platformNo, Integer managerNo, String gameNewsTitle, String gameNewsContent, byte[] gameNewsPic) {
		GameNewsVO gameNewsVO = new GameNewsVO();
		gameNewsVO.setGamePlatformNo(platformNo);
		gameNewsVO.setManagerNo(managerNo);
		gameNewsVO.setGameNewsTitle(gameNewsTitle);
		gameNewsVO.setGameNewsContent(gameNewsContent);
		gameNewsVO.setGameNewsPic(gameNewsPic);
		dao.insert(gameNewsVO);
		return gameNewsVO;
	}
	
	public GameNewsVO updateGameNews(Integer platformNo, Integer managerNo, String gameNewsTitle, String gameNewsContent, byte[] pic, Integer gameNewsNo) {
		GameNewsVO gameNewsVO = new GameNewsVO();
		gameNewsVO.setGamePlatformNo(platformNo);
		gameNewsVO.setManagerNo(managerNo);
		gameNewsVO.setGameNewsTitle(gameNewsTitle);
		gameNewsVO.setGameNewsContent(gameNewsContent);
		gameNewsVO.setGameNewsPic(pic);
		gameNewsVO.setGameNewsNo(gameNewsNo);
		dao.update(gameNewsVO);
		return gameNewsVO;
	}
	
	public List<GameNewsVO> getAll(Map<String, String[]>map){
		return dao.getAll(map);
	}
	
	public GameNewsVO getOne(Integer gameNewsNo) {
		return dao.findByPrimaryKey(gameNewsNo);
	}
}
