package com.gameplatformtype.model;

import java.util.List;

public class GamePlatformTypeService {

	private GamePlatformTypeDAO_interface dao;
	
	public GamePlatformTypeService() {
		dao = new GamePlatformTypeJDBCDAO ();
	}

	public void addGamePlatformType(String gamePlatformName) {

		GamePlatformTypeVO gamePlatformTypeVO = new GamePlatformTypeVO();
		gamePlatformTypeVO.setGamePlatformName(gamePlatformName);
		
		dao.insert(gamePlatformTypeVO);
	
	}

	public GamePlatformTypeVO updateGamePlatformType(String gamePlatformName , Integer gamePlatformNo) {

		GamePlatformTypeVO gamePlatformTypeVO = new GamePlatformTypeVO();
		gamePlatformTypeVO.setGamePlatformName(gamePlatformName);
		gamePlatformTypeVO.setGamePlatformNo(gamePlatformNo);
		
		dao.update(gamePlatformTypeVO);

		return dao.findByPrimaryKey(gamePlatformNo);
	}
	
	public void deleteGamePlatformType(Integer gamePlatformNo) {
		dao.delete(gamePlatformNo);
	}
	
	public GamePlatformTypeVO getOneGamePlatformType(Integer gamePlatformNo) {
		return dao.findByPrimaryKey(gamePlatformNo);
	}

	public List<GamePlatformTypeVO> getAll() {
		return dao.getAll();

	}
	
}
