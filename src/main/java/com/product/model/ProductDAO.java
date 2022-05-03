package com.product.model;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.ls.LSException;

import com.gameplatformtype.model.GamePlatformTypeVO;
import com.gametype.model.GameTypeService;
import com.gametype.model.GameTypeVO;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;

public class ProductDAO implements ProductDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	                               //新增不要上架
		private static final String INSERT = 
			"INSERT INTO product (gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum) VALUES (?, ?, ?, ?, ?, ?,?,?)";
	                            //    1          2               3             4            5           6                7             8      

		private static final String InsertAndSold = 
				"INSERT INTO product (gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum,LaunchedTime) VALUES (?, ?, ?, ?, ?, ?,?,?,now())";
		                            //    1          2               3             4            5           6                7             8      	
		
		
		
		private static final String GET_ALL = 
			"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum FROM product order by productNo desc";
		         //    1          2               3             4            5           6                7             8         9

		
		private static final String GET_ONE = 
			"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum FROM product where productNo = ?";
//	                  1           2               3             4         5              6          7               8            9                                    10
	

		private static final String GetOneName=
				"select ProductName from cga101g1.product where ProductNo = ? ;";
		
		
		
		private static final String GET_NewestONE = 
				"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum FROM product order by 1 desc limit 3";
//		                    1           2               3             4         5              6          7               8            9                                 

		
		

		private static final String GETAllInSell = 
				"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 order by productNo desc";
			         //    1          2               3             4            5           6       

		
		//分頁查詢				
		private static final String GETInSellByPage = 
				"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 order by productNo desc limit  ? , 9;";
			         //    1          2               3             4            5           6       

	   //顯示上市	
		private static final String ShowInSellCount = 
				"SELECT count(productNo) FROM product where ProductState = 1 order by productNo desc;";
			         //    1           

		
		
		//		                    產品修改也要上架
		private static final String UpdateAndSold = 
			"UPDATE product SET ProductName = ?, ProductPrice = ?, GameTypeNo = ?, GamePlatformNo = ?, GameCompanyNo = ?, ProductState = ?,LaunchedTime = now(), ItemProdDescription = ?,  upcNum = ? WHERE (ProductNo = ?); ";
		                       //    1                 2               3             4                      5                 6                                                  7             8                     9

		
		//		                    產品修改不要上架
		private static final String UpdateNotSold = 
				"UPDATE product SET ProductName = ?, ProductPrice = ?, GameTypeNo = ?, GamePlatformNo = ?, GameCompanyNo = ?, ProductState = ?,SoldTime = now(), ItemProdDescription = ? ,  upcNum = ?WHERE (ProductNo = ?); ";
		                          //    1                 2               3             4                      5                 6                                                  7             8                     9

		
		//		                    
		private static final String GetAllByWord = 
			"SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum FROM product where ProductName LIKE ?;";
		                       //    1                 2               3             4                      5                 6                7                          8                     9

		
		private static final String GetAllName=
				"select ProductNo,ProductName from product;";
		         //           1         2
		
		
		
	@Override
	public void insert(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, ProductVO.getGameTypeNo());
			pstmt.setInt(2, ProductVO.getGamePlatformNo());
			pstmt.setInt(3, ProductVO.getGameCompanyNo());
			pstmt.setString(4, ProductVO.getProductName());
			pstmt.setInt(5, ProductVO.getProductPrice());
			pstmt.setInt(6, ProductVO.getProductState());
			pstmt.setString(7, ProductVO.getItemProdDescription());
			pstmt.setString(8, ProductVO.getUpcNum());

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
	public void insertAndSold(ProductVO ProductVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(InsertAndSold);

			pstmt.setInt(1, ProductVO.getGameTypeNo());
			pstmt.setInt(2, ProductVO.getGamePlatformNo());
			pstmt.setInt(3, ProductVO.getGameCompanyNo());
			pstmt.setString(4, ProductVO.getProductName());
			pstmt.setInt(5, ProductVO.getProductPrice());
			pstmt.setInt(6, ProductVO.getProductState());
			pstmt.setString(7, ProductVO.getItemProdDescription());
			pstmt.setString(8, ProductVO.getUpcNum());

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
	public void updateAndSold(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UpdateAndSold);
			
			pstmt.setString(1, ProductVO.getProductName());
			pstmt.setInt(2, ProductVO.getProductPrice());
			pstmt.setInt(3, ProductVO.getGameTypeNo());
			pstmt.setInt(4, ProductVO.getGamePlatformNo());
			pstmt.setInt(5, ProductVO.getGameCompanyNo());
			pstmt.setInt(6, ProductVO.getProductState());
			pstmt.setString(7, ProductVO.getItemProdDescription());
			pstmt.setString(8, ProductVO.getUpcNum());
			
			pstmt.setInt(9, ProductVO.getProductNo());

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
	public void updateNotSold(ProductVO ProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UpdateNotSold);
			
			pstmt.setString(1, ProductVO.getProductName());
			pstmt.setInt(2, ProductVO.getProductPrice());
			pstmt.setInt(3, ProductVO.getGameTypeNo());
			pstmt.setInt(4, ProductVO.getGamePlatformNo());
			pstmt.setInt(5, ProductVO.getGameCompanyNo());
			pstmt.setInt(6, ProductVO.getProductState());
			pstmt.setString(7, ProductVO.getItemProdDescription());
			pstmt.setString(8, ProductVO.getUpcNum());
			
			pstmt.setInt(9, ProductVO.getProductNo());

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
	public ProductVO findByPrimaryKey(Integer productNo) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
				productVO.setProductState(rs.getInt("ProductState"));
				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
				productVO.setUpcNum(rs.getString("UpcNum"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
				productVO.setProductState(rs.getInt("ProductState"));
				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
				productVO.setUpcNum(rs.getString("UpcNum"));
				list.add(productVO);
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
	public List<ProductVO> getAllByWord(String ProductName) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetAllByWord);
			pstmt.setString(1, "%"+ProductName+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
				productVO.setProductState(rs.getInt("ProductState"));
				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
				productVO.setUpcNum(rs.getString("UpcNum"));
				list.add(productVO);
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
	public List<ProductVO> getAllInSell() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETAllInSell);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
//				productVO.setProductState(rs.getInt("ProductState"));
//				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
//				productVO.setUpcNum(rs.getString("UpcNum"));
				list.add(productVO);
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
	public List<ProductVO> findByTop3MaxPrimaryKey() {
		
		
		List<ProductVO> list = new ArrayList<>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NewestONE);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
				productVO.setProductState(rs.getInt("ProductState"));
				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
				productVO.setUpcNum(rs.getString("UpcNum"));
				list.add(productVO);
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
	public List<ProductVO> getAllInDetail() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setGameTypeNo(rs.getInt("GameTypeNo"));
				productVO.setGamePlatformNo(rs.getInt("GamePlatformNo"));
				productVO.setGameCompanyNo(rs.getInt("GameCompanyNo"));
				productVO.setProductName(rs.getString("ProductName"));
				productVO.setProductPrice(rs.getInt("ProductPrice"));
				productVO.setProductState(rs.getInt("ProductState"));
				productVO.setItemProdDescription(rs.getString("ItemProdDescription"));
				productVO.setUpcNum(rs.getString("UpcNum"));
				list.add(productVO);
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
	public List<ProductVO> getAllInName() {
		List<ProductVO> list = new ArrayList<>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetAllName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductNo(rs.getInt("ProductNo"));
				productVO.setProductName(rs.getString("ProductName"));
				list.add(productVO);
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
	public ProductVO findNameByPrimaryKey(Integer productNo) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetOneName);
			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProductName(rs.getString("ProductName"));

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
		return productVO;
	}

	
	
	@Override
	public List<Object> getAllInSellByMap() {
		
		
		List<Object>list = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETAllInSell);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				Integer productNo = rs.getInt("ProductNo");
				map.put("productNo", productNo);				
				map.put("gameTypeNo", rs.getInt("GameTypeNo"));
				map.put("gamePlatformNo", rs.getInt("GamePlatformNo"));
				map.put("gameCompanyNo", rs.getInt("GameCompanyNo"));
				map.put("productName", rs.getString("ProductName"));
				map.put("productPrice", rs.getInt("ProductPrice"));
				map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+productNo);
				OrderDetailService orderDetailService = new OrderDetailService();
				OrderDetailVO orderDetailVO = orderDetailService.showCommentAfterCaled(productNo);
				
				
				/*********** 該商品沒有評論時 orderDetailVO 會是null的處理 ***************/
				if (orderDetailVO == null) {
					map.put("avgCommentStar", 0);
					list.add(map);
				} else {
					Integer avgC = (int) Math.floor((orderDetailVO.getCommentStar() / orderDetailVO.getProductSales()));

					map.put("avgCommentStar", avgC);
					list.add(map);
				}
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
	public List<Object> getPageInSellByMap(Integer page) {
		List<Object>list = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETInSellByPage);
			
			pstmt.setInt(1, (page-1)*9);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				ProductVO productVO = new ProductVO();				
				Integer productNo = rs.getInt("ProductNo");
				map.put("productNo", productNo);
				
				Integer gameTypeNo=rs.getInt("GameTypeNo");
				map.put("gameTypeNo", gameTypeNo);
