package com.managerauthrizationfunction.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.utils.DButil;

public class ManagerAuthrizationFunctionJDBCDAO extends DButil implements ManagerAuthrizationFunctionDAO_interface{

	private static final String INSERT_STMT = 
		"INSERT INTO managerauthrizationfunction (managerAuthrizationFunction) "
		+ "VALUES (?)";
	private static final String GET_ALL_STMT = 
		"SELECT managerAuthrizationFunctionNo,managerAuthrizationFunction "
		+ "FROM managerauthrizationfunction order by managerAuthrizationFunctionNo";
	private static final String GET_ONE_STMT = 
		"SELECT managerAuthrizationFunctionNo,managerAuthrizationFunction "
		+ "FROM managerauthrizationfunction where managerAuthrizationFunctionNo = ?";
//	private static final String DELETE = 
//		"DELETE FROM managerauthrizationfunction where managerAuthrizationFunctionNo = ?";
	private static final String UPDATE = 
		"UPDATE managerauthrizationfunction set managerAuthrizationFunction=? "
		+ "where ManagerAuthrizationFunctionNo = ?";
	
	@Override
	public void insert(ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, managerAuthrizationFunctionVO.getManagerAuthrizationFunction());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
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
	public void update(ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, managerAuthrizationFunctionVO.getManagerAuthrizationFunction());
			pstmt.setInt(2, managerAuthrizationFunctionVO.getManagerAuthrizationFunctionNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
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
	public void delete(Integer managerauthrizationfunctionno) {
		// TODO Auto-generated method stub
	}

	@Override
	public ManagerAuthrizationFunctionVO findByPrimaryKey(Integer managerAuthrizationFunctionNo) {
		// TODO Auto-generated method stub
		ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, managerAuthrizationFunctionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 
				managerAuthrizationFunctionVO = new ManagerAuthrizationFunctionVO();
				managerAuthrizationFunctionVO.setManagerAuthrizationFunctionNo(rs.getInt("managerAuthrizationFunctionNo"));
				managerAuthrizationFunctionVO.setManagerAuthrizationFunction(rs.getString("managerAuthrizationFunction"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
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
		return managerAuthrizationFunctionVO;
	}

	@Override
	public List<ManagerAuthrizationFunctionVO> getAll() {
		// TODO Auto-generated method stub
		List<ManagerAuthrizationFunctionVO> list = new ArrayList<ManagerAuthrizationFunctionVO>();
		ManagerAuthrizationFunctionVO managerAuthrizationFunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 
				managerAuthrizationFunctionVO = new ManagerAuthrizationFunctionVO();
				managerAuthrizationFunctionVO.setManagerAuthrizationFunctionNo(rs.getInt("managerAuthrizationFunctionNo"));
				managerAuthrizationFunctionVO.setManagerAuthrizationFunction(rs.getString("managerAuthrizationFunction"));
				list.add(managerAuthrizationFunctionVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
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

		ManagerAuthrizationFunctionJDBCDAO dao = new ManagerAuthrizationFunctionJDBCDAO();

		// 
//		ManagerAuthrizationFunctionVO MAFunVO1 = new ManagerAuthrizationFunctionVO();
//		MAFunVO1.setManagerAuthrizationFunction("text");
//		dao.insert(MAFunVO1);
//
//		// 
//		ManagerAuthrizationFunctionVO MAFunVO2 = new ManagerAuthrizationFunctionVO();
//		MAFunVO2.setManagerAuthrizationFunctionNo(73001);
//		MAFunVO2.setManagerAuthrizationFunction("textt");
//		dao.update(MAFunVO2);

		// 
//		dao.delete(7014);

		// 
		ManagerAuthrizationFunctionVO MAFunVO3 = dao.findByPrimaryKey(73001);
		System.out.print(MAFunVO3.getManagerAuthrizationFunctionNo() + ",");
		System.out.print(MAFunVO3.getManagerAuthrizationFunction() + ",");
		System.out.println("---------------------");

		// 
		List<ManagerAuthrizationFunctionVO> list = dao.getAll();
		for (ManagerAuthrizationFunctionVO managerFunction : list) {
			System.out.print(managerFunction.getManagerAuthrizationFunctionNo() + ",");
			System.out.print(managerFunction.getManagerAuthrizationFunction() + ",");
			System.out.println();
		}
	}
	
}
