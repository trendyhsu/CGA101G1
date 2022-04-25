package com.gametype.model;

import java.util.List;

public class GameTypeService {

	private GameTypeDAO_interface dao;
	
	public GameTypeService() {
		dao = new GameTypeJDBCDAO();
	}

	public void addGameType(String gameTypeName) {

		GameTypeVO gameTypeVO = new GameTypeVO();
		gameTypeVO.setGameTypeName(gameTypeName);
		
		dao.insert(gameTypeVO);
	
	}

	public void updateGameType(String gameTypeName , Integer gameTypeNo) {

		GameTypeVO gameTypeVO = new GameTypeVO();
		gameTypeVO.setGameTypeName(gameTypeName);
		gameTypeVO.setGameTypeNo(gameTypeNo);
		
		dao.update(gameTypeVO);
	
	}
	
	public void deleteGameType(Integer gameTypeNo) {
		dao.delete(gameTypeNo);
	}
	
	public GameTypeVO getOneGameType(Integer gameTypeNo) {
		return dao.findByPrimaryKey(gameTypeNo);
	}

	public List<GameTypeVO> getAll() {
		return dao.getAll();

	}
	
}
