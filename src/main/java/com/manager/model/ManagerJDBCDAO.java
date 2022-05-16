package com.manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.member.model.MemVO;
import com.utils.CompositeQuery_BidProduct;
import com.utils.DButil;


public class ManagerJDBCDAO extends DButil implements ManagerDAO_interface{


	private static final String INSERT_STMT = 
		"INSERT INTO manager (ManagerAccount,ManagerPassword,ManagerName,ManagerPhone,MyManagerPic) "
		+ " VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ManagerNo,ManagerAccount,ManagerPassword,ManagerName,ManagerPhone,MyManagerPic,ManagerState "
		+ " FROM manager order by ManagerNo";
	private static final String GET_ONE_STMT = 
		"SELECT ManagerNo,ManagerAccount,ManagerPassword,ManagerName,ManagerPhone,MyManagerPic,ManagerState "
		+ " FROM manager where ManagerNo = ?";
	private static final String DELETE = 
		" DELETE FROM manager where ManagerNo = ?";
	private static final String UPDATE = 
		"UPDATE manager set ManagerAccount=?, ManagerPassword=?, ManagerName=?, ManagerPhone=?, MyManagerPic=?, "
		+ " ManagerState=? where ManagerNo = ?";
	private static final String UPDATE_MANAGERSTATE = "UPDATE manager SET ManagerState = ? WHERE ManagerNo = ?";
	private static final String SELECT_FOR_LOGIN = "SELECT * FROM manager WHERE managerAccount= ? and managerPassword= ? ";
	private static final String SELECT_MANAGER_ACCOUNT = "SELECT managerAccount FROM manager WHERE managerAccount=?";
	private static final String INSERT_REGISTER = 
			"INSERT INTO manager (ManagerAccount,ManagerPassword,ManagerName,ManagerState) "
			+ " VALUES (?, ?, ?, ?)";
	@Override
	public void insert(ManagerVO managerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, managerVO.getManagerAccount());
			pstmt.setString(2, managerVO.getManagerPassword());
			pstmt.setString(3, managerVO.getManagerName());
			pstmt.setString(4, managerVO.getManagerPhone());
			pstmt.setBytes(5, managerVO.getMyManagerPic());

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
	public void insertRegister(ManagerVO managerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_REGISTER);

