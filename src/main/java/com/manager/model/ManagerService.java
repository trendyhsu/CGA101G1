package com.manager.model;

import java.util.List;


public class ManagerService {
private ManagerDAO_interface dao;
	
	public ManagerService() {
		// TODO Auto-generated constructor stub
		dao = new ManagerJDBCDAO ();
	}

	public void addManager(String managerAccount, String managerPassword, String managerName,
			String managerPhone, byte[] myManagerPic, Integer managerState) {

		ManagerVO managerVO = new ManagerVO();
		managerVO.setManagerAccount(managerAccount);
		managerVO.setManagerPassword(managerPassword);
		managerVO.setManagerName(managerName);
		managerVO.setManagerPhone(managerPhone);
		managerVO.setMyManagerPic(myManagerPic);
		managerVO.setManagerState(managerState);
		dao.insert(managerVO);
	
	}

	public ManagerVO updateManager(Integer managerNo, String managerAccount, String managerPassword, String managerName,
			String managerPhone,byte[] myManagerPic,Integer managerState) {

		ManagerVO managerVO = new ManagerVO();
		managerVO.setManagerNo(managerNo);
		managerVO.setManagerAccount(managerAccount);
		managerVO.setManagerPassword(managerPassword);
		managerVO.setManagerName(managerName);
		managerVO.setManagerPhone(managerPhone);
		managerVO.setMyManagerPic(myManagerPic);
		managerVO.setManagerState(managerState);
		
		dao.update(managerVO);

		return dao.findByPrimaryKey(managerNo);
	}
	
	public void deleteManager(Integer managerNo) {
		dao.delete(managerNo);
	}
	
	public ManagerVO getOneManager(Integer managerNo) {
		return dao.findByPrimaryKey(managerNo);
	}

	public List<ManagerVO> getAll() {
		return dao.getAll();

	}
	
	public void updateManagerState(ManagerVO managerVO) {
		dao.updateManagerState(managerVO);

	} 
	
	public ManagerVO addManagerPic(Integer managerNo, byte[] myManagerPic) {

		ManagerVO managerVO = new ManagerVO();

		managerVO.setManagerNo(managerNo);
		managerVO.setMyManagerPic(myManagerPic);

		dao.insertManagerPic(managerVO);

		return managerVO;
	}
}