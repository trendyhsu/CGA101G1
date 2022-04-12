package com.bidpic.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.DButil;

public class BidPicJDBCDAO extends DButil implements BidPicDAO_interface {

	// SQL語句
	private static final String INSERT_STMT = "INSERT INTO bidpic (BidProductNo, BidProdPicContent) VALUES (?, ?)";

	private static final String UPDATE_STMT = "UPDATE bidpic SET BidProductNo = ?, BidProdPicContent = ? WHERE BidProdPicNo = ?";

	private static final String DELETE_STMT = "DELETE FROM bidpic WHERE BidProdPicNo = ?";
	
	private static final String GET_ALL_STMT = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic";

	private static final String GET_ONE_STMT = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProdPicNo = ?";
	// 使用 BidProductNo 取得所有 BidProductNo 的照片
	private static final String GET_ALL_STMT_BIDPRODUCTNO = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProductNo = ?";
	// 使用 BidProductNo 取得封面(第一張) BidProductNo 的照片
	private static final String GET_ONE_STMT_BIDPRODUCTNO = "SELECT BidProdPicNo, BidProductNo, BidProdPicContent FROM bidpic WHERE BidProductNo = ? limit 1";
	
	@Override
	public void insert(BidPicVO bidPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidPicVO.getBidProductNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());

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
	public void update(BidPicVO bidPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, bidPicVO.getBidProductNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());
			pstmt.setInt(3, bidPicVO.getBidProdPicNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer bidProdPicNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, bidProdPicNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<BidPicVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BidPicVO> list = new ArrayList<BidPicVO>();
		BidPicVO bidPicVO = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
				list.add(bidPicVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public BidPicVO findByPrimaryKey(Integer bidProdPicNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BidPicVO bidPicVO = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, bidProdPicNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return bidPicVO;
	}

	@Override
	public List<BidPicVO> findByBidProductNo(Integer bidProductNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BidPicVO bidPicVO = null;
		List<BidPicVO> list = new ArrayList<BidPicVO>();

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODUCTNO);
			pstmt.setInt(1, bidProductNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
				list.add(bidPicVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public BidPicVO findFirstPicByBidProductNo(Integer bidProductNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BidPicVO bidPicVO = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDPRODUCTNO);
			pstmt.setInt(1, bidProductNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("BidProdPicNo"));
				bidPicVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("BidProdPicContent"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return bidPicVO;
	}

	public static void main(String[] args) {
		// 測試建立一個dao物件
		BidPicJDBCDAO dao = new BidPicJDBCDAO();
		
		// 測試dao insert方法
		// 測試建立一個bidpic物件
//		BidPicVO bidPicVO = new BidPicVO();
//		bidPicVO.setBidProdPicNo(32010);
//		bidPicVO.setBidProductNo(31003);
//		FileInputStream in;
//		try {
//			in = new FileInputStream("test.jpg");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			bidPicVO.setBidProdPicContent(b);
//			in.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		dao.insert(bidPicVO);
		
		// 測試dao update方法
//		BidPicVO bidPicVO2 = new BidPicVO();
//		bidPicVO2.setBidProdPicNo(32010);
//		bidPicVO2.setBidProductNo(31002);
//		FileInputStream in2;
//		try {
//			in2 = new FileInputStream("test2.jpg");
//			byte[] b = new byte[in2.available()];
//			in2.read(b);
//			bidPicVO2.setBidProdPicContent(b);
//			in2.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		dao.update(bidPicVO2);
		
		// 測試dao delete方法
//		dao.delete(32010);
		
		// 測試dao findByPrimaryKey方法
		BidPicVO bidPicVO3 = dao.findByPrimaryKey(32001);
		System.out.print(bidPicVO3.getBidProdPicNo() + " , ");
		System.out.println(bidPicVO3.getBidProductNo());
		System.out.println("---------------------");
		
		// 測試dao getall方法
		List<BidPicVO> list = dao.getAll();
		for(BidPicVO bidPicVO:list) {
			System.out.print(bidPicVO.getBidProdPicNo() + " , ");
			System.out.println(bidPicVO.getBidProductNo());
			System.out.println("---------------------");
		}
		
		// 測試dao findFirstPicByBidProductNo
		BidPicVO bidPicVO4 = dao.findFirstPicByBidProductNo(31003);
		System.out.print(bidPicVO4.getBidProdPicNo() + " , ");
		System.out.println(bidPicVO4.getBidProductNo());
		System.out.println("---------------------");
		
		// 測試dao findByBidProductNo
		List<BidPicVO> list2 = dao.findByBidProductNo(31003);
		for(BidPicVO bidPicVO:list2) {
		System.out.print(bidPicVO.getBidProdPicNo() + " , ");
		System.out.println(bidPicVO.getBidProductNo());
		System.out.println("---------------------");
		}
	}

}
