package com.gamenews.model;

public class GameNewsVO {
	private Integer gameNewsNo;
	private Integer gamePlatformNo;
	private Integer managerNo;
	private String gameNewsTitle;
	private String gameNewsContent;
	private byte[] gameNewsPic;
	
	public GameNewsVO(){}
	public GameNewsVO(Integer gameNewsNo, Integer gamePlatformNo, Integer managerNo, String gameNewsTitle, String gameNewsContent, byte[] gameNewsPic) {
		this.gameNewsNo = gameNewsNo;
		this.gamePlatformNo = gamePlatformNo;
		this.managerNo = managerNo;
		this.gameNewsTitle = gameNewsTitle;
		this.gameNewsContent = gameNewsContent;
		this.gameNewsPic = gameNewsPic;
	}
	
	@Override
	public String toString() {
		String text = String.format("GameNewsNo: %d, GamePlatformNo: %s, ManagerNo: %s, GameNewsTitle: %s, GameNewsContent: %s,",gameNewsNo, gamePlatformNo, managerNo, gameNewsTitle, gameNewsContent);
		if(gameNewsPic!=null) {
			text += "GameNewsPic: 1";
		}else {
			text += "GameNewsPic: 0";
		}
		return text;
	}

	public Integer getGameNewsNo() {
		return gameNewsNo;
	}
	public void setGameNewsNo(Integer gameNewsNo) {
		this.gameNewsNo = gameNewsNo;
	}
	public Integer getGamePlatformNo() {
		return gamePlatformNo;
	}
	public void setGamePlatformNo(Integer gamePlatformNo) {
		this.gamePlatformNo = gamePlatformNo;
	}
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getGameNewsTitle() {
		return gameNewsTitle;
	}
	public void setGameNewsTitle(String gameNewsTitle) {
		this.gameNewsTitle = gameNewsTitle;
	}
	public String getGameNewsContent() {
		return gameNewsContent;
	}
	public void setGameNewsContent(String gameNewsContent) {
		this.gameNewsContent = gameNewsContent;
	}
	public byte[] getGameNewsPic() {
		return gameNewsPic;
	}
	public void setGameNewsPic(byte[] gameNewsPic) {
		this.gameNewsPic = gameNewsPic;
	}

}
