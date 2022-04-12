package com.bidrecord.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import com.utils.DButil;

public class BidRecordJDBCDAO extends DButil implements BidRecordDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO bidrecord (BidProductNo, MemNo, BidPrice, BidTime) VALUES (?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE bidrecord SET BidProductNo =?, MemNo=?, BidPrice=?, BidTime=? WHERE BidRecordNo=? ";

	private static final String DELETE_STMT = "DELETE FROM bidrecord WHERE bidRecordNo = ?";

	private static final String GET_ONE_STMT = "SELECT BidRecordNo, BidProductNo, MemNo, BidPrice, BidTime FROM bidrecord WHERE BidRecordNo = ?";

	private static final String GET_ALL_STMT = "SELECT BidRecordNo, BidProductNo, MemNo, BidPrice, BidTime FROM bidrecord";
	// 使用 BidProductNo 取得所有 BidProductNo 的出價紀錄
	private static final String GET_ALL_STMT_BIDPRODUCTNO = "SELECT BidRecordNo, BidProductNo, MemNo, BidPrice, BidTime FROM bidrecord WHERE BidProductNo = ?";
	// 使用 BidProductNo 取得該商品最高出價
	private static final String GET_ONE_STMT_BIDPRODUCTNO_HIGHEST = "SELECT BidRecordNo, BidProductNo, MemNo, BidPrice, BidTime FROM bidrecord WHERE BidProductNo = ? ORDER BY BidPrice DESC LIMIT 1";
	// 使用 memNo 取得該會員的所有出價紀錄
	private static final String GET_ALL_STMT_MEMNO = "SELECT BidRecordNo, BidProductNo, MemNo, BidPrice, BidTime FROM bidrecord WHERE MemNo = ?";

	@Override
	public void insert(BidRecordVO bidRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidRecordVO.getBidProductNo());
			pstmt.setInt(2, bidRecordVO.getMemNo());
			pstmt.setInt(3, bidRecordVO.getBidPrice());
			pstmt.setTimestamp(4, bidRecordVO.getBidTime());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(BidRecordVO bidRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, bidRecordVO.getBidProductNo());
			pstmt.setInt(2, bidRecordVO.getMemNo());
			pstmt.setInt(3, bidRecordVO.getBidPrice());
			pstmt.setTimestamp(4, bidRecordVO.getBidTime());
			pstmt.setInt(5, bidRecordVO.getBidRecordNo());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer bidRecordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, bidRecordNo);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public BidRecordVO findByPrimaryKey(Integer bidRecordNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, bidRecordNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("BidRecordNo"));
				bidRecordVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidRecordVO.setMemNo(rs.getInt("MemNo"));
				bidRecordVO.setBidPrice(rs.getInt("BidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("BidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}

	@Override
	public List<BidRecordVO> getAll() {
		List<BidRecordVO> list = new ArrayList<BidRecordVO>();
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("BidRecordNo"));
				bidRecordVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidRecordVO.setMemNo(rs.getInt("MemNo"));
				bidRecordVO.setBidPrice(rs.getInt("BidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("BidTime"));
				list.add(bidRecordVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public List<BidRecordVO> findByBidProductNo(Integer bidProductNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BidRecordVO> list = new ArrayList<BidRecordVO>();

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODUCTNO);

			pstmt.setInt(1, bidProductNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
				list.add(bidRecordVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public BidRecordVO findByBidProductNoHighestPrice(Integer bidProductNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDPRODUCTNO_HIGHEST);

			pstmt.setInt(1, bidProductNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("BidRecordNo"));
				bidRecordVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidRecordVO.setMemNo(rs.getInt("MemNo"));
				bidRecordVO.setBidPrice(rs.getInt("BidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("BidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}

	@Override
	public List<BidRecordVO> findByMemNo(Integer memNo) {
		BidRecordVO bidRecordVO = null;
		List<BidRecordVO> list = new ArrayList<BidRecordVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMNO);

			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("BidRecordNo"));
				bidRecordVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidRecordVO.setMemNo(rs.getInt("MemNo"));
				bidRecordVO.setBidPrice(rs.getInt("BidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("BidTime"));
				list.add(bidRecordVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		BidRecordJDBCDAO dao = new BidRecordJDBCDAO();

		// insert方法
//		BidRecordVO bidRecordVO = new BidRecordVO();
//		bidRecordVO.setBidProductNo(31001);
//		bidRecordVO.setMemNo(11003);
//		bidRecordVO.setBidPrice(700);
//		Calendar cal = new GregorianCalendar(2022, 3, 9, 20, 0, 0);
//		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
//		bidRecordVO.setBidTime(timestamp);
//		dao.insert(bidRecordVO);

		// update方法
//		BidRecordVO bidRecordVO2 = new BidRecordVO();
//		bidRecordVO2.setBidRecordNo(33004);
//		bidRecordVO2.setBidProductNo(31001);
//		bidRecordVO2.setBidPrice(750);
//		bidRecordVO2.setMemNo(11003);
//		Timestamp timestamp2 = new Timestamp(new GregorianCalendar(2022, 3, 10,20,0,0).getTimeInMillis());
//		bidRecordVO2.setBidTime(timestamp2);
//		dao.update(bidRecordVO2);

		// delete方法
//		dao.delete(33004);
		
		// getAll方法
//		List<BidRecordVO> list = dao.getAll();
//		for (BidRecordVO bidRecordVO : list) {
//			System.out.print(bidRecordVO.getBidRecordNo() + " , ");
//			System.out.print(bidRecordVO.getBidProductNo() + " , ");
//			System.out.print(bidRecordVO.getMemNo() + " , ");
//			System.out.print(bidRecordVO.getBidPrice() + " , ");
//			System.out.println(bidRecordVO.getBidTime() + " , ");
//			System.out.println("------------------");
//		}
		
		// findByPrimaryKey方法
//		BidRecordVO bidRecordVO3 = dao.findByPrimaryKey(33001);
//		System.out.print(bidRecordVO3.getBidRecordNo() + " , ");
//		System.out.print(bidRecordVO3.getBidProductNo() + " , ");
//		System.out.print(bidRecordVO3.getMemNo() + " , ");
//		System.out.print(bidRecordVO3.getBidPrice() + " , ");
//		System.out.println(bidRecordVO3.getBidTime() + " , ");
//		System.out.println("------------------");
		
		// findByBidProductNoHighestPrice方法
//		BidRecordVO bidRecordVO4 = dao.findByBidProductNoHighestPrice(31001);
//		System.out.print(bidRecordVO4.getBidRecordNo() + " , ");
//		System.out.print(bidRecordVO4.getBidProductNo() + " , ");
//		System.out.print(bidRecordVO4.getMemNo() + " , ");
//		System.out.print(bidRecordVO4.getBidPrice() + " , ");
//		System.out.println(bidRecordVO4.getBidTime() + " , ");
//		System.out.println("------------------");
		
		// findByMemNo方法
//		List<BidRecordVO> list2 = dao.findByMemNo(11002) ;
//		for (BidRecordVO bidRecordVO : list2) {
//			System.out.print(bidRecordVO.getBidRecordNo() + " , ");
//			System.out.print(bidRecordVO.getBidProductNo() + " , ");
//			System.out.print(bidRecordVO.getMemNo() + " , ");
//			System.out.print(bidRecordVO.getBidPrice() + " , ");
//			System.out.println(bidRecordVO.getBidTime() + " , ");
//			System.out.println("------------------");
//		}
		
		// findByBidProductNo方法
		List<BidRecordVO> list3 = dao.findByBidProductNo(31001) ;
		for (BidRecordVO bidRecordVO : list3) {
			System.out.print(bidRecordVO.getBidRecordNo() + " , ");
			System.out.print(bidRecordVO.getBidProductNo() + " , ");
			System.out.print(bidRecordVO.getMemNo() + " , ");
			System.out.print(bidRecordVO.getBidPrice() + " , ");
			System.out.println(bidRecordVO.getBidTime() + " , ");
			System.out.println("------------------");
		}
		
	}

}
