package com.myfavoritelist.model;

import java.util.List;

public class MyfavoritelistService {
	private MyfavoritelistDAO_Interface dao;
	
	public MyfavoritelistService() {
		dao = new MyfavoritelistDAO();
	}
	
	public MyfavoritelistVo getOneByOneMem(Integer memNo, Integer productNo) {
		return dao.getOneByOneMem(memNo, productNo);
	};
	
	public void deleteOne(Integer memNo,Integer productNo) {
		dao.delete(memNo, productNo);
	};
	
	public void addOne(Integer memNo,Integer productNo) {
		dao.insert(memNo, productNo);
	}
	
	public List<MyfavoritelistVo> getAllByOneMem(Integer memNo) {
		return dao.getAllByOneMem(memNo);
	};
	
	
	public List<Object> getAllByOneMemInJoin(Integer memNo) {
		return dao.getAllByOneMemInJoin(memNo);
	};
}
