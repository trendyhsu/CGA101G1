package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements OrderDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";

//                                  新增訂單-沒使用優惠券	
		private static final String NewOrderWithoutCoupon = 
			"INSERT INTO cga101g1.order (MemNo,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,ReceiverName,ReceiverAddress,ReceiverPhone) VALUES (?,?,0,?,?,?,?,?);";
	                                  //    1        2                          3           4            5           6           7                          

//                                  新增訂單-使用優惠券	
		private static final String NewOrderWithCoupon = 
				"INSERT INTO cga101g1.order (MemNo,MemCouponNo,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,ReceiverName,ReceiverAddress,ReceiverPhone) VALUES (?,?,?,0,?,?,?,?,?)";
		                                  //    1        2         3                            4            5           6           7             8                   
		
		
		
		//查某個人最新的訂單
		private static final String FindNewetOrderByMem=
				"Select max(orderNo),MemNo FROM cga101g1.order where MemNo = ? ;";
		              //     1          2                                   

		
		
		private static final String FindByMemNo = 
//				"SELECT OrderNo,MemCouponNo,TranTime,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where MemNo = ? order by TranTime desc";
				"SELECT OrderNo,MemCouponNo,TranTime,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where MemNo = ? order by TranTime desc";
//		                  1           2            3             4         5              6          7               8         9              10             11                                12

		
		//		                    訂單修改
		private static final String UPDATE = 
				"UPDATE cga101g1.order SET OrderTotalPrice = ?, OrderState = ?, PickupMethod = ?, ShippingFee = ?, ReceiverName = ?, ReceiverAddress = ? ,ReceiverPhone= ? WHERE OrderNo = ?;";
		//                                     1                   2               3             4                      5                 6                7                       8             
		

		private static final String FindByOrderNo = 
				"SELECT MemNo,MemCouponNo,TranTime,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderNo = ? order by TranTime desc";
//		                  1       2           3           4             5           6          7            8            9            10               11                             13

		private static final String FindByTrashOrderNo = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderState = 4 order by TranTime desc";
//	                  1       2       3           4             5           6          7            8            9            10               11                                     

		
		private static final String FindByFinishedOrderNo = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderState = 2 order by TranTime desc";
//	                     1       2       3           4             5           6          7            8            9            10               11                                    

		
		private static final String FindByReturnedOrderNo = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderState = 3 order by TranTime desc";
//	                  1       2       3           4             5           6          7            8            9            10               11                                     

		
		private static final String FindByUnPulledOrderNo = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderState = 0 order by TranTime desc";
//	                  1       2       3           4             5           6          7            8            9            10               11                                   

	
		private static final String FindByPulledOrderNo = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order where OrderState = 1 order by TranTime desc";
//	                  1       2       3           4             5           6          7            8            9            10               11          12                             


		private static final String GET_AllOrder = 
				"SELECT OrderNo,MemNo,MemCouponNo,TranTime,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,TrackingNum,ReceiverName,ReceiverAddress,ReceiverPhone FROM cga101g1.order order by TranTime desc";
		             //    1     2       3           4            5           6            7             8         9           10            11             12         
	

	@Override
	public void newOrderWithC(OrderVO orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(NewOrderWithCoupon);

			pstmt.setInt(1, orderVO.getMemNo());
			pstmt.setInt(2, orderVO.getMemCouponNo());
			pstmt.setInt(3, orderVO.getOrderTotalPrice());
			pstmt.setInt(4, orderVO.getPickupMethod());
			pstmt.setInt(5, orderVO.getShippingFee());
			pstmt.setString(6, orderVO.getReceiverName());
			pstmt.setString(7, orderVO.getReceiverAddress());
			pstmt.setString(8, orderVO.getReceiverPhone());

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
	public void newOrderWithouC(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(NewOrderWithoutCoupon);

			pstmt.setInt(1, orderVO.getMemNo());
			pstmt.setInt(2, orderVO.getOrderTotalPrice());
			pstmt.setInt(3, orderVO.getPickupMethod());
			pstmt.setInt(4, orderVO.getShippingFee());
			pstmt.setString(5, orderVO.getReceiverName());
			pstmt.setString(6, orderVO.getReceiverAddress());
			pstmt.setString(7, orderVO.getReceiverPhone());

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
	public void update(OrderVO orderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderVO.getOrderTotalPrice());
			pstmt.setInt(2, orderVO.getOrderState());
			pstmt.setInt(3, orderVO.getPickupMethod());
			pstmt.setInt(4, orderVO.getShippingFee());
			pstmt.setString(5, orderVO.getReceiverName());
			pstmt.setString(6, orderVO.getReceiverAddress());
			pstmt.setString(7, orderVO.getReceiverPhone());
			pstmt.setInt(8, orderVO.getOrderNo());

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
	public List<OrderVO> findByMemNo(Integer memNo) {
		
		List<OrderVO> list = new ArrayList<>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByMemNo);
			pstmt.setInt(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setOrderState(rs.getInt("OrderState"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
				
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
	public List<OrderVO> findByFinishedOrderNo() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByFinishedOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
				
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
	public OrderVO findByOrderNo(Integer orderNo) {

		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByOrderNo);
			pstmt.setInt(1, orderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setOrderState(rs.getInt("OrderState"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				
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
		return orderVO;
	}

	@Override
	public List<OrderVO> findByTrashOrderNo() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByTrashOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
				
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
	public List<OrderVO> findByReturnedOrderNo() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByReturnedOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
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
	public List<OrderVO> findByUnPulledOrderNo() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByUnPulledOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
				
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
	public List<OrderVO> findByPulledOrderNo() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByPulledOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
				
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
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AllOrder);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderNo(rs.getInt("OrderNo"));
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setMemCouponNo(rs.getInt("MemCouponNo"));
				orderVO.setTranTime(rs.getTimestamp("TranTime"));
				orderVO.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
				orderVO.setOrderState(rs.getInt("OrderState"));
				orderVO.setPickupMethod(rs.getInt("PickupMethod"));
				orderVO.setShippingFee(rs.getInt("ShippingFee"));
				orderVO.setTrackingNum(rs.getString("TrackingNum"));
				orderVO.setReceiverName(rs.getString("ReceiverName"));
				orderVO.setReceiverAddress(rs.getString("ReceiverAddress"));
				orderVO.setReceiverPhone(rs.getString("ReceiverPhone"));
				list.add(orderVO);
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
	public OrderVO findNewetOrderByMem(Integer memNo) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindNewetOrderByMem);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				orderVO = new OrderVO();				
				orderVO.setMemNo(rs.getInt("MemNo"));
				orderVO.setOrderNo(rs.getInt("max(orderNo)"));
			
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
		return orderVO;
	}

}
