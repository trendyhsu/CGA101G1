package com.couponType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponTypeJDBCDAO implements CouponType_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	
	private static final String INSERT="INSERT INTO coupontype(CouponName, DiscountPrice, CouponDeadline, CouponQuantity, CouponDescription)\r\n"
			+ "VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE="UPDATE coupontype SET  CouponName= ?,\r\n"
			+ "DiscountPrice= ? , CouponDeadline = ?, CouponQuantity= ?,CouponDescription= ?\r\n"
			+ "WHERE CouponTypeNo= ?";
	private static final String DELETE="DELETE FROM coupontype WHERE CouponTypeNo= ?";
	private static final String GETALL="SELECT CouponTypeNo, CouponName, DiscountPrice, CouponDeadline, CouponQuantity, couponDescription\r\n"
			+ "FROM coupontype ORDER BY couponTypeNo;";
	private static final String GETONE="SELECT couponTypeNo, couponName, discountPrice, couponDeadline, couponQuantity, CouponDescription\r\n"
			+ "FROM coupontype WHERE couponTypeNo = ?;";
	@Override
	public void insert(CouponTypeVO couponTypeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, couponTypeVO.getCouponName());
			ps.setInt(2, couponTypeVO.getDiscountPrice());
			ps.setObject(3, couponTypeVO.getCouponDeadline());
			ps.setInt(4, couponTypeVO.getCouponQuantity());
			ps.setString(5, couponTypeVO.getCouponDescription());
			ps.executeUpdate();
		
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}  finally {
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
	public void update(CouponTypeVO couponTypeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, couponTypeVO.getCouponName());
			ps.setInt(2, couponTypeVO.getDiscountPrice());
			ps.setObject(3, couponTypeVO.getCouponDeadline());
			ps.setInt(4, couponTypeVO.getCouponQuantity());
			ps.setString(5, couponTypeVO.getCouponDescription());
			ps.setInt(6, couponTypeVO.getCouponTypeNo());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}  finally {
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
	public void delete(Integer couponTypeNo) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);
			ps.setInt(1, couponTypeNo);
			ps.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public CouponTypeVO getOne(Integer couponTypeNo) {

		CouponTypeVO couponTypeVO= null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GETONE);
			
			ps.setInt(1, couponTypeNo);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				couponTypeVO=new CouponTypeVO();
				couponTypeVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				couponTypeVO.setCouponName(rs.getString("couponName"));
				couponTypeVO.setDiscountPrice(rs.getInt("discountPrice"));	
				couponTypeVO.setCouponDeadline(rs.getDate("couponDeadline"));
				couponTypeVO.setCouponQuantity(rs.getInt("couponQuantity"));
				couponTypeVO.setCouponDescription(rs.getString("couponDescription"));
			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return couponTypeVO;
	}
	
	@Override
	public List<CouponTypeVO> getAll() {
		List<CouponTypeVO> list =new ArrayList<CouponTypeVO>();
		CouponTypeVO couponTypeVO=null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				couponTypeVO=new CouponTypeVO();
				couponTypeVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				couponTypeVO.setCouponName(rs.getString("couponName"));
				couponTypeVO.setDiscountPrice(rs.getInt("discountPrice"));	
				couponTypeVO.setCouponDeadline(rs.getDate("couponDeadline"));
				couponTypeVO.setCouponQuantity(rs.getInt("couponQuantity"));
				couponTypeVO.setCouponDescription(rs.getString("couponDescription"));
				list.add(couponTypeVO);
			}			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) {

		CouponTypeJDBCDAO dao=new CouponTypeJDBCDAO();
		
		//新增
		CouponTypeVO couponTypeVO1=new CouponTypeVO();
		couponTypeVO1.setCouponName("買啦!");
		couponTypeVO1.setDiscountPrice(30);
		couponTypeVO1.setCouponDeadline(java.sql.Date.valueOf("2022-08-30"));
		couponTypeVO1.setCouponQuantity(999);
		couponTypeVO1.setCouponDescription("買");
		dao.insert(couponTypeVO1);
		
		//修改
		CouponTypeVO couponTypeVO2=new CouponTypeVO();
		couponTypeVO2.setCouponTypeNo(13004);
		couponTypeVO2.setCouponName("買啦!哪次不買");
		couponTypeVO2.setDiscountPrice(30);
		couponTypeVO2.setCouponDeadline(java.sql.Date.valueOf("2022-08-30"));
		couponTypeVO2.setCouponQuantity(999);
		couponTypeVO2.setCouponDescription("買爆");
		dao.update(couponTypeVO2);
		
		//刪除
		dao.delete(13003);
		
		//查詢單一Row
		CouponTypeVO couponTypeVO3=dao.getOne(13001);
		System.out.println("CouponTypeNo :" + couponTypeVO3.getCouponTypeNo());
		System.out.println("CouponName :" + couponTypeVO3.getCouponName());
		System.out.println("DiscountPrice :" + couponTypeVO3.getDiscountPrice());
		System.out.println("CouponDeadline :" + couponTypeVO3.getCouponDeadline());
		System.out.println("CouponQuantity :" + couponTypeVO3.getCouponQuantity());
		System.out.println("CouponDescription :" + couponTypeVO3.getCouponDescription());
		
		//查詢所有Row
		List<CouponTypeVO> list=dao.getAll();
		for(CouponTypeVO couponType: list) {
			System.out.println("CouponTypeNo :" + couponType.getCouponTypeNo());
			System.out.println("CouponName :" + couponType.getCouponName());
			System.out.println("DiscountPrice :" + couponType.getDiscountPrice());
			System.out.println("CouponDeadline :" + couponType.getCouponDeadline());
			System.out.println("CouponQuantity :" + couponType.getCouponQuantity());
			System.out.println("CouponDescription :" + couponType.getCouponDescription());
			System.out.println("=========================================");
		}
		
	}


}	
