package com.product.model;

import java.util.List;

public interface ProductDAO_interface {

	public void insert(ProductVO ProductVO);
	
	public void insertAndSold(ProductVO ProductVO);

	public void updateAndSold(ProductVO ProductVO);
	
	public void updateNotSold(ProductVO ProductVO);

	public ProductVO findByPrimaryKey(Integer ProductNo);
	
	public ProductVO findNameByPrimaryKey(Integer productNo);
	
	public List<ProductVO> findByTop3MaxPrimaryKey();

	public List<ProductVO> getAll();

	public List<Object> getAllByWord(Integer page,String keyword);
	
	public Integer showSellCountByKeyword(String keyword);
	
	public List<ProductVO> getAllInSell();
	
	public List<Object> getAllInSellByMap();
	
	public List<ProductVO> getAllInDetail();
	
	public List<ProductVO> getAllInName();
	
	public List<Object> getPageInSellByMap(Integer page);
	
	public Integer showSellCount();
	
	

	public List<Object> getPageInSellByMapAndGameType(Integer page,Integer gameTypeNo);
	
	public Integer showSellCountByGameType(Integer gameTypeNo);
	
	public List<Object> getPageInSellByMapAndGamePlatformNo(Integer page,Integer gamePlatformNo);
	
	public Integer showSellCountByGamePlatformType(Integer gamePlatformNo);
	
	public List<Object> getPageInSellByMapAndMoney(Integer page,Integer lowPrice,Integer highPrice);
	
	public Integer showSellCountByMoney(Integer lowPrice,Integer highPrice);


}
