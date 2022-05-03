package com.manager.model;

import java.util.List;

import com.member.model.MemVO;



public class ManagerService {
//private ManagerDAO_interface dao;
private ManagerJDBCDAO dao;
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

	public ManagerVO addManagerRegister(ManagerVO managerVO) {

		ManagerVO managerVO1 = new ManagerVO();
		if (dao.selectManagerAccount(managerVO1.getManagerAccount()) != null) {
			managerVO1.setMessage("帳號重複");
			managerVO1.setSuccessful(false);
			return managerVO1;
			
		}else {
		dao.insertRegister(managerVO1);
		managerVO1.setMessage("註冊成功");
		managerVO1.setSuccessful(true);
		return managerVO1;
		}
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
	// login
	public ManagerVO login(ManagerVO managerVO) {
		final String managerAccount = managerVO.getManagerAccount();
		final String managerPassword = managerVO.getManagerPassword();
		if (managerAccount == null) {
			managerVO.setMessage("帳號未輸入");
			managerVO.setSuccessful(false);
			return managerVO;
		}

		if (managerPassword == null) {
			managerVO.setMessage("密碼未輸入");
			managerVO.setSuccessful(false);
			return managerVO;
		}

		managerVO = dao.Managerlogin(managerAccount, managerPassword);
		if (managerVO == null) {
			managerVO = new ManagerVO();
			managerVO.setMessage("帳號或密碼錯誤");
			managerVO.setSuccessful(false);
			return managerVO;
		}
		managerVO.setMessage("登入成功");
		managerVO.setSuccessful(true);
		return managerVO;
	}
		// register
		public ManagerVO register(ManagerVO managerVO) {
			if (dao.selectManagerAccount(managerVO.getManagerAccount()) != null) {
				managerVO.setMessage("帳號重複");
				managerVO.setSuccessful(false);
				return managerVO;
			}else {
			dao.insert(managerVO);
			managerVO.setMessage("註冊成功");
			managerVO.setSuccessful(true);
			return managerVO;
			
			}
		}
}