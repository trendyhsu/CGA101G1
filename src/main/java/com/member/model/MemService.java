package com.member.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MemService {
	private MemJDBCDAO dao;

	public MemService() {
		dao = new MemJDBCDAO();
	}

	// 登入
	public MemVO login(MemVO memVO) {
		final String memAccount = memVO.getMemAccount();
		final String memPassword = memVO.getMemPassword();
		if (memAccount == null) {
			memVO.setMessage("帳號未輸入");
			memVO.setSuccessful(false);
			return memVO;
		}

		if (memPassword == null) {
			memVO.setMessage("密碼未輸入");
			memVO.setSuccessful(false);
			return memVO;
		}

		memVO = dao.selectForLogin(memAccount, memPassword);
		if (memVO == null) {
			memVO = new MemVO();
			memVO.setMessage("帳號或密碼錯誤");
			memVO.setSuccessful(false);
			return memVO;
		}
		memVO.setMessage("登入成功");
		memVO.setSuccessful(true);
		return memVO;
	}

	// 註冊
	public MemVO register(MemVO memVo) {
		if (dao.selectMemAccount(memVo.getMemAccount()) != null) {
			memVo.setMessage("帳號重複");
			memVo.setSuccessful(false);
			return memVo;
		} else

		if (dao.selectEmail(memVo.getMemEmail()) != null) {
			memVo.setMessage("一個信箱只能註冊一個帳號");
			memVo.setSuccessful(false);
			return memVo;
		}
		dao.insert(memVo);
		memVo.setMessage("註冊成功，請至信箱點選驗證連結驗證");
		memVo.setSuccessful(true);
		return memVo;
	}

	// 檢驗帳號輸入完後回傳是否有人用過
	public MemVO selectForMemAccount(MemVO memVO) {
		if (dao.selectMemAccount(memVO.getMemAccount()) != null) {
			memVO.setMessage("此帳號已有人使用");
			memVO.setSuccessful(false);
			return memVO;
		}
		memVO.setMessage("註冊成功");
		memVO.setSuccessful(true);
		return memVO;
	}

	// 檢驗信箱輸入完後回傳是否有人用過
	public MemVO selectForMemEmail(MemVO memVO) {
		if (dao.selectEmail(memVO.getMemEmail()) != null) {
			memVO.setMessage("此信箱已註冊過");
			memVO.setSuccessful(false);
			return memVO;
		}
		memVO.setMessage("註冊成功");
		memVO.setSuccessful(true);
		return memVO;
	}

	// 顯示會員個人資料
	public MemVO showMemInfo(MemVO memVO) {
		memVO = dao.getOne(memVO.getMemNo());
		return memVO;
	}
	// 回傳會員個人資料
	public MemVO getMemVOByEmail(String memEmail) {
		MemVO memVO = dao.getOneByEmail(memEmail);
		return memVO;
	}

	// 顯示會員個人照片
	public MemVO showMemPic(int memNo, InputStream defaultPath) {
		MemVO memVO = dao.selectMemPic(memNo);
		if (memVO.getMyPic() == null) {
			try {
				byte[] b2 = new byte[defaultPath.available()];
				defaultPath.read(b2);
				memVO.setMyPic(b2);
				defaultPath.close();
				return memVO;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return memVO;

	}

	// 驗證舊密碼
	public MemVO checkOldPassword(int memNO) {
		MemVO memVO = dao.selectMemPassword(memNO);
		return memVO;
	}

	// 修改成新密碼
	public void updatePassword(String conNewPassword, int memNo) {
		dao.updateMemPassword(conNewPassword, memNo);
	}

	// 忘記密碼 改成新密碼
		public void insertPassword(String conNewPassword, String memEmail) {
			dao.NewMemPassword(conNewPassword, memEmail);
		}
	// 修改會員個人資料
	public MemVO memEdit(MemVO memVO) {
		dao.update(memVO);
		memVO.setMessage("會員資料更新成功!");
		memVO.setSuccessful(true);
		return memVO;
	}

	// 修改會員個人照片
	public void memEditPic(String memAccount, byte[] myPic) {
		dao.updatePic(memAccount, myPic);
	}

	// 顯示所有會員資料
	public List<MemVO> listAllMem() {
		return dao.getAll();
	}

	// 透過手機顯示單一會員資料
	public MemVO getOneMemberByMemMobile(String memMobile) {
		MemVO memVO = dao.getOneByMemMobile(memMobile);
		return memVO;
	}

	// 透過帳號顯示單一會員資料
	public MemVO getOneMemberByMemAccount(String memAccount) {
		MemVO memVO = dao.getOneByMemAccount(memAccount);
		return memVO;
	}

	// 修改會員使用狀態
	public void memStatusEdit(String memAccount, int memStatus) {
		dao.updateMemStatus(memAccount, memStatus);
	}

	// 修改會員帳號信箱驗證
	public void updateVerify(String memAccount) {
		dao.updateVerify(memAccount);
	}

	// 修改會員帳號信箱驗證時間
	public void updateVerifyTime(String memAccount) {
		dao.ChangeVerifyTime(memAccount);
	}
	// 用memNo取得單一會員所有資料
	public MemVO getMemVObyMemNo(Integer memNo) {
		MemVO memVO=dao.getOneByMemNo(memNo);
		return memVO;
	}
}
