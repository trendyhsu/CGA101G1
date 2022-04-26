package com.gamecompany.model;

import java.util.List;

public interface GameCompanyDAO_interface {
	
	public void insert(GameCompanyVO gameCompanyVO);

	public void update(GameCompanyVO gameCompanyVO);

	public void delete(Integer gameCompanyNo);

	public GameCompanyVO findByPrimaryKey(Integer gameCompanyNo);

	public List<GameCompanyVO> getAll();

}