//				GameTypeVO gameTypeVO = productVO.getOneGameType(gameTypeNo);
//				String gameTypeName = gameTypeVO.getGameTypeName();				
//				map.put("gameTypeName", gameTypeName);
				
				Integer gamePlatformNo= rs.getInt("GamePlatformNo");
				map.put("gamePlatformNo", gamePlatformNo);
				GamePlatformTypeVO gamePlatformTypeVO = productVO.getOneGamePlatformType(gamePlatformNo);
				String gamePlatformTypeName = gamePlatformTypeVO.getGamePlatformName();
				map.put("gamePlatformTypeName", gamePlatformTypeName);
				
				
				map.put("gameCompanyNo", rs.getInt("GameCompanyNo"));
				map.put("productName", rs.getString("ProductName"));
				map.put("productPrice", rs.getInt("ProductPrice"));
				map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+productNo);
				OrderDetailService orderDetailService = new OrderDetailService();
				OrderDetailVO orderDetailVO = orderDetailService.showCommentAfterCaled(productNo);
				
				
				/*********** 該商品沒有評論時 orderDetailVO 會是null的處理 ***************/
				if (orderDetailVO == null) {
					map.put("avgCommentStar", 0);
					list.add(map);
				} else {
					Integer avgC = (int) Math.floor((orderDetailVO.getCommentStar() / orderDetailVO.getProductSales()));

					map.put("avgCommentStar", avgC);
					list.add(map);
				}
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
	public Integer showSellCount() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        Integer count = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ShowInSellCount);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {

				count = (rs.getInt("count(productNo)"));

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
		return count;
	}
}
	
	

	

