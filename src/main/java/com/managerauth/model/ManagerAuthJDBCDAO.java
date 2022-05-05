package com.managerauth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;
import com.utils.DButil;

public class ManagerAuthJDBCDAO extends DButil implements ManagerAuthDAO_interface{
	
	private static final String INSERT_STMT = 
		"INSERT INTO managerauth (managerNo,managerAuthrizationFunctionNo) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT managerNo,managerAuthrizationFunctionNo FROM managerauth order by managerNo";
	private static final String GET_ONE_STMT = 
		"SELECT ManagerNo,ManagerAuthrizationFunctionNo FROM managerauth where managerNo = ?";
	private static final String DELETEONE = 
		"DELETE FROM managerauth where (managerNo = ?) And (managerAuthrizationFunctionNo = ?)";
	private static final String DELETEALL = 
			"DELETE FROM managerauth where managerNo = ?";
	private static final String UPDATE = 
		"UPDATE managerauth set managerAuthrizationFunctionNo=? where managerNo = ?";
	private static final String GET_FUNCTIONNAME = 
	"SELECT managerAuthrizationFunctionNo,managerNo "
	+ "FROM managerauth WHERE managerNo = ?";
	@Override
	public void insert(ManagerAuthVO managerAuthVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, managerAuthVO.getManagerNo());
			pstmt.setInt(2, managerAuthVO.getManagerAuthrizationFunctionNo());

			pstmt.executeUpdate();

			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void update(ManagerAuthVO managerAuthVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, managerAuthVO.getManagerAuthrizationFunctionNo());
			pstmt.setInt(2, managerAuthVO.getManagerNo());

			pstmt.executeUpdate();

			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void delete(Integer managerAuthrizationFunctionNo,Integer managerNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(DELETEONE);

			pstmt.setInt(1, managerAuthrizationFunctionNo);
			pstmt.setInt(2, managerNo);
			pstmt.executeUpdate();
			System.out.println("成功刪除");
			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void deleteAll(Integer managerNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(DELETEALL);

			pstmt.setInt(1, managerNo);
			pstmt.executeUpdate();
			System.out.println("成功刪除");
			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public ManagerAuthVO findByPrimaryKey(Integer managerNo) {
		// TODO Auto-generated method stub
		ManagerAuthVO managerAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 
				
				managerAuthVO = new ManagerAuthVO();
				managerAuthVO.setManagerNo(rs.getInt("managerNo"));
				managerAuthVO.setManagerAuthrizationFunctionNo(rs.getInt("managerAuthrizationFunctionNo"));
				managerAuthVO.getOneManagerAuthrizationFunctionVO();
			}

			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return managerAuthVO;
	}

	@Override
	public List<ManagerAuthVO> getAll() {
		// TODO Auto-generated method stub
		List<ManagerAuthVO> list = new ArrayList<ManagerAuthVO>();
		ManagerAuthVO managerAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				managerAuthVO = new ManagerAuthVO();
				managerAuthVO.setManagerNo(rs.getInt("managerNo"));
				managerAuthVO.setManagerAuthrizationFunctionNo(rs.getInt("managerAuthrizationFunctionNo"));
				list.add(managerAuthVO);
			}

			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	@Override
	public List<ManagerAuthVO> getFunction(Integer managerNo) {
		// TODO Auto-generated method stub
		List<ManagerAuthVO> list = new ArrayList<ManagerAuthVO>();
		ManagerAuthVO managerAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_FUNCTIONNAME);

			pstmt.setInt(1, managerNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 
				managerAuthVO = new ManagerAuthVO();
				managerAuthVO.setManagerAuthrizationFunctionNo(rs.getInt("managerAuthrizationFunctionNo"));
				list.add(managerAuthVO);
			}

			// Handle any getDriver() errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database getDriver(). "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public static void main(String[] args) {

		ManagerAuthJDBCDAO dao = new ManagerAuthJDBCDAO();

		// 新增
		ManagerAuthVO MAuthVO1 = new ManagerAuthVO();
		MAuthVO1.setManagerNo(71015);
		MAuthVO1.setManagerAuthrizationFunctionNo(73001);
		dao.insert(MAuthVO1);

//		// 修改
//		ManagerAuthVO MAuthVO2 = new ManagerAuthVO();
//		MAuthVO2.setManagerno(71001);
//		MAuthVO2.setManagerauthrizationfunctionno(73003);
//		dao.update(MAuthVO2);

		// 刪除
//		dao.delete(71001, 73003);

		// 
//		ManagerAuthVO MAuthVO3 = dao.findByPrimaryKey(71001);
//		System.out.print(MAuthVO3.getManagerNo() + ",");
//		System.out.print(MAuthVO3.getManagerAuthrizationFunctionNo() + ",");
//		System.out.print(MAuthVO3.getOneManagerAuthrizationFunctionVO() + ",");
//		System.out.println("---------------------");
//
//		// 
//		List<ManagerAuthVO> list = dao.getAll();
//		for (ManagerAuthVO managerAuth : list) {
//			System.out.print(managerAuth.getManagerNo() + ",");
//			System.out.print(managerAuth.getManagerAuthrizationFunctionNo() + ",");
//			System.out.println();
//		}
		List<ManagerAuthVO> list = dao.getFunction(71001);
		System.out.println(list);
		for (ManagerAuthVO aFun : list) {
			System.out.print(aFun.getManagerAuthrizationFunctionNo() + ",");
			System.out.println();
		}
	}
}
