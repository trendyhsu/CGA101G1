package com.productPic.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




public class ProductPicJDBCDAO implements  ProductPicDAO_interface{
	
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	
                              //新增  OK
	private static final String INSERT = 
			"Insert into productpic (ProductPicContent,ProductNo) value (?,(SELECT ProductNo from product order by ProductNo desc limit 1));";

	
	
		private static final String GET_ALL = 
			"SELECT ProductNo,ProductPicNO from productpic";
		         //OK    1          2               3            

		
		private static final String GET_AllCovers = 
				"select ProductNo,ProductPicNO,ProductPicContent FROM productpic where ProductPicNO in(select min(ProductPicNO) FROM productpic group by ProductNo);";
			         //OK    1          2               3  
		
		
		
		
		private static final String GET_ONE = 
			"select ProductNo,ProductPicNO,ProductPicContent from productpic where ProductNo = ?";
//OK	                  1           2               3                                             4
		
		//		                    圖片修改
		private static final String UPDATE = 
			"UPDATE productpic SET ProductPicContent = ? WHERE ProductPicNO = ?;";
		                       //                      1                      2            
	
		private static final String GetoneInByte = " select ProductPicContent from productpic where ProductPicNO=?";
                                    //OK	
	
	@Override
	public void insert(ProductPicVO ProductPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String ProductPicNO[] = { "ProductPicNO" };
			pstmt = con.prepareStatement(INSERT,ProductPicNO);
		    pstmt.setBytes(1, ProductPicVO.getProductPicContentByte());
					
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
	public void update(ProductPicVO ProductPicVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);	
			
		    pstmt.setBytes(1, ProductPicVO.getProductPicContentByte());
		    pstmt.setInt(2, ProductPicVO.getProductPicNo());
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
	public List<ProductPicVO> findByPrimaryKeyInBase64(Integer productno) {
		List<ProductPicVO> list = new ArrayList<>();
		ProductPicVO productPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, productno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				byte[] imageBytes = rs.getBytes("ProductPicContent");
				String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
				
				// productVO 也稱為 Domain objects
				productPicVO = new ProductPicVO();
				productPicVO.setProductNo(rs.getInt("ProductNo"));
				productPicVO.setProductPicNo(rs.getInt("ProductPicNo"));
				productPicVO.setProductPicContentBase64(imageBase64);
				list.add(productPicVO);
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
	public List<ProductPicVO> getAll(HttpServletRequest request) {
		
		List<ProductPicVO> list = new ArrayList<>();
		ProductPicVO productPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productPicVO = new ProductPicVO();
				
				String imgurl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/product/OutPicByServerlet?ProductPicNO="+rs.getString(2);
	
				productPicVO.setProductNo(rs.getInt("ProductNo"));
				productPicVO.setProductPicNo(rs.getInt("ProductPicNo"));
				productPicVO.setImageUrl(imgurl);
				list.add(productPicVO);
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
	public ProductPicVO findByPrimaryKeyInByte(Integer productPicNo) {
		ProductPicVO productPicVO = new ProductPicVO();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement ps = con.prepareStatement(GetoneInByte);
			ps.setInt(1, productPicNo);
			rs = ps.executeQuery();

			while (rs.next()) {
			
				productPicVO.setProductPicContentByte(rs.getBytes("ProductPicContent"));
				return productPicVO;
			}
			

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductPicVO> getAllCovers(HttpServletRequest request) {
		List<ProductPicVO> list = new ArrayList<>();
		ProductPicVO productPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AllCovers);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productPicVO = new ProductPicVO();
//				System.out.println(rs.getString(2));
				
				String imgurl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/product/OutPicByServerlet?ProductPicNO="+rs.getString(2);
//				System.out.println(imgurl);
				productPicVO.setProductNo(rs.getInt("ProductNo"));
				productPicVO.setProductPicNo(rs.getInt("ProductPicNo"));
				productPicVO.setImageUrl(imgurl);
				list.add(productPicVO);
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
}