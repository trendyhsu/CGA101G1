package com.orderdetail.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements OrderDetailDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	
	//查某產品銷售狀況
	private static final String FindByProductNo = "SELECT ProductNo,sum(ProductSales) TotalSales,sum(ProductTotalPrice) TotalPrice,avg(CommentStar) avgCommentStar  FROM orderdetail group by ProductNo having ProductNo = ?;";
                                                     //         1              2                        3                                             4
	//查全品項銷售狀況
	private static final String FindAllProduct = "SELECT ProductNo,sum(ProductSales) totalSales,sum(ProductTotalPrice) TotalPrice,avg(CommentStar) avgCommentStar  FROM cga101g1.orderdetail group by ProductNo order by TotalPrice desc;";

	
	//查某張訂單的項目
	private static final String FindAllProductByOrderNo = "SELECT OrderNo,ProductNo,ProductSales,ProductTotalPrice,CommentStar,CommentCotent FROM cga101g1.orderdetail where OrderNo=? ;";
                                                             //      1        2           3              4              5           6
	
	//新增訂單項目
	private static final String NewByOrder = "INSERT INTO orderdetail (OrderNo, ProductNo, ProductSales, ProductTotalPrice) VALUES (?, ?, ?, ?);";
                                                                     //   1         2             3          4            

	//修改訂單項目內容
	private static final String ModByOrder = "UPDATE `cga101g1`.`orderdetail` SET ProductSales = ?, ProductTotalPrice = ?  WHERE (OrderNo = ?) and (ProductNo = ?);";
 
	
	//作廢訂單項目內容
	private static final String ClearByOrder = "UPDATE `cga101g1`.`orderdetail` SET ProductSales = 0, ProductTotalPrice = 0  WHERE (OrderNo = ?) and (ProductNo = ?);";
	
	
	//發表評論
	private static final String NewCommentCotent = "UPDATE `cga101g1`.`orderdetail` SET `CommentCotent` = ?,`CommentTime` = now(), `CommentStar` = ? WHERE (`OrderNo` = ?) and (`ProductNo` = ? );";
	
	//查某會員的所有評論
//	private static final String FindCommentbyMemNo = "select MemNo , ProductNo,ProductSales, ProductTotalPrice,CommentCotent,CommentTime, CommentStar FROM cga101g1.orderdetail WHERE MemNo = ?;";
                                                         //    1           2       3               4                 5             6             7                (1)  
	
	
	//查某商品的所有評論
	private static final String FindCommentbyProductNo = "select ProductNo,ProductSales, ProductTotalPrice,CommentCotent,CommentTime, CommentStar FROM cga101g1.orderdetail WHERE ProductNo = ? and CommentStar >= 0;";
	                                                        //    1           2                 3                4                 5         6                                                (1) 
	
	
	@Override
	public List<OrderDetailVO> findByProductNo(Integer productNo) {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindByProductNo);
			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setProductNo(rs.getInt("ProductNo"));
				orderDetailVO.setProductSales(rs.getInt("TotalSales"));
				orderDetailVO.setProductTotalPrice(rs.getInt("TotalPrice"));
				orderDetailVO.setCommentStar(rs.getInt("avgCommentStar"));
				list.add(orderDetailVO);			
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
	public void newByOrder(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(NewByOrder);
			pstmt.setInt(1, orderDetailVO.getOrderNo());
			pstmt.setInt(2, orderDetailVO.getProductNo());
			pstmt.setDouble(3, orderDetailVO.getProductSales());
			pstmt.setDouble(4, orderDetailVO.getProductTotalPrice());


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
	public void modByOrder(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ModByOrder);

			pstmt.setInt(1, orderDetailVO.getProductSales());
			pstmt.setInt(2, orderDetailVO.getProductTotalPrice());
			pstmt.setInt(3, orderDetailVO.getOrderNo());
			pstmt.setInt(4, orderDetailVO.getProductNo());

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
	public void newCommentCotent(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(NewCommentCotent);

			pstmt.setString(1, orderDetailVO.getCommentCotent());
			pstmt.setInt(2, orderDetailVO.getCommentStar());
			pstmt.setInt(3, orderDetailVO.getOrderNo());
			pstmt.setInt(4, orderDetailVO.getProductNo());

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
	public List<OrderDetailVO> findCommentbyProductNo(Integer productNo) {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindCommentbyProductNo);
			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setProductNo(rs.getInt("ProductNo"));
				orderDetailVO.setProductSales(rs.getInt("ProductSales"));
				orderDetailVO.setProductTotalPrice(rs.getInt("ProductTotalPrice"));
				orderDetailVO.setCommentCotent(rs.getString("CommentCotent"));
				orderDetailVO.setCommentTime(rs.getDate("CommentTime"));
				orderDetailVO.setCommentStar(rs.getInt("CommentStar"));
				list.add(orderDetailVO);			
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
	public List<OrderDetailVO> findAllProduct() {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindAllProduct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setProductNo(rs.getInt("ProductNo"));
				orderDetailVO.setProductSales(rs.getInt("totalSales"));
				orderDetailVO.setProductTotalPrice(rs.getInt("TotalPrice"));
				orderDetailVO.setCommentStar(rs.getInt("avgCommentStar"));
				list.add(orderDetailVO);			
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
	public List<OrderDetailVO> findAllDetailByOrderNo(Integer OrderNo) {
		List<OrderDetailVO> list = new ArrayList<>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FindAllProductByOrderNo);
			pstmt.setInt(1, OrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getInt("OrderNo"));
				orderDetailVO.setProductNo(rs.getInt("ProductNo"));
				orderDetailVO.setProductSales(rs.getInt("ProductSales"));
				orderDetailVO.setProductTotalPrice(rs.getInt("ProductTotalPrice"));
				orderDetailVO.setCommentStar(rs.getInt("CommentStar"));
				orderDetailVO.setCommentCotent(rs.getString("CommentCotent"));
				list.add(orderDetailVO);			
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
	public OrderDetailVO clearByOrder(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ClearByOrder);

			pstmt.setInt(1, orderDetailVO.getOrderNo());
			pstmt.setInt(2, orderDetailVO.getProductNo());


			pstmt.executeUpdate();
			return orderDetailVO;

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
}