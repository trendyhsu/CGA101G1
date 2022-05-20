package com.bidproduct.model;

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
import java.util.Map;

import com.bidrecord.model.BidRecordVO;
import com.connection.model.ConnectionDAO;
import com.mysql.cj.xdevapi.Statement;
import com.utils.CompositeQuery_BidProduct;
import com.utils.DButil;

public class BidProductJDBCDAO extends DButil implements BidProductDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO bidproduct (BidApplyListNo, ProductNo, BidName, BidProdDescription, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidPriceIncrement, OrderState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String INSERT_NO_PRODUCT_STMT = "INSERT INTO bidproduct (BidApplyListNo, BidName, BidProdDescription, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidPriceIncrement, OrderState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE bidproduct SET BidApplyListNo=?, ProductNo=?, BidName=?, BidProdDescription=?, BuyerNo=?, SellerNo=?, InitialPrice=?, BidState=?, BidLaunchedTime=?, BidSoldTime=?, BidWinnerPrice=?, BidPriceIncrement=?, OrderState=?, ReceiverName=?, ReceiverAddress=?, ReceiverPhone=? WHERE BidProductNo = ?";

	private static final String UPDATE_NO_PRODUCT_STMT = "UPDATE bidproduct SET BidApplyListNo=?, BidName=?, BidProdDescription=?, BuyerNo=?, SellerNo=?, InitialPrice=?, BidState=?, BidLaunchedTime=?, BidSoldTime=?, BidWinnerPrice=?, BidPriceIncrement=?, OrderState=?, ReceiverName=?, ReceiverAddress=?, ReceiverPhone=? WHERE BidProductNo = ?";

	private static final String DELETE_STMT = "DELETE FROM bidproduct WHERE BidProductNo = ?";

	private static final String GET_ALL_STMT = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct";

	private static final String GET_ONE_STMT = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidProductNo = ?";
	// 使用 bidApplyListNo 取得 對應商品
	private static final String GET_ONE_STMT_APPLYLIST = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidApplyListNo = ?";
	// 使用 buyerNo 查詢所有 buyerNo 得標商品
	private static final String GET_ALL_STMT_BUYERNO = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BuyerNo = ?";
	// 使用 sellerNo 查詢所有 buyerNo 得標商品
	private static final String GET_ALL_STMT_SELLERNO = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE SellerNo = ?";
	// 使用 bidName 查詢所有 符合 bidName 的商品
	private static final String GET_ALL_STMT_BIDNAME = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidName LIKE ?  OR BidProdDescription LIKE ?";
	// 查詢競標商品 BidState 等於 0 (競標中) 而且 截標時間小於目前時間 ( 為了改成流標 )
	private static final String GET_ALL_STMT_BIDSTATE_NEED_CHANGE = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidState = 0 AND BidSoldTime <= NOW()";
	// 查詢競標商品 BidState 等於 1 (結帳結束進入訂單處理中)
	private static final String GET_ALL_STMT_BIDSTATE = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidState = 1";
	// 更新競標狀態
	private static final String UPDATE_BIDSTATE = "UPDATE bidproduct SET BidState = ? WHERE BidProductNo = ?";
	// 更新競標狀態(有買家 有出價者)
	private static final String UPDATE_BIDSTATE_HAVE_BUYER = "UPDATE bidproduct SET BuyerNo = ?, BidState = ?, BidWinnerPrice = ? WHERE BidProductNo = ?";
	// 更改收件資訊與商品狀態
	private static final String UPDATE_RECEIVER_AND_PRODSTATE = "UPDATE bidproduct SET OrderState=?, ReceiverName = ?, ReceiverAddress = ?, ReceiverPhone = ? WHERE BidProductNo = ?";
	// 更改orderState用來更新取回以及重新上架的狀態
	private static final String UPDATE_ORDERSTATE_FOR_GETBACK_AND_RELIST = "UPDATE bidproduct SET OrderState=? WHERE BidProductNo = ?";
	// 查詢已截標 30分鐘後沒有付款 將 BidState 改為 棄標
	private static final String GET_ALL_STMT_BIDSTATE_NEED_CHANGE_TO_THREE = "SELECT BidProductNo, BidApplyListNo, ProductNo, BidName, BidProdDescription, BuyerNo, SellerNo, InitialPrice, BidState, BidLaunchedTime, BidSoldTime, BidWinnerPrice, BidPriceIncrement, OrderState, ReceiverName, ReceiverAddress, ReceiverPhone FROM bidproduct WHERE BidState = 1 AND OrderState = 0 AND DATE_ADD(BidSoldTime, INTERVAL 30 MINUTE) <= NOW()";
	// 後臺更新競標資訊
	private static final String UPDATE_ONE_STMT_BY_BACKEND = "UPDATE bidproduct SET BidApplyListNo=?, ProductNo=?, BidName=?, BidProdDescription=?, InitialPrice=?, BidState=?, BidLaunchedTime=?, BidSoldTime=?, BidPriceIncrement=?, OrderState=?, ReceiverName=?, ReceiverAddress=?, ReceiverPhone=? WHERE BidProductNo = ?";
	// 後臺更新競標資訊 沒有一手商品
	private static final String UPDATE_ONE_STMT_BY_BACKEND_NO_PRODUCT = "UPDATE bidproduct SET BidApplyListNo=?, BidName=?, BidProdDescription=?, InitialPrice=?, BidState=?, BidLaunchedTime=?, BidSoldTime=?, BidPriceIncrement=?, OrderState=?, ReceiverName=?, ReceiverAddress=?, ReceiverPhone=? WHERE BidProductNo = ?";

	
	@Override
	public Integer insert(BidProductVO bidProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String columns[] = { "BidProductNo" };
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT,columns);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setInt(2, bidProductVO.getProductNo());
			pstmt.setString(3, bidProductVO.getBidName());
			pstmt.setString(4, bidProductVO.getBidProdDescription());
			pstmt.setInt(5, bidProductVO.getSellerNo());
			pstmt.setInt(6, bidProductVO.getInitialPrice());
			pstmt.setInt(7, bidProductVO.getBidState());
			pstmt.setTimestamp(8, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(9, bidProductVO.getBidSoldTime());
			pstmt.setInt(10, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(11, bidProductVO.getOrderState());

			pstmt.executeUpdate();
			Integer nextBidProductNo = null;
			// 印出現在新增的競標商品編號 用於新增該圖片
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextBidProductNo = rs.getInt(1);
				System.out.println("自動增加欄位號碼為: " + nextBidProductNo);
			}
			rs.close();
			return nextBidProductNo;
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
	public Integer insertWithoutProduct(BidProductVO bidProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String columns[] = { "BidProductNo" };
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(INSERT_NO_PRODUCT_STMT,columns);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setString(2, bidProductVO.getBidName());
			pstmt.setString(3, bidProductVO.getBidProdDescription());
			pstmt.setInt(4, bidProductVO.getSellerNo());
			pstmt.setInt(5, bidProductVO.getInitialPrice());
			pstmt.setInt(6, bidProductVO.getBidState());
			pstmt.setTimestamp(7, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(8, bidProductVO.getBidSoldTime());
			pstmt.setInt(9, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(10, bidProductVO.getOrderState());

			pstmt.executeUpdate();
			Integer nextBidProductNo = null;
			// 印出現在新增的競標商品編號 用於新增該圖片
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextBidProductNo = rs.getInt(1);
				System.out.println("自動增加欄位號碼為: " + nextBidProductNo);
			}
			rs.close();
			return nextBidProductNo;
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
	public void update(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setInt(2, bidProductVO.getProductNo());
			pstmt.setString(3, bidProductVO.getBidName());
			pstmt.setString(4, bidProductVO.getBidProdDescription());
			pstmt.setInt(5, bidProductVO.getBuyerNo());
			pstmt.setInt(6, bidProductVO.getSellerNo());
			pstmt.setInt(7, bidProductVO.getInitialPrice());
			pstmt.setInt(8, bidProductVO.getBidState());
			pstmt.setTimestamp(9, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(10, bidProductVO.getBidSoldTime());
			pstmt.setInt(11, bidProductVO.getBidWinnerPrice());
			pstmt.setInt(12, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(13, bidProductVO.getOrderState());
			pstmt.setString(14, bidProductVO.getReceiverName());
			pstmt.setString(15, bidProductVO.getReceiverAddress());
			pstmt.setString(16, bidProductVO.getReceiverPhone());
			pstmt.setInt(17, bidProductVO.getBidProductNo());

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
	public void updateWithoutProduct(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_NO_PRODUCT_STMT);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setString(2, bidProductVO.getBidName());
			pstmt.setString(3, bidProductVO.getBidProdDescription());
			pstmt.setInt(4, bidProductVO.getBuyerNo());
			pstmt.setInt(5, bidProductVO.getSellerNo());
			pstmt.setInt(6, bidProductVO.getInitialPrice());
			pstmt.setInt(7, bidProductVO.getBidState());
			pstmt.setTimestamp(8, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(9, bidProductVO.getBidSoldTime());
			pstmt.setInt(10, bidProductVO.getBidWinnerPrice());
			pstmt.setInt(11, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(12, bidProductVO.getOrderState());
			pstmt.setString(13, bidProductVO.getReceiverName());
			pstmt.setString(14, bidProductVO.getReceiverAddress());
			pstmt.setString(15, bidProductVO.getReceiverPhone());
			pstmt.setInt(16, bidProductVO.getBidProductNo());

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
	public void delete(Integer bidProductNO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, bidProductNO);

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
	public List<BidProductVO> getAll() {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public BidProductVO findByPrimaryKey(Integer bidProductNo) {
		BidProductVO bidProductVO = new BidProductVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, bidProductNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
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
		return bidProductVO;
	}

	@Override
	public BidProductVO findByBidApplyListNo(Integer bidApplyListNo) {
		BidProductVO bidProductVO = new BidProductVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
	
			pstmt = con.prepareStatement(GET_ONE_STMT_APPLYLIST);
			pstmt.setInt(1, bidApplyListNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
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
		return bidProductVO;
	}

	@Override
	public List<BidProductVO> findByBuyerNo(Integer buyerNo) {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = new BidProductVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ALL_STMT_BUYERNO);
			pstmt.setInt(1, buyerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public List<BidProductVO> findBySellerNo(Integer sellerNo) {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = new BidProductVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ALL_STMT_SELLERNO);
			pstmt.setInt(1, sellerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public List<BidProductVO> findByBidName(String bidName) {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(GET_ALL_STMT_BIDNAME);

			pstmt.setString(1, "%" + bidName + "%");
			pstmt.setString(2, "%" + bidName + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public List<BidProductVO> findByBidStateAndSoldTime() {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDSTATE_NEED_CHANGE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public void updateBidState(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_BIDSTATE);

			pstmt.setInt(1, bidProductVO.getBidState());
			pstmt.setInt(2, bidProductVO.getBidProductNo());

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
	public void updateBidStateHaveBuyer(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_BIDSTATE_HAVE_BUYER);

			pstmt.setInt(1, bidProductVO.getBuyerNo());
			pstmt.setInt(2, bidProductVO.getBidState());
			pstmt.setInt(3, bidProductVO.getBidWinnerPrice());
			pstmt.setInt(4, bidProductVO.getBidProductNo());

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
	public void updateReceiverAndOrderState(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_RECEIVER_AND_PRODSTATE);

			pstmt.setInt(1, bidProductVO.getOrderState());
			pstmt.setString(2, bidProductVO.getReceiverName());
			pstmt.setString(3, bidProductVO.getReceiverAddress());
			pstmt.setString(4, bidProductVO.getReceiverPhone());
			pstmt.setInt(5, bidProductVO.getBidProductNo());

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
	public List<BidProductVO> findByBidStateAndOrderState() {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDSTATE_NEED_CHANGE_TO_THREE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public void updateByBackend(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
	
			pstmt = con.prepareStatement(UPDATE_ONE_STMT_BY_BACKEND);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setInt(2, bidProductVO.getProductNo());
			pstmt.setString(3, bidProductVO.getBidName());
			pstmt.setString(4, bidProductVO.getBidProdDescription());
			pstmt.setInt(5, bidProductVO.getInitialPrice());
			pstmt.setInt(6, bidProductVO.getBidState());
			pstmt.setTimestamp(7, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(8, bidProductVO.getBidSoldTime());
			pstmt.setInt(9, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(10, bidProductVO.getOrderState());
			pstmt.setString(11, bidProductVO.getReceiverName());
			pstmt.setString(12, bidProductVO.getReceiverAddress());
			pstmt.setString(13, bidProductVO.getReceiverPhone());
			pstmt.setInt(14, bidProductVO.getBidProductNo());
	
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
	public void updateByBackendWithoutProduct(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
	
			pstmt = con.prepareStatement(UPDATE_ONE_STMT_BY_BACKEND_NO_PRODUCT);
			pstmt.setInt(1, bidProductVO.getBidApplyListNo());
			pstmt.setString(2, bidProductVO.getBidName());
			pstmt.setString(3, bidProductVO.getBidProdDescription());
			pstmt.setInt(4, bidProductVO.getInitialPrice());
			pstmt.setInt(5, bidProductVO.getBidState());
			pstmt.setTimestamp(6, bidProductVO.getBidLaunchedTime());
			pstmt.setTimestamp(7, bidProductVO.getBidSoldTime());
			pstmt.setInt(8, bidProductVO.getBidPriceIncrement());
			pstmt.setInt(9, bidProductVO.getOrderState());
			pstmt.setString(10, bidProductVO.getReceiverName());
			pstmt.setString(11, bidProductVO.getReceiverAddress());
			pstmt.setString(12, bidProductVO.getReceiverPhone());
			pstmt.setInt(13, bidProductVO.getBidProductNo());
	
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
	public void updateOrderStateGetbackAndRelist(BidProductVO bidProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());

			pstmt = con.prepareStatement(UPDATE_ORDERSTATE_FOR_GETBACK_AND_RELIST);

			pstmt.setInt(1, bidProductVO.getOrderState());
			pstmt.setInt(2, bidProductVO.getBidProductNo());

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
	public List<BidProductVO> findByBidState() {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDSTATE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
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
	public List<BidProductVO> getAll(Map<String, String[]> map) {
		List<BidProductVO> list = new ArrayList<BidProductVO>();
		BidProductVO bidProductVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(getDriver());
			con = DriverManager.getConnection(getUrl(), getUserid(), getPassword());
			String finalSQL = "select * from bidproduct "
		          + CompositeQuery_BidProduct.get_WhereCondition(map)
		          + "order by BidProductNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				bidProductVO = new BidProductVO();
				bidProductVO.setBidProductNo(rs.getInt("BidProductNo"));
				bidProductVO.setBidApplyListNo(rs.getInt("BidApplyListNo"));
				bidProductVO.setProductNo(rs.getInt("ProductNo"));
				bidProductVO.setBidName(rs.getString("BidName"));
				bidProductVO.setBidProdDescription(rs.getString("BidProdDescription"));
				bidProductVO.setBuyerNo(rs.getInt("BuyerNo"));
				bidProductVO.setSellerNo(rs.getInt("SellerNo"));
				bidProductVO.setInitialPrice(rs.getInt("InitialPrice"));
				bidProductVO.setBidState(rs.getInt("BidState"));
				bidProductVO.setBidLaunchedTime(rs.getTimestamp("BidLaunchedTime"));
				bidProductVO.setBidSoldTime(rs.getTimestamp("BidSoldTime"));
				bidProductVO.setBidWinnerPrice(rs.getInt("BidWinnerPrice"));
				bidProductVO.setBidPriceIncrement(rs.getInt("BidPriceIncrement"));
				bidProductVO.setOrderState(rs.getInt("OrderState"));
				bidProductVO.setReceiverName(rs.getString("ReceiverName"));
				bidProductVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				bidProductVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(bidProductVO);
			}
	
			// Handle any SQL errors
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
			BidProductJDBCDAO dao = new BidProductJDBCDAO();
	
			// insert方法
	//		BidProductVO bidProductVO = new BidProductVO();
	//		bidProductVO.setBidApplyListNo(34004);
	//		bidProductVO.setProductNo(21001);
	//		bidProductVO.setBidName("測試");
	//		bidProductVO.setBidProdDescription("測試測試");
	//		bidProductVO.setBuyerNo(11002);
	//		bidProductVO.setSellerNo(11001);
	//		bidProductVO.setInitialPrice(600);
	//		bidProductVO.setBidState(1);
	//		Calendar cal = new GregorianCalendar(2022, 3, 8, 20, 0, 0);
	//		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
	//		bidProductVO.setBidLaunchedTime(timestamp);
	//		Calendar cal2 = new GregorianCalendar(2022, 3, 9, 10, 0, 0);
	//		Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
	//		bidProductVO.setBidSoldTime(timestamp2);
	//		bidProductVO.setBidWinnerPrice(700);
	//		bidProductVO.setBidPriceIncrement(50);
	//		bidProductVO.setOrderState(1);
	//		bidProductVO.setReceiverName("宇陽");
	//		bidProductVO.setReceiverAddress("新北市新莊區");
	//		bidProductVO.setReceiverPhone("0912345678");
	//		
	//		dao.insert(bidProductVO);
	
			// update方法
	//		BidProductVO bidProductVO2 = new BidProductVO();
	//		bidProductVO2.setBidProductNo(31004);
	//		bidProductVO2.setBidApplyListNo(34004);
	//		bidProductVO2.setProductNo(21001);
	//		bidProductVO2.setBidName("測試更改");
	//		bidProductVO2.setBidProdDescription("測試測試更改");
	//		bidProductVO2.setBuyerNo(11002);
	//		bidProductVO2.setSellerNo(11001);
	//		bidProductVO2.setInitialPrice(600);
	//		bidProductVO2.setBidState(1);
	//		Calendar cal = new GregorianCalendar(2022, 3, 8, 22, 0, 0);
	//		Timestamp timestamp = new Timestamp(cal.getTime().getTime());
	//		bidProductVO2.setBidLaunchedTime(timestamp);
	//		Calendar cal2 = new GregorianCalendar(2022, 3, 9, 12, 0, 0);
	//		Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());
	//		bidProductVO2.setBidSoldTime(timestamp2);
	//		bidProductVO2.setBidWinnerPrice(700);
	//		bidProductVO2.setBidPriceIncrement(50);
	//		bidProductVO2.setOrderState(1);
	//		bidProductVO2.setReceiverName("宇陽改起來");
	//		bidProductVO2.setReceiverAddress("新北市");
	//		bidProductVO2.setReceiverPhone("0987654321");
	//		
	//		dao.update(bidProductVO2);
	
			// delete方法
	//		dao.delete(31004);
	
			// getAll方法
	//		List<BidProductVO> list = dao.getAll();
	//		for (BidProductVO bidProductVO : list) {
	//			System.out.print(bidProductVO.getBidProductNo() + " , "); 
	//			System.out.print(bidProductVO.getBidApplyListNo() + " , "); 
	//			System.out.print(bidProductVO.getProductNo() + " , "); 
	//			System.out.print(bidProductVO.getBidName() + " , "); 
	//			System.out.print(bidProductVO.getBidProdDescription() + " , "); 
	//			System.out.print(bidProductVO.getBuyerNo() + " , "); 
	//			System.out.print(bidProductVO.getSellerNo() + " , "); 
	//			System.out.print(bidProductVO.getInitialPrice() + " , "); 
	//			System.out.print(bidProductVO.getBidState() + " , "); 
	//			System.out.print(bidProductVO.getBidLaunchedTime() + " , "); 
	//			System.out.print(bidProductVO.getBidSoldTime() + " , "); 
	//			System.out.print(bidProductVO.getBidWinnerPrice() + " , "); 
	//			System.out.print(bidProductVO.getBidPriceIncrement() + " , "); 
	//			System.out.print(bidProductVO.getOrderState() + " , "); 
	//			System.out.print(bidProductVO.getReceiverName() + " , "); 
	//			System.out.print(bidProductVO.getReceiverAddress() + " , "); 
	//			System.out.println(bidProductVO.getReceiverPhone() + " , "); 
	//			System.out.println("------------------------");
	//		}
	
			// findByPrimaryKey方法
	//		BidProductVO bidProductVO3 = dao.findByPrimaryKey(31001);
	//		System.out.print(bidProductVO3.getBidProductNo() + " , "); 
	//		System.out.print(bidProductVO3.getBidApplyListNo() + " , "); 
	//		System.out.print(bidProductVO3.getProductNo() + " , "); 
	//		System.out.print(bidProductVO3.getBidName() + " , "); 
	//		System.out.print(bidProductVO3.getBidProdDescription() + " , "); 
	//		System.out.print(bidProductVO3.getBuyerNo() + " , "); 
	//		System.out.print(bidProductVO3.getSellerNo() + " , "); 
	//		System.out.print(bidProductVO3.getInitialPrice() + " , "); 
	//		System.out.print(bidProductVO3.getBidState() + " , "); 
	//		System.out.print(bidProductVO3.getBidLaunchedTime() + " , "); 
	//		System.out.print(bidProductVO3.getBidSoldTime() + " , "); 
	//		System.out.print(bidProductVO3.getBidWinnerPrice() + " , "); 
	//		System.out.print(bidProductVO3.getBidPriceIncrement() + " , "); 
	//		System.out.print(bidProductVO3.getOrderState() + " , "); 
	//		System.out.print(bidProductVO3.getReceiverName() + " , "); 
	//		System.out.print(bidProductVO3.getReceiverAddress() + " , "); 
	//		System.out.println(bidProductVO3.getReceiverPhone() + " , "); 
	//		System.out.println("------------------------");
	
			// findByBuyerNo方法
	//		List<BidProductVO> list2 = dao.findByBuyerNo(11002);
	//		for (BidProductVO bidProductVO : list2) {
	//			System.out.print(bidProductVO.getBidProductNo() + " , ");
	//			System.out.print(bidProductVO.getBidApplyListNo() + " , ");
	//			System.out.print(bidProductVO.getProductNo() + " , ");
	//			System.out.print(bidProductVO.getBidName() + " , ");
	//			System.out.print(bidProductVO.getBidProdDescription() + " , ");
	//			System.out.print(bidProductVO.getBuyerNo() + " , ");
	//			System.out.print(bidProductVO.getSellerNo() + " , ");
	//			System.out.print(bidProductVO.getInitialPrice() + " , ");
	//			System.out.print(bidProductVO.getBidState() + " , ");
	//			System.out.print(bidProductVO.getBidLaunchedTime() + " , ");
	//			System.out.print(bidProductVO.getBidSoldTime() + " , ");
	//			System.out.print(bidProductVO.getBidWinnerPrice() + " , ");
	//			System.out.print(bidProductVO.getBidPriceIncrement() + " , ");
	//			System.out.print(bidProductVO.getOrderState() + " , ");
	//			System.out.print(bidProductVO.getReceiverName() + " , ");
	//			System.out.print(bidProductVO.getReceiverAddress() + " , ");
	//			System.out.println(bidProductVO.getReceiverPhone() + " , ");
	//			System.out.println("------------------------");
	//		}
	
			// findByBidName方法
			List<BidProductVO> list3 = dao.findByBidName("卡");
			for (BidProductVO bidProductVO : list3) {
				System.out.print(bidProductVO.getBidProductNo() + " , ");
				System.out.print(bidProductVO.getBidApplyListNo() + " , ");
				System.out.print(bidProductVO.getProductNo() + " , ");
				System.out.print(bidProductVO.getBidName() + " , ");
				System.out.print(bidProductVO.getBidProdDescription() + " , ");
				System.out.print(bidProductVO.getBuyerNo() + " , ");
				System.out.print(bidProductVO.getSellerNo() + " , ");
				System.out.print(bidProductVO.getInitialPrice() + " , ");
				System.out.print(bidProductVO.getBidState() + " , ");
				System.out.print(bidProductVO.getBidLaunchedTime() + " , ");
				System.out.print(bidProductVO.getBidSoldTime() + " , ");
				System.out.print(bidProductVO.getBidWinnerPrice() + " , ");
				System.out.print(bidProductVO.getBidPriceIncrement() + " , ");
				System.out.print(bidProductVO.getOrderState() + " , ");
				System.out.print(bidProductVO.getReceiverName() + " , ");
				System.out.print(bidProductVO.getReceiverAddress() + " , ");
				System.out.println(bidProductVO.getReceiverPhone() + " , ");
				System.out.println("------------------------");
			}
		}


}
