package com.gamenews.model;

import java.util.List;
import java.util.Map;

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
		gameNewsVO.setGameNewsNo(gameNewsNo);
		
		if(pic.length != 0) {
			gameNewsVO.setGameNewsPic(pic);
			dao.update(gameNewsVO);
		}else {
			dao.updateWithoutPic(gameNewsVO);
		}

		return gameNewsVO;
	}
	
	public void deleteGameNews(Integer gameNewsNo) {
		dao.delete(gameNewsNo);
	}
	public List<GameNewsVO> getAll(Map<String, String[]>map){
		return dao.getAll(map);
	}
	
	public List<GameNewsVO> getAll(){
		return dao.getAll();
	}
	
	public GameNewsVO getOne(Integer gameNewsNo) {
		return dao.findByPrimaryKey(gameNewsNo);
	}
	
	public List<GameNewsVO> getTopTwelve(Integer gamePlatformNo){
		return dao.getTopTwelve(gamePlatformNo);
	}

}
