package com.member.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemJDBCDAO implements MemDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";

	private static final String INSERT = "INSERT INTO mem(MemAccount, MemPassword, MemName, MemMobile, MemCity, MemDist, MemAdd\r\n"
			+ ", MemEmail, MemBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String UPDATE = "UPDATE mem set "
			+ "memAccount = ?,  memName = ?, memMobile = ?, memCity = ?, memDist = ?, memAdd = ?,"
			+ "memEmail = ?, memBirth = ?, creditcardNo = ? ,creditcardDate = ? ,creditcardSecurityNo = ?,bankAccount = ? ,bankAccountOwner = ?  "
			+ "WHERE memAccount= ? ";

	private static final String GETALL = "SELECT memNo, memAccount, memPassword, memStatus, memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd,"
			+ " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus, myPic, isMute FROM"
			+ " mem ORDER BY memNo";

	private static final String SELECT_FOR_LOGIN = "SELECT * FROM mem WHERE memAccount= ? and memPassword= ? ";

	private static final String SELECT_MEM_ACCOUNT = "SELECT memAccount FROM mem WHERE memAccount=?";

	private static final String SELECT_MEM_EMAIL = "SELECT memEmail FROM mem WHERE memEmail=?";

	private static final String SELECT_MEM_MOBILE = "SELECT memNo, memAccount, memPassword, memStatus,memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd, "
			+ " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus FROM"
			+ " mem WHERE memMobile = ?";;

	private static final String SELECT_MEM_MEMNAME="SELECT * FROM mem WHERE memName=?";
			
	private static final String UPDATE_MEM_VERITY = "UPDATE mem SET memvrfed=? WHERE memAccount=?";

	private static final String SELECT_MEM_VERITY = "SELECT memvrfed FROM mem WHERE memAccount=?";

	private static final String SELECT_MEM_INFO = "SELECT * FROM mem WHERE memAccount= ?";

	private static final String SELECT_MEM_PIC = "SELECT myPic FROM mem WHERE memNo= ?";

	private static final String SELECT_MEM_PASSWORD = "SELECT memPassword FROM mem WHERE memNo=?";

	private static final String UPDATE_MEM_PASSWORD = "UPDATE mem SET memPassword=? WHERE memNo=?";

	private static final String UPDATE_MEM_FORGETPASSWORD = "UPDATE mem SET memPassword=? WHERE memEmail=?";
	
	private static final String UPDATE_MEM_PIC = "UPDATE mem SET myPic=? WHERE memAccount=?";

	private static final String UPDATE_MEM_STATUS = "UPDATE mem SET memStatus=? WHERE memAccount=?";

	private static final String UPDATE_MEM_VERITY_TIME = "UPDATE mem SET memNoVrftime=? WHERE memAccount=?";

	private static final String UPDATE_MEM_ISMUTE_STATUS ="UPDATE mem SET isMute=? WHERE memNo=?";
	
	private static final String GET_ONE_BY_MEMACCOUNT = "SELECT memNo, memAccount, memPassword, memStatus,memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd, "
			+ " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus FROM"
			+ " mem WHERE memAccount = ?";

	private static final String GET_ONE = "SELECT memNo, memAccount, memPassword, memStatus,memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd, "
			+ " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus FROM"
			+ " mem WHERE memNo = ?";
	
	private static final String GET_ONE_BY_EMAIL="SELECT memNo, memAccount, memPassword, memStatus,memVrfed, memNoVrftime, memName, memMobile, memCity, memDist, memAdd, "
			+ " memEmail, memBirth, memJoinTime, creditcardNo, creditcardDate, creditcardSecurityNo, bankAccount, bankAccountOwner, userStatus FROM"
			+ " mem WHERE memEmail = ?";
	
	private static final String GET_ONE_BY_MEMNO = "SELECT * FROM mem WHERE memNo = ?";
	
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);

			ps.setString(1, memVO.getMemAccount());
			ps.setString(2, memVO.getMemPassword());
			ps.setString(3, memVO.getMemName());
			ps.setString(4, memVO.getMemMobile());
			ps.setString(5, memVO.getMemCity());
			ps.setString(6, memVO.getMemDist());
			ps.setString(7, memVO.getMemAdd());
			ps.setString(8, memVO.getMemEmail());
			ps.setObject(9, memVO.getMemBirth());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, memVO.getMemAccount());
			ps.setString(2, memVO.getMemName());
			ps.setString(3, memVO.getMemMobile());
			ps.setString(4, memVO.getMemCity());
			ps.setString(5, memVO.getMemDist());
			ps.setString(6, memVO.getMemAdd());
			ps.setString(7, memVO.getMemEmail());
			ps.setObject(8, memVO.getMemBirth());
			ps.setString(9, memVO.getCreditcardNo());
			ps.setString(10, memVO.getCreditcardDate());
			ps.setString(11, memVO.getCreditcardSecurityNo());
			ps.setString(12, memVO.getBankAccount());
			ps.setString(13, memVO.getBankAccountOwner());
			ps.setString(14, memVO.getMemAccount());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MemVO getOne(Integer memNo) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, memNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPassword(rs.getString("memPassword"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemVrfed(rs.getInt("memVrfed"));
				memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemMobile(rs.getString("memMobile"));
				memVO.setMemCity(rs.getString("memCity"));
				memVO.setMemDist(rs.getString("memDist"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemBirth(rs.getDate("memBirth"));
				memVO.setMemJoinTime(rs.getDate("memJoinTime"));
				memVO.setCreditcardNo(rs.getString("creditcardNo"));
				memVO.setCreditcardDate(rs.getString("creditcardDate"));
				memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
				memVO.setBankAccount(rs.getString("bankAccount"));
				memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
				memVO.setUserStatus(rs.getInt("userStatus"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return memVO;
	}

	public MemVO getOneByEmail(String memEmail) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE_BY_EMAIL);

			ps.setString(1, memEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPassword(rs.getString("memPassword"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemVrfed(rs.getInt("memVrfed"));
				memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemMobile(rs.getString("memMobile"));
				memVO.setMemCity(rs.getString("memCity"));
				memVO.setMemDist(rs.getString("memDist"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemBirth(rs.getDate("memBirth"));
				memVO.setMemJoinTime(rs.getDate("memJoinTime"));
				memVO.setCreditcardNo(rs.getString("creditcardNo"));
				memVO.setCreditcardDate(rs.getString("creditcardDate"));
				memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
				memVO.setBankAccount(rs.getString("bankAccount"));
				memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
				memVO.setUserStatus(rs.getInt("userStatus"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return memVO;
	}
	
	
	public MemVO getOneByMemAccount(String memAccount) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE_BY_MEMACCOUNT);

			ps.setString(1, memAccount);

			rs = ps.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPassword(rs.getString("memPassword"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemVrfed(rs.getInt("memVrfed"));
				memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemMobile(rs.getString("memMobile"));
				memVO.setMemCity(rs.getString("memCity"));
				memVO.setMemDist(rs.getString("memDist"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemBirth(rs.getDate("memBirth"));
				memVO.setMemJoinTime(rs.getDate("memJoinTime"));
				memVO.setCreditcardNo(rs.getString("creditcardNo"));
				memVO.setCreditcardDate(rs.getString("creditcardDate"));
				memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
				memVO.setBankAccount(rs.getString("bankAccount"));
				memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
				memVO.setUserStatus(rs.getInt("userStatus"));
				return memVO;
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	public MemVO getOneByMemMobile(String memMobile) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_MOBILE)) {
			ps.setString(1, memMobile);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemNo(rs.getInt("memNo"));
					memVO.setMemAccount(rs.getString("memAccount"));
					memVO.setMemPassword(rs.getString("memPassword"));
					memVO.setMemStatus(rs.getInt("memStatus"));
					memVO.setMemVrfed(rs.getInt("memVrfed"));
					memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
					memVO.setMemName(rs.getString("memName"));
					memVO.setMemMobile(rs.getString("memMobile"));
					memVO.setMemCity(rs.getString("memCity"));
					memVO.setMemDist(rs.getString("memDist"));
					memVO.setMemAdd(rs.getString("memAdd"));
					memVO.setMemEmail(rs.getString("memEmail"));
					memVO.setMemBirth(rs.getDate("memBirth"));
					memVO.setMemJoinTime(rs.getDate("memJoinTime"));
					memVO.setCreditcardNo(rs.getString("creditcardNo"));
					memVO.setCreditcardDate(rs.getString("creditcardDate"));
					memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
					memVO.setBankAccount(rs.getString("bankAccount"));
					memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
					memVO.setUserStatus(rs.getInt("userStatus"));
					memVO.setMyPic(rs.getBytes("myPic"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {

		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getInt("memNo"));
				memVO.setMemAccount(rs.getString("memAccount"));
				memVO.setMemPassword(rs.getString("memPassword"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemVrfed(rs.getInt("memVrfed"));
				memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemMobile(rs.getString("memMobile"));
				memVO.setMemCity(rs.getString("memCity"));
				memVO.setMemDist(rs.getString("memDist"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemBirth(rs.getDate("memBirth"));
				memVO.setMemJoinTime(rs.getDate("memJoinTime"));
				memVO.setCreditcardNo(rs.getString("creditcardNo"));
				memVO.setCreditcardDate(rs.getString("creditcardDate"));
				memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
				memVO.setBankAccount(rs.getString("bankAccount"));
				memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
				memVO.setUserStatus(rs.getInt("userStatus"));
				memVO.setMyPic(rs.getBytes("myPic"));
				memVO.setIsMute(rs.getInt("isMute"));
				list.add(memVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public void updateVerify(String memAccount) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_VERITY)) {
			ps.setInt(1, 1);
			ps.setString(2, memAccount);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ChangeVerifyTime(String memAccount) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_VERITY_TIME)) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			String now = year + "-" + month + "-" + date;
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(now);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(1, sqlDate);
			ps.setString(2, memAccount);
			ps.executeUpdate();
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeIsMute(Integer isMute, Integer memNo) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_ISMUTE_STATUS)) {
			ps.setInt(1, isMute);
			ps.setInt(2, memNo);
			ps.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public MemVO selectVerify(String memAccount) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_VERITY)) {
			ps.setString(1, memAccount);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemVrfed(rs.getInt("memvrfed"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemVO selectMemInfo(String memAccount) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_INFO)) {
			ps.setString(1, memAccount);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
//					memVO.setMemAccount(rs.getString("memAccount"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemVO selectMemEname(String memEname) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_MEMNAME)) {
			ps.setString(1, memEname);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemNo(rs.getInt("memNo"));
					memVO.setMemAccount(rs.getString("memAccount"));
					memVO.setMemPassword(rs.getString("memPassword"));
					memVO.setMemStatus(rs.getInt("memStatus"));
					memVO.setMemVrfed(rs.getInt("memVrfed"));
					memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
					memVO.setMemName(rs.getString("memName"));
					memVO.setMemMobile(rs.getString("memMobile"));
					memVO.setMemCity(rs.getString("memCity"));
					memVO.setMemDist(rs.getString("memDist"));
					memVO.setMemAdd(rs.getString("memAdd"));
					memVO.setMemEmail(rs.getString("memEmail"));
					memVO.setMemBirth(rs.getDate("memBirth"));
					memVO.setMemJoinTime(rs.getDate("memJoinTime"));
					memVO.setCreditcardNo(rs.getString("creditcardNo"));
					memVO.setCreditcardDate(rs.getString("creditcardDate"));
					memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
					memVO.setBankAccount(rs.getString("bankAccount"));
					memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
					memVO.setUserStatus(rs.getInt("userStatus"));
					memVO.setMyPic(rs.getBytes("myPic"));
					memVO.setIsMute(rs.getInt("isMute"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemVO selectMemPic(int memNo) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_PIC)) {
			ps.setInt(1, memNo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMyPic(rs.getBytes("myPic"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemVO selectMemPassword(int memNo) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_PASSWORD)) {
			ps.setInt(1, memNo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemPassword(rs.getString("memPassword"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateMemPassword(String memPassword, int memNo) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_PASSWORD)) {
			ps.setString(1, memPassword);
			ps.setInt(2, memNo);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void NewMemPassword(String memPassword, String memEmail) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_FORGETPASSWORD)) {
			ps.setString(1, memPassword);
			ps.setString(2, memEmail);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMemStatus(String memAccount, int memStatus) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_STATUS)) {
			ps.setInt(1, memStatus);
			ps.setString(2, memAccount);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePic(String memAccount, byte[] myPic) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(UPDATE_MEM_PIC)) {
			ps.setBytes(1, myPic);
			ps.setString(2, memAccount);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemVO selectMemAccount(String memAccount) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_ACCOUNT)) {
			ps.setString(1, memAccount);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemAccount(rs.getString("memAccount"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemVO selectEmail(String memEmail) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_MEM_EMAIL)) {
			ps.setString(1, memEmail);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemEmail(rs.getString("memEmail"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MemVO selectForLogin(String memAccount, String memPassword) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(SELECT_FOR_LOGIN)) {

			ps.setString(1, memAccount);
			ps.setString(2, memPassword);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemNo(rs.getInt("memNo"));
					memVO.setMemAccount(rs.getString("memAccount"));
					memVO.setMemPassword(rs.getString("memPassword"));
					memVO.setMemStatus(rs.getInt("memStatus"));
					memVO.setMemVrfed(rs.getInt("memVrfed"));
					memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
					memVO.setMemName(rs.getString("memName"));
					memVO.setMemMobile(rs.getString("memMobile"));
					memVO.setMemCity(rs.getString("memCity"));
					memVO.setMemDist(rs.getString("memDist"));
					memVO.setMemAdd(rs.getString("memAdd"));
					memVO.setMemEmail(rs.getString("memEmail"));
					memVO.setMemBirth(rs.getDate("memBirth"));
					memVO.setMemJoinTime(rs.getDate("memJoinTime"));
					memVO.setCreditcardNo(rs.getString("creditcardNo"));
					memVO.setCreditcardDate(rs.getString("creditcardDate"));
					memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
					memVO.setBankAccount(rs.getString("bankAccount"));
					memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
					memVO.setUserStatus(rs.getInt("userStatus"));
					memVO.setMyPic(rs.getBytes("myPic"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public MemVO getOneByMemNo(Integer memNo) {
		MemVO memVO = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GET_ONE_BY_MEMNO)) {

			ps.setInt(1, memNo);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					memVO = new MemVO();
					memVO.setMemNo(rs.getInt("memNo"));
					memVO.setMemAccount(rs.getString("memAccount"));
					memVO.setMemPassword(rs.getString("memPassword"));
					memVO.setMemStatus(rs.getInt("memStatus"));
					memVO.setMemVrfed(rs.getInt("memVrfed"));
					memVO.setMemNoVrftime(rs.getDate("memNoVrftime"));
					memVO.setMemName(rs.getString("memName"));
					memVO.setMemMobile(rs.getString("memMobile"));
					memVO.setMemCity(rs.getString("memCity"));
					memVO.setMemDist(rs.getString("memDist"));
					memVO.setMemAdd(rs.getString("memAdd"));
					memVO.setMemEmail(rs.getString("memEmail"));
					memVO.setMemBirth(rs.getDate("memBirth"));
					memVO.setMemJoinTime(rs.getDate("memJoinTime"));
					memVO.setCreditcardNo(rs.getString("creditcardNo"));
					memVO.setCreditcardDate(rs.getString("creditcardDate"));
					memVO.setCreditcardSecurityNo(rs.getString("creditcardSecurityNo"));
					memVO.setBankAccount(rs.getString("bankAccount"));
					memVO.setBankAccountOwner(rs.getString("bankAccountOwner"));
					memVO.setUserStatus(rs.getInt("userStatus"));
					memVO.setMyPic(rs.getBytes("myPic"));
					return memVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}


	public static void main(String[] args) throws IOException {

//		MemJDBCDAO dao = new MemJDBCDAO();

		// 新增
//		MemVO memVO1 = new MemVO();
//		memVO1.setMemAccount("z123");
//		memVO1.setMemPassword("z123");
//		memVO1.setMemName("hihi");
//		memVO1.setMemMobile("0895708957");
//		memVO1.setMemCity("龜山島");
//		memVO1.setMemDist("龜山區");
//		memVO1.setMemAdd("龜山路10號");
//		memVO1.setMemEmail("hihihi@123.com");
//		memVO1.setMemBirth(java.sql.Date.valueOf("2002-01-01"));
//		FileInputStream in = new FileInputStream("GotIt.jpeg");
//		byte[] b= new byte[in.available()];
//		in.read(b);
//		memVO1.setMyPic(b);
//		in.close();
//		dao.insert(memVO1);

		// 修改
//		MemVO memVO2 =new MemVO();
//		memVO2.setMemNo(11003);
//		memVO2.setMemAccount("hsu123");
//		memVO2.setMemPassword("hsu123");
//		memVO2.setMemStatus(0);
//		memVO2.setMemVrfed(1);
//		memVO2.setMemNoVrftime(java.sql.Date.valueOf("2022-04-08"));
//		memVO2.setMemName("早餐店小帥哥");
//		memVO2.setMemMobile("0912345345");
//		memVO2.setMemCity("早餐店市");
//		memVO2.setMemDist("早餐店區");
//		memVO2.setMemAdd("早餐街111號");
//		memVO2.setMemEmail("trendy81502@gmail.com");
//		memVO2.setMemBirth(java.sql.Date.valueOf("1992-05-02"));
//		memVO2.setMemJoinTime(java.sql.Date.valueOf("2022-04-08"));
//		memVO2.setCreditcardNo("1234567812345678");
//		memVO2.setCreditcardDate("0525");
//		memVO2.setCreditcardSecurityNo("012");
//		memVO2.setBankAccount("006-005568855688");
//		memVO2.setBankAccountOwner("早餐店小帥哥");
//		memVO2.setUserStatus(1);
//		FileInputStream in2 = new FileInputStream("SoHandsome.jpg");
//		byte[] b2= new byte[in2.available()];
//		in2.read(b2);
//		memVO2.setMyPic(b2);
//		in2.close();
//		dao.update(memVO2);
//		

//		// 查詢單一Row
//		MemVO memVO3 = dao.getOne(11003);
//		System.out.println("MemNo :" + memVO3.getMemNo());
//		System.out.println("MemAccount :" + memVO3.getMemAccount());
//		System.out.println("MemPassword :" + memVO3.getMemPassword());
//		System.out.println("getMemStatus :" + memVO3.getMemStatus());
//		System.out.println("MemVrfed :" + memVO3.getMemVrfed());
//		System.out.println("MemNoVrftime :" + memVO3.getMemNoVrftime());
//		System.out.println("MemName :" + memVO3.getMemName());
//		System.out.println("getMemMobile :" + memVO3.getMemMobile());
//		System.out.println("MemCity :" + memVO3.getMemCity());
//		System.out.println("MemDist :" + memVO3.getMemDist());
//		System.out.println("MemAdd :" + memVO3.getMemAdd());
//		System.out.println("MemEmail :" + memVO3.getMemEmail());
//		System.out.println("MemBirth :" + memVO3.getMemBirth());
//		System.out.println("MemJoinTime :" + memVO3.getMemJoinTime());
//		System.out.println("CreditcardNo :" + memVO3.getCreditcardNo());
//		System.out.println("CreditcardDate :" + memVO3.getCreditcardDate());
//		System.out.println("CreditcardSecurityNo :" + memVO3.getCreditcardSecurityNo());
//		System.out.println("BankAccount :" + memVO3.getBankAccount());
//		System.out.println("BankAccountOwne :" + memVO3.getBankAccountOwner());
//		System.out.println("UserStatus :" + memVO3.getUserStatus());
//		System.out.println("MyPic :" + memVO3.getMyPic());
//		
//		// 查詢所有Row
//		List<MemVO> list=dao.getAll();
//		for (MemVO mem : list) {
//			System.out.println("MemNo :" + mem.getMemNo());
//			System.out.println("MemAccount :" + mem.getMemAccount());
//			System.out.println("MemPassword :" + mem.getMemPassword());
//			System.out.println("getMemStatus :" + mem.getMemStatus());
//			System.out.println("MemVrfed :" + mem.getMemVrfed());
//			System.out.println("MemNoVrftime :" + mem.getMemNoVrftime());
//			System.out.println("MemName :" + mem.getMemName());
//			System.out.println("getMemMobile :" + mem.getMemMobile());
//			System.out.println("MemCity :" + mem.getMemCity());
//			System.out.println("MemDist :" + mem.getMemDist());
//			System.out.println("MemAdd :" + mem.getMemAdd());
//			System.out.println("MemEmail :" + mem.getMemEmail());
//			System.out.println("MemBirth :" + mem.getMemBirth());
//			System.out.println("MemJoinTime :" + mem.getMemJoinTime());
//			System.out.println("CreditcardNo :" + mem.getCreditcardNo());
//			System.out.println("CreditcardDate :" + mem.getCreditcardDate());
//			System.out.println("CreditcardSecurityNo :" + mem.getCreditcardSecurityNo());
//			System.out.println("BankAccount :" + mem.getBankAccount());
//			System.out.println("BankAccountOwne :" + mem.getBankAccountOwner());
//			System.out.println("UserStatus :" + mem.getUserStatus());
//			System.out.println("MyPic :" + mem.getMyPic());
//			System.out.println("===============================================");
//		}
//		
	}

}
