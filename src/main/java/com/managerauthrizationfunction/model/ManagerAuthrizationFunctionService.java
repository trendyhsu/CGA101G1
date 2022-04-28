package com.managerauthrizationfunction.model;

import java.util.List;

public class ManagerAuthrizationFunctionService {
	private ManagerAuthrizationFunctionDAO_interface dao;

	public ManagerAuthrizationFunctionService() {
		// TODO Auto-generated constructor stub
		dao = new ManagerAuthrizationFunctionJDBCDAO();
	}
	//新增
	public void addManagerFunction(String managerAuthrizationFunction) {

		ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO = new ManagerAuthrizationFunctionVO();
		managerAuthrizationFunctionVO.setManagerAuthrizationFunction(managerAuthrizationFunction);
		dao.insert(managerAuthrizationFunctionVO);

	}
	//修改
	public ManagerAuthrizationFunctionVO updateManagerFunction(Integer managerAuthrizationFunctionNo,
			String managerAuthrizationFunction) {

		ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO = new ManagerAuthrizationFunctionVO();
		managerAuthrizationFunctionVO.setManagerAuthrizationFunctionNo(managerAuthrizationFunctionNo);
		managerAuthrizationFunctionVO.setManagerAuthrizationFunction(managerAuthrizationFunction);

		dao.update(managerAuthrizationFunctionVO);

		return dao.findByPrimaryKey(managerAuthrizationFunctionNo);
	}
	//刪除，用不到
	public void deleteManagerAuthrizationFunction(Integer managerAuthrizationFunctionNo) {
		dao.delete(managerAuthrizationFunctionNo);
	}
	//查詢單一
	public ManagerAuthrizationFunctionVO getOneManagerAuthrizationFunction(Integer managerAuthrizationFunctionNo) {
		return dao.findByPrimaryKey(managerAuthrizationFunctionNo);
	}
	//查詢，all
	public List<ManagerAuthrizationFunctionVO> getAll() {
		return dao.getAll();

	}

}