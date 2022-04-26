package com.gamecompany.model;

import java.util.List;

public class GameCompanyService {

	private GameCompanyDAO_interface dao;

	public GameCompanyService() {
		dao = new GameCompanyJDBCDAO();
	}

	public void addGameCompany(String gameCompanyName) {
		GameCompanyVO gameCompanyVO = new GameCompanyVO();
		gameCompanyVO.setGameCompanyName(gameCompanyName);
		
		dao.insert(gameCompanyVO);
	}
	
	public void updateGameCompany(String gameCompanyName, Integer gameCompanyNo) {
		GameCompanyVO gameCompanyVO = new GameCompanyVO();
		gameCompanyVO.setGameCompanyName(gameCompanyName);
		gameCompanyVO.setGameCompanyNo(gameCompanyNo);
		
		dao.update(gameCompanyVO);
	}
	
	public GameCompanyVO getOneGameCompanyVO(Integer gameCompanyNo) {
		return dao.findByPrimaryKey(gameCompanyNo);
	}
	
	public List<GameCompanyVO> getAll(){
		return dao.getAll();
	}
}
