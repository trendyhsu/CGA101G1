package com.memCoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCouponJDBCDAO implements MemCoupon_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	
	private static final String INSERT="INSERT INTO memcoupon(couponTypeNo, memNo, couponDate) \r\n"
			+ "VALUES (?, ?, ?);";
	private static final String UPDATE="UPDATE memcoupon SET couponTypeNo= ?, memNo= ?,\r\n"
			+ "couponState= ? couponDate=?  WHERE memCouponNo= ?;";
	
	
	private static final String DELETE="DELETE FROM memcoupon WHERE couponTypeNo= ?";
	private static final String GETALL="SELECT memCouponNo, couponTypeNo, memNo, couponState, CouponDate \r\n"
			+ "FROM memcoupon  ORDER BY memCouponNo ;";
	
	private static final String GETONE="SELECT memCouponNo, couponTypeNo, memNo, couponState, CouponDate \r\n"
			+ "FROM memcoupon WHERE memCouponNo= ? ;";
	
	private static final String GET_WHICH_COUPON="SELECT memCouponNo, couponTypeNo, memNo, couponState, CouponDate \r\n"
			+ "FROM memcoupon WHERE couponTypeNo= ? ORDER BY memNo;";
	private static final String GET_WHICH_MEMBER ="SELECT memCouponNo, couponTypeNo, memNo, couponState, CouponDate \r\n"
			+ "FROM memcoupon WHERE memNo= ? and couponState=0 ORDER BY couponTypeNo;";
	@Override
	public void insert(MemCouponVO memCouponVO) {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);
			
			ps.setInt(1, memCouponVO.getCouponTypeNo());
			ps.setInt(2, memCouponVO.getMemNo());
			ps.setDate(3, memCouponVO.getCouponDate());
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
	public void update(MemCouponVO memCouponVO) {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);
			
			ps.setInt(1, memCouponVO.getCouponTypeNo());
			ps.setInt(2, memCouponVO.getMemNo());
			ps.setInt(3, memCouponVO.getCouponState());
			ps.setInt(4, memCouponVO.getMemCouponNo());
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
	public MemCouponVO getOne(Integer memCouponNo) {
		MemCouponVO memCouponVO=null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GETONE);

			ps.setInt(1,memCouponNo);
			rs=ps.executeQuery();
			while(rs.next()) {
				memCouponVO=new MemCouponVO();
				memCouponVO.setMemCouponNo(rs.getInt("memCouponNo"));
				memCouponVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				memCouponVO.setMemNo(rs.getInt("memNo"));
				memCouponVO.setCouponState(rs.getInt("couponState"));
			
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
		return memCouponVO;
	}
	
	public List<MemCouponVO> getOneCouponType (Integer memCouponNo) {
		List<MemCouponVO> list=new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO=null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_WHICH_COUPON);

			ps.setInt(1,memCouponNo);
			rs=ps.executeQuery();
			while(rs.next()) {
				memCouponVO=new MemCouponVO();
				memCouponVO.setMemCouponNo(rs.getInt("memCouponNo"));
				memCouponVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				memCouponVO.setMemNo(rs.getInt("memNo"));
				memCouponVO.setCouponState(rs.getInt("couponState"));
				memCouponVO.setCouponDate(rs.getDate("couponDate"));
				list.add(memCouponVO);
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
	
	public List<MemCouponVO> getOneMember(Integer memNo) {
		List<MemCouponVO> list=new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO=null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_WHICH_MEMBER);

			ps.setInt(1,memNo);
			rs=ps.executeQuery();
			while(rs.next()) {
				memCouponVO=new MemCouponVO();
				memCouponVO.setMemCouponNo(rs.getInt("memCouponNo"));
				memCouponVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				memCouponVO.setMemNo(rs.getInt("memNo"));
				memCouponVO.setCouponState(rs.getInt("couponState"));
				memCouponVO.setCouponDate(rs.getDate("couponDate"));
				list.add(memCouponVO);
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
	
	@Override
	public List<MemCouponVO> getAll() {
		List<MemCouponVO> list=new ArrayList<MemCouponVO>();
		MemCouponVO memCouponVO=null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GETALL);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				memCouponVO=new MemCouponVO();
				memCouponVO.setMemCouponNo(rs.getInt("memCouponNo"));
				memCouponVO.setCouponTypeNo(rs.getInt("couponTypeNo"));
				memCouponVO.setMemNo(rs.getInt("memNo"));
				memCouponVO.setCouponState(rs.getInt("couponState"));
				list.add(memCouponVO);
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
		
		MemCouponJDBCDAO dao=new MemCouponJDBCDAO();
		
//		//新增
//		MemCouponVO memCouponVO1=new MemCouponVO();
//		memCouponVO1.setCouponTypeNo(13004);
//		memCouponVO1.setMemNo(11003);
//		memCouponVO1.setCouponState(1);
//		dao.insert(memCouponVO1);
//		
//		//修改
//		MemCouponVO memCouponVO2=new MemCouponVO();
//		memCouponVO2.setMemCouponNo(12002);
//		memCouponVO2.setCouponTypeNo(13001);
//		memCouponVO2.setMemNo(11002);
//		memCouponVO2.setCouponState(1);
//		dao.update(memCouponVO2);
		
		//刪除
		dao.delete(13008);
		
//		//查詢單一Row
//		MemCouponVO memCouponVO3=dao.getOne(12002);
//		System.out.println("MemCouponNo :" + memCouponVO3.getMemCouponNo());
//		System.out.println("CouponTypeNo :" + memCouponVO3.getCouponTypeNo());
//		System.out.println("MemNo :" + memCouponVO3.getMemNo());
//		System.out.println("CouponState :" + memCouponVO3.getCouponState());
//		
//		//查詢所有Row
//		List<MemCouponVO> list=dao.getAll();
//		for(MemCouponVO memCoupon: list) {
//			System.out.println("MemCouponNo :" + memCoupon.getMemCouponNo());
//			System.out.println("CouponTypeNo :" + memCoupon.getCouponTypeNo());
//			System.out.println("MemNo :" + memCoupon.getMemNo());
//			System.out.println("CouponState :" + memCoupon.getCouponState());
//			System.out.println("==================================");
//		}
//		
	}

	
}
