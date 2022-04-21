package com.bidapplylist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import com.utils.DButil;

public class BidApplyListJDBCDAO extends DButil implements BidApplyListDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO bidapplylist(MemNo, BidName, BidProdDescription, GameCompanyNo, GameTypeNo, GamePlatformNo, InitialPrice, BidPriceIncrement, UpcNum, BidLaunchedTime, BidSoldTime, ApplyState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE bidapplylist SET MemNo=?, BidName=?, BidProdDescription=?, GameCompanyNo=?, GameTypeNo=?, GamePlatformNo=?, InitialPrice=?, BidPriceIncrement=?, UpcNum=?, BidLaunchedTime=?, BidSoldTime=?, ApplyState=? WHERE BidApplyListNo=?";
	private static final String DELETE_STMT = "DELETE FROM bidapplylist WHERE BidApplyListNo = ?";
	private static final String GET_ALL_STMT = "SELECT BidApplyListNo, MemNo, BidName, BidProdDescription, GameCompanyNo, GameTypeNo, GamePlatformNo, InitialPrice, BidPriceIncrement, UpcNum, BidLaunchedTime, BidSoldTime, ApplyState FROM bidapplylist";
	private static final String GET_ONE_STMT = "SELECT BidApplyListNo, MemNo, BidName, BidProdDescription, GameCompanyNo, GameTypeNo, GamePlatformNo, InitialPrice, BidPriceIncrement, UpcNum, BidLaunchedTime, BidSoldTime, ApplyState FROM bidapplylist WHERE BidApplyListNo = ?";
	private static final String GET_ALL_STMT_MEMNO = "SELECT BidApplyListNo, MemNo, BidName, BidProdDescription, GameCompanyNo, GameTypeNo, GamePlatformNo, InitialPrice, BidPriceIncrement, UpcNum, BidLaunchedTime, BidSoldTime, ApplyState FROM bidapplylist WHERE MemNo = ?";
	private static final String UPDATE_ONE_STMT_APPLYSTATE = "UPDATE bidapplylist set ApplyState=? WHERE BidApplyListNo=?";
	@Override
	public void insert(BidApplyListVO bidApplyListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidApplyListVO.getMemNo());
			pstmt.setString(2, bidApplyListVO.getBidName());
			pstmt.setString(3, bidApplyListVO.getBidProdDescription());
			pstmt.setInt(4, bidApplyListVO.getGameCompanyNo());
			pstmt.setInt(5, bidApplyListVO.getGameTypeNo());
			pstmt.setInt(6, bidApplyListVO.getGamePlatformNo());
			pstmt.setInt(7, bidApplyListVO.getInitialPrice());
			pstmt.setInt(8, bidApplyListVO.getBidPriceIncrement());
			pstmt.setString(9, bidApplyListVO.getUpcNum());
			pstmt.setTimestamp(10, bidApplyListVO.getBidLaunchedTime());
			pstmt.setTimestamp(11, bidApplyListVO.getBidSoldTime());
			pstmt.setInt(12, bidApplyListVO.getApplyState());

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
	public void update(BidApplyListVO bidApplyListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, bidApplyListVO.getMemNo());
			pstmt.setString(2, bidApplyListVO.getBidName());
			pstmt.setString(3, bidApplyListVO.getBidProdDescription());
			pstmt.setInt(4, bidApplyListVO.getGameCompanyNo());
			pstmt.setInt(5, bidApplyListVO.getGameTypeNo());
			pstmt.setInt(6, bidApplyListVO.getGamePlatformNo());
			pstmt.setInt(7, bidApplyListVO.getInitialPrice());
			pstmt.setInt(8, bidApplyListVO.getBidPriceIncrement());
			pstmt.setString(9, bidApplyListVO.getUpcNum());
			pstmt.setTimestamp(10, bidApplyListVO.getBidLaunchedTime());
			pstmt.setTimestamp(11, bidApplyListVO.getBidSoldTime());
			pstmt.setInt(12, bidApplyListVO.getApplyState());
			pstmt.setInt(13, bidApplyListVO.getBidApplyListNo());

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
	public void delete(Integer bidApplyListNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, bidApplyListNo);

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
	public List<BidApplyListVO> getAll() {
		List<BidApplyListVO> list = new ArrayList<BidApplyListVO>();
		BidApplyListVO bidApplyListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidApplyListVO = new BidApplyListVO();
				bidApplyListVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidApplyListVO.setMemNo(rs.getInt("MemNo"));
				bidApplyListVO.setBidName(rs.getString("BidName"));
				bidApplyListVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidApplyListVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				bidApplyListVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				bidApplyListVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				bidApplyListVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidApplyListVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidApplyListVO.setUpcNum(rs.getString("UpcNum"));
				bidApplyListVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidApplyListVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidApplyListVO.setApplyState(rs.getInt("ApplyState"));
				list.add(bidApplyListVO);
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
	public BidApplyListVO findByPrimaryKey(Integer bidApplyListNo) {
		BidApplyListVO bidApplyListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, bidApplyListNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidApplyListVO = new BidApplyListVO();
				bidApplyListVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidApplyListVO.setMemNo(rs.getInt("MemNo"));
				bidApplyListVO.setBidName(rs.getString("BidName"));
				bidApplyListVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidApplyListVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				bidApplyListVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				bidApplyListVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				bidApplyListVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidApplyListVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidApplyListVO.setUpcNum(rs.getString("UpcNum"));
				bidApplyListVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidApplyListVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidApplyListVO.setApplyState(rs.getInt("ApplyState"));
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
		return bidApplyListVO;
	}

	@Override
	public List<BidApplyListVO> findByMemNo(Integer memNo) {
		BidApplyListVO bidApplyListVO = null;
		List<BidApplyListVO> list = new ArrayList<BidApplyListVO>();
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
				bidApplyListVO = new BidApplyListVO();
				bidApplyListVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidApplyListVO.setMemNo(rs.getInt("MemNo"));
				bidApplyListVO.setBidName(rs.getString("BidName"));
				bidApplyListVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidApplyListVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				bidApplyListVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				bidApplyListVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				bidApplyListVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidApplyListVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidApplyListVO.setUpcNum(rs.getString("UpcNum"));
				bidApplyListVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidApplyListVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidApplyListVO.setApplyState(rs.getInt("ApplyState"));
				list.add(bidApplyListVO);
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
		BidApplyListJDBCDAO dao = new BidApplyListJDBCDAO();

		// insert方法
//		BidApplyListVO bidApplyListVO = new BidApplyListVO();
//		bidApplyListVO.setMemNo(11003);
//		bidApplyListVO.setBidName("測試");
//		bidApplyListVO.setBidProdDescription("測試測試");
//		bidApplyListVO.setGameCompanyNo(61007);
//		bidApplyListVO.setGameTypeNo(63010);
//		bidApplyListVO.setGamePlatformNo(64003);
//		bidApplyListVO.setInitialPrice(700);
//		bidApplyListVO.setBidPriceIncrement(50);
//		bidApplyListVO.setUpcNum("2222222");
//		
//		Calendar cal = new GregorianCalendar(2022, 3, 9, 20, 0, 0);
//		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
//		bidApplyListVO.setBidLaunchedTime(timestamp);
//		
//		Calendar cal2 = new GregorianCalendar(2022, 3, 10, 20, 0, 0);
//		Timestamp timestamp2 = new Timestamp(cal2.getTime().getTime());
//		bidApplyListVO.setBidSoldTime(timestamp2);
//		bidApplyListVO.setApplyState(0);
//		
//		dao.insert(bidApplyListVO);

		// update方法
//		BidApplyListVO bidApplyListVO2 = new BidApplyListVO();
//		bidApplyListVO2.setBidApplyListNo(34009);
//		bidApplyListVO2.setMemNo(11003);
//		bidApplyListVO2.setBidName("測試修改");
//		bidApplyListVO2.setBidProdDescription("測試測試修改");
//		bidApplyListVO2.setGameCompanyNo(61006);
//		bidApplyListVO2.setGameTypeNo(63009);
//		bidApplyListVO2.setGamePlatformNo(64002);
//		bidApplyListVO2.setInitialPrice(650);
//		bidApplyListVO2.setBidPriceIncrement(80);
//		bidApplyListVO2.setUpcNum("3333333");
//		
//		Calendar cal3 = new GregorianCalendar(2022, 3, 9, 8, 0, 0);
//		Timestamp timestamp3 = new Timestamp(cal3.getTime().getTime());
//		bidApplyListVO2.setBidLaunchedTime(timestamp3);
//		
//		Calendar cal4 = new GregorianCalendar(2022, 3, 10, 8, 0, 0);
//		Timestamp timestamp4 = new Timestamp(cal4.getTime().getTime());
//		bidApplyListVO2.setBidSoldTime(timestamp4);
//		bidApplyListVO2.setApplyState(0);
//		
//		dao.update(bidApplyListVO2);

		// delete方法
//		dao.delete(34009);

		// getAll方法
//		List<BidApplyListVO> list = dao.getAll();
//		for (BidApplyListVO bidApplyListVO : list) {
//			System.out.print(bidApplyListVO.getBidApplyListNo() + " , ");
//			System.out.print(bidApplyListVO.getMemNo() + " , ");
//			System.out.print(bidApplyListVO.getBidName() + " , ");
//			System.out.print(bidApplyListVO.getBidProdDescription() + " , ");
//			System.out.print(bidApplyListVO.getGameCompanyNo() + " , ");
//			System.out.print(bidApplyListVO.getGameTypeNo() + " , ");
//			System.out.print(bidApplyListVO.getGamePlatformNo() + " , ");
//			System.out.print(bidApplyListVO.getInitialPrice() + " , ");
//			System.out.print(bidApplyListVO.getBidPriceIncrement() + " , ");
//			System.out.print(bidApplyListVO.getUpcNum() + " , ");
//			System.out.print(bidApplyListVO.getBidLaunchedTime() + " , ");
//			System.out.print(bidApplyListVO.getBidSoldTime() + " , ");
//			System.out.println(bidApplyListVO.getBidApplyListNo() + " , ");
//			System.out.println("-------------------");
//		}

		// findByPrimaryKey方法
//		BidApplyListVO bidApplyListVO3 = dao.findByPrimaryKey(34001);
//		System.out.print(bidApplyListVO3.getBidApplyListNo() + " , ");
//		System.out.print(bidApplyListVO3.getMemNo() + " , ");
//		System.out.print(bidApplyListVO3.getBidName() + " , ");
//		System.out.print(bidApplyListVO3.getBidProdDescription() + " , ");
//		System.out.print(bidApplyListVO3.getGameCompanyNo() + " , ");
//		System.out.print(bidApplyListVO3.getGameTypeNo() + " , ");
//		System.out.print(bidApplyListVO3.getGamePlatformNo() + " , ");
//		System.out.print(bidApplyListVO3.getInitialPrice() + " , ");
//		System.out.print(bidApplyListVO3.getBidPriceIncrement() + " , ");
//		System.out.print(bidApplyListVO3.getUpcNum() + " , ");
//		System.out.print(bidApplyListVO3.getBidLaunchedTime() + " , ");
//		System.out.print(bidApplyListVO3.getBidSoldTime() + " , ");
//		System.out.println(bidApplyListVO3.getBidApplyListNo() + " , ");
//		System.out.println("-------------------");

		// findByMemNo方法
		List<BidApplyListVO> list2 = dao.findByMemNo(11001);
		for (BidApplyListVO bidApplyListVO : list2) {
			System.out.print(bidApplyListVO.getBidApplyListNo() + " , ");
			System.out.print(bidApplyListVO.getMemNo() + " , ");
			System.out.print(bidApplyListVO.getBidName() + " , ");
			System.out.print(bidApplyListVO.getBidProdDescription() + " , ");
			System.out.print(bidApplyListVO.getGameCompanyNo() + " , ");
			System.out.print(bidApplyListVO.getGameTypeNo() + " , ");
			System.out.print(bidApplyListVO.getGamePlatformNo() + " , ");
			System.out.print(bidApplyListVO.getInitialPrice() + " , ");
			System.out.print(bidApplyListVO.getBidPriceIncrement() + " , ");
			System.out.print(bidApplyListVO.getUpcNum() + " , ");
			System.out.print(bidApplyListVO.getBidLaunchedTime() + " , ");
			System.out.print(bidApplyListVO.getBidSoldTime() + " , ");
			System.out.println(bidApplyListVO.getBidApplyListNo() + " , ");
			System.out.println("-------------------");
		}
	}

	@Override
	public void updateApplyState(BidApplyListVO bidApplyListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE_ONE_STMT_APPLYSTATE);

			pstmt.setInt(1, bidApplyListVO.getApplyState());
			pstmt.setInt(2, bidApplyListVO.getBidApplyListNo());

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

}
