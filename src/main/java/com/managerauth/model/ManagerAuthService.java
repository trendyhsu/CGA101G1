package com.managerauth.model;

import java.util.List;

public class ManagerAuthService {
	private ManagerAuthDAO_interface dao;

	public ManagerAuthService() {
		// TODO Auto-generated constructor stub
		dao = new ManagerAuthJDBCDAO();
	}
	//新增
	public void addManagerAuth(Integer managerNo, Integer managerAuthrizationFunctionNo) {

		ManagerAuthVO managerAuthVO = new ManagerAuthVO();
		managerAuthVO.setManagerNo(managerNo);
		managerAuthVO.setManagerAuthrizationFunctionNo(managerAuthrizationFunctionNo);
		dao.insert(managerAuthVO);

	}
	//修改，應該用不到
	public ManagerAuthVO updateManagerAuth(Integer managerAuthrizationFunctionNo,Integer managerNo) {

		ManagerAuthVO managerAuthVO = new ManagerAuthVO();
		managerAuthVO.setManagerAuthrizationFunctionNo(managerAuthrizationFunctionNo);
		managerAuthVO.setManagerNo(managerNo);

		dao.update(managerAuthVO);

		return dao.findByPrimaryKey(managerNo);
	}
	//刪除，管理員單一功能
	public void deleteOneManagerAuthrizationFunction(Integer managerAuthrizationFunctionNo,Integer managerNo) {
		dao.delete(managerAuthrizationFunctionNo,managerNo);
	}
	//查詢，單一管理員多個功能
	public ManagerAuthVO getOneManagerAuth(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}
	//查詢，all
	public List<ManagerAuthVO> getAll() {
		return dao.getAll();

	}

}