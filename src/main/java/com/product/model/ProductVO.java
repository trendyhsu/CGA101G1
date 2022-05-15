package com.product.model;

import java.sql.Timestamp;
import java.util.List;

import com.gamecompany.model.GameCompanyDAO_interface;
import com.gamecompany.model.GameCompanyJDBCDAO;
import com.gamecompany.model.GameCompanyVO;
import com.gameplatformtype.model.GamePlatformTypeDAO_interface;
import com.gameplatformtype.model.GamePlatformTypeJDBCDAO;
import com.gameplatformtype.model.GamePlatformTypeVO;
import com.gametype.model.GameTypeDAO_interface;
import com.gametype.model.GameTypeJDBCDAO;
import com.gametype.model.GameTypeVO;
import com.productPic.model.ProductPicDAO_interface;
import com.productPic.model.ProductPicJDBCDAO;
import com.productPic.model.ProductPicVO;

public class ProductVO implements java.io.Serializable{
	private Integer productNo;
	private Integer gameTypeNo;
	private Integer gamePlatformNo;
	private Integer gameCompanyNo;
	private String productName;
	private Integer productPrice;
	private Integer productState;
	private Timestamp soldTime;
	private Timestamp launchedTime;
	private String itemProdDescription;
	private String upcNum;
	
	
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public Integer getGameTypeNo() {
		return gameTypeNo;
	}
	public void setGameTypeNo(Integer gameTypeNo) {
		this.gameTypeNo = gameTypeNo;
	}
	public Integer getGamePlatformNo() {
		return gamePlatformNo;
	}
	public void setGamePlatformNo(Integer gamePlatformNo) {
		this.gamePlatformNo = gamePlatformNo;
	}
	public Integer getGameCompanyNo() {
		return gameCompanyNo;
	}
	public void setGameCompanyNo(Integer gameCompanyNo) {
		this.gameCompanyNo = gameCompanyNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductState() {
		return productState;
	}
	public void setProductState(Integer productState) {
		this.productState = productState;
	}
	public Timestamp getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(Timestamp soldTime) {
		this.soldTime = soldTime;
	}
	public Timestamp getLaunchedTime() {
		return launchedTime;
	}
	public void setLaunchedTime(Timestamp launchedTime) {
		this.launchedTime = launchedTime;
	}
	public String getItemProdDescription() {
		return itemProdDescription;
	}
	public void setItemProdDescription(String itemProdDescription) {
		this.itemProdDescription = itemProdDescription;
	}
	public String getUpcNum() {
		return upcNum;
	}
	public void setUpcNum(String upcNum) {
		this.upcNum = upcNum;
	}
	
	public GameTypeVO getOneGameType(Integer gameTypeNo) {
		GameTypeDAO_interface dao=new GameTypeJDBCDAO();
		return dao.findByPrimaryKey(gameTypeNo);
	}
	
	public GameTypeVO getOneGameType() {
		GameTypeDAO_interface dao=new GameTypeJDBCDAO();
		return dao.findByPrimaryKey(gameTypeNo);
	}
	
	
	
	public GamePlatformTypeVO getOneGamePlatformType(Integer gamePlatformNo) {
		GamePlatformTypeDAO_interface dao = new GamePlatformTypeJDBCDAO();
		return dao.findByPrimaryKey(gamePlatformNo);
	}
	
	public GamePlatformTypeVO getOneGamePlatformType() {
		GamePlatformTypeDAO_interface dao = new GamePlatformTypeJDBCDAO();
		return dao.findByPrimaryKey(gamePlatformNo);
	}
	
	public GameCompanyVO getOneGameCompanyVO(Integer gameCompanyNo) {
		GameCompanyDAO_interface dao = new GameCompanyJDBCDAO();
		return dao.findByPrimaryKey(gameCompanyNo);
	}
	
	public GameCompanyVO getOneGameCompanyVO() {
		GameCompanyDAO_interface dao = new GameCompanyJDBCDAO();
		return dao.findByPrimaryKey(gameCompanyNo);
	}
	
	
	
	public List<ProductPicVO> getPicsNo() {
		ProductPicDAO_interface dao = new ProductPicJDBCDAO();
		return dao.getOneProductPics(productNo);
	}
	
	public List<ProductPicVO> getPicsNo(Integer productNo) {
		ProductPicDAO_interface dao = new ProductPicJDBCDAO();
		return dao.getOneProductPics(productNo);
	}

}