			pstmt.setString(1, managerVO.getManagerAccount());
			pstmt.setString(2, managerVO.getManagerPassword());
			pstmt.setString(3, managerVO.getManagerName());
			pstmt.setInt(4, 0);

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
	public void update(ManagerVO managerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, managerVO.getManagerAccount());
			pstmt.setString(2, managerVO.getManagerPassword());
			pstmt.setString(3, managerVO.getManagerName());
			pstmt.setString(4, managerVO.getManagerPhone());
			pstmt.setBytes(5, managerVO.getMyManagerPic());
			pstmt.setInt(6, managerVO.getManagerState());
			pstmt.setInt(7, managerVO.getManagerNo());
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
	public void delete(Integer managerNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ManagerVO findByPrimaryKey(Integer managerNo) {
		// TODO Auto-generated method stub
		ManagerVO managerVO = null;
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
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerPhone(rs.getString("managerPhone"));
				managerVO.setMyManagerPic(rs.getBytes("myManagerPic"));
				managerVO.setManagerState(rs.getInt("managerState"));
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
		return managerVO;
	}
	public ManagerVO selectManagerAccount(String managerAccount) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(SELECT_MANAGER_ACCOUNT);

			pstmt.setString(1, managerAccount);

			rs = pstmt.executeQuery();
				while (rs.next()) {
					//
					managerVO = new ManagerVO();
					managerVO.setManagerAccount(rs.getString("managerAccount"));
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
		return managerVO;
	}
	
	@Override
	public List<ManagerVO> getAll() {
		// TODO Auto-generated method stub
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerPhone(rs.getString("managerPhone"));
				managerVO.setMyManagerPic(rs.getBytes("myManagerPic"));
				managerVO.setManagerState(rs.getInt("managerState"));
				
				list.add(managerVO); // Store the row in the list
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
	
	@Override
	public void updateManagerState(ManagerVO managerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE_MANAGERSTATE);
			
			pstmt.setInt(1, managerVO.getManagerState());
			pstmt.setInt(2, managerVO.getManagerNo());
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
	
	public ManagerVO Managerlogin(String managerAccount,String managerPassword) {
		// TODO Auto-generated method stub
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(SELECT_FOR_LOGIN);

			pstmt.setString(1, managerAccount);
			pstmt.setString(2, managerPassword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerPhone(rs.getString("managerPhone"));
				managerVO.setMyManagerPic(rs.getBytes("myManagerPic"));
				managerVO.setManagerState(rs.getInt("managerState"));
				return managerVO;
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
		return managerVO;
	}
	@Override
	public List<ManagerVO> getAll(Map<String, String[]> map) {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			String finalSQL = "select * from manager "
			          + CompositeQuery_BidProduct.get_WhereCondition(map)
			          + "order by managerNo";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();

			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerPhone(rs.getString("managerPhone"));
				managerVO.setMyManagerPic(rs.getBytes("myManagerPic"));
				managerVO.setManagerState(rs.getInt("managerState"));
				
				list.add(managerVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		ManagerJDBCDAO dao = new ManagerJDBCDAO();

//		// 
//		ManagerVO managerVO1 = new ManagerVO();
//		managerVO1.setManagerAccount("A123");
//		managerVO1.setManagerPassword("a123");
//		managerVO1.setManagerName("國國國國");
//		managerVO1.setManagerPhone("0988888888");
//		managerVO1.getMyManagerPic();
//		managerVO1.setManagerState(1);
//		
//		dao.insert(managerVO1);
//
//		//
//		ManagerVO managerVO2 = new ManagerVO();
//		managerVO2.setManagerNo(71001);
//		managerVO2.setManagerAccount("B1234");
//		managerVO2.setManagerPassword("1234");
//		managerVO2.setManagerName("��P�޲z��");
//		managerVO2.setManagerPhone("0988888887");
//		managerVO2.getMyManagerPic();
//		managerVO2.setManagerState(1);
//	
//		dao.update(managerVO2);
//
		// 
//		ManagerVO managerVO4 = new ManagerVO();
//		managerVO4.setManagerNo(71002);
//		managerVO4.setManagerAccount("A123");
//		managerVO4.setManagerPassword("a123");
//		managerVO4.setManagerName("��x�޲z��");
//		managerVO4.setManagerState(1);
//	
//		dao.update(managerVO4);
//		
		
//		// 
////		dao.delete(7014);
//
//		
//		ManagerVO managerVO3 = dao.findByPrimaryKey(71001);
//		System.out.print(managerVO3.getManagerNo() + ",");
//		System.out.print(managerVO3.getManagerAccount() + ",");
//		System.out.print(managerVO3.getManagerPassword() + ",");
//		System.out.print(managerVO3.getManagerName() + ",");
//		System.out.print(managerVO3.getManagerPhone() + ",");
//		System.out.print(managerVO3.getMyManagerPic() + ",");
//		System.out.println(managerVO3.getManagerState());
//		System.out.println("---------------------");
//
		ManagerVO managerVO4 = dao.Managerlogin("Patrick123","p123p1234");
		System.out.print(managerVO4.getManagerNo() + ",");
		System.out.print(managerVO4.getManagerAccount() + ",");
		System.out.print(managerVO4.getManagerPassword() + ",");
		System.out.print(managerVO4.getManagerName() + ",");
		System.out.print(managerVO4.getManagerPhone() + ",");
		System.out.print(managerVO4.getMyManagerPic() + ",");
		System.out.println(managerVO4.getManagerState());
		System.out.println("---------------------");
		
		List<ManagerVO> list = dao.getAll();
		for (ManagerVO aMana : list) {
			System.out.print(aMana.getManagerNo() + ",");
			System.out.print(aMana.getManagerAccount() + ",");
			System.out.print(aMana.getManagerPassword() + ",");
			System.out.print(aMana.getManagerName() + ",");
			System.out.print(aMana.getManagerPhone() + ",");
			System.out.print(aMana.getMyManagerPic() + ",");
			System.out.println(aMana.getManagerState());
			System.out.println();
		}
	}
	@Override
	public void insertManagerPic(ManagerVO managerVO) {
		// TODO Auto-generated method stub
		
	}
	
}
