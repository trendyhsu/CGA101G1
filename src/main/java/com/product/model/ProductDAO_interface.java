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

	public List<ProductVO> getAllByWord(String ProductName);
	
	public List<ProductVO> getAllInSell();
	
	public List<Object> getAllInSellByMap();
	
	public List<ProductVO> getAllInDetail();
	
	public List<ProductVO> getAllInName();

}
