package com.managerauth.model;

import java.util.List;
import java.util.Set;

import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;

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
	//刪除，管理員全功能
	public void deleteOneManagerAuthrizationFunction(Integer managerNo) {
		dao.deleteAll(managerNo);
	}
	//查詢，單一管理員多個功能
	public ManagerAuthVO getOneManagerAuth(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}
	//查詢，all
	public List<ManagerAuthVO> getAll() {
		return dao.getAll();

	}
	//查詢，單一管理員多個功能
		public List<ManagerAuthVO> getFunction(Integer managerNo) {
			return dao.getFunction(managerNo);
		}
}