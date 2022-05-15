package com.myfavoritelist.model;

import java.util.List;

public interface MyfavoritelistDAO_Interface {

	//刪除
	public void delete(Integer memNo,Integer productNo);
		
	//查有沒有重複特定項目
	public MyfavoritelistVo getOneByOneMem(Integer memNo,Integer productNo);
	
   //添加一個
	public void insert(Integer memNo,Integer productNo);
	
	
	//查某會員全部的項目
	public List<MyfavoritelistVo> getAllByOneMem(Integer memNo);
	
	//查某會員全部的項目
	public List<Object> getAllByOneMemInJoin(Integer memNo);
	
	
}
