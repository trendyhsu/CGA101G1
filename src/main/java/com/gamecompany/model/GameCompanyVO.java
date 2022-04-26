package com.gamecompany.model;

import java.io.Serializable;

public class GameCompanyVO implements Serializable {
	private Integer gameCompanyNo;
	private String gameCompanyName;

	public GameCompanyVO() {

	}

	public Integer getGameCompanyNo() {
		return gameCompanyNo;
	}

	public void setGameCompanyNo(Integer gameCompanyNo) {
		this.gameCompanyNo = gameCompanyNo;
	}

	public String getGameCompanyName() {
		return gameCompanyName;
	}

	public void setGameCompanyName(String gameCompanyName) {
		this.gameCompanyName = gameCompanyName;
	}

}
