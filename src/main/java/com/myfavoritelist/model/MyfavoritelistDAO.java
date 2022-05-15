package com.myfavoritelist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyfavoritelistDAO implements MyfavoritelistDAO_Interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://35.194.146.30:3306/cga101g1?serverTimezone=Asia/Taipei";
	String userid = "tibame";
	String passwd = "tibame";
	
	
	
	
	private final static String getOneInMem ="select MemNo,ProductNo from `cga101g1`.`myfavoritelist` where MemNo = ? and ProductNo = ? ;";
	
	private final static String getAllInMem ="select MemNo,ProductNo from `cga101g1`.`myfavoritelist` where MemNo = ? ;";
	
	private final static String addOneInMem ="INSERT INTO `cga101g1`.`myfavoritelist` (`MemNo`, `ProductNo`) VALUES (? , ?);";
	
	private final static String deleteOne ="DELETE FROM `cga101g1`.`myfavoritelist` WHERE (MemNo = ?) and (ProductNo = ?) ;";
	
	
	
	@Override
	public void delete(Integer memNo,Integer productNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(deleteOne);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, productNo);					
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
	public MyfavoritelistVo getOneByOneMem(Integer memNo, Integer productNo) {
		MyfavoritelistVo myfavoritelistVo = new MyfavoritelistVo();
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(getOneInMem);

			
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, productNo);					
			ResultSet rs =pstmt.executeQuery();

			if(rs.next()) {
				myfavoritelistVo.setMemNo(rs.getInt(1));
				myfavoritelistVo.setProductNo(rs.getInt(2));
				return myfavoritelistVo;
			}else
			{
			myfavoritelistVo.setMemNo(memNo);
			myfavoritelistVo.setProductNo(0);	
			return myfavoritelistVo;
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
	public void insert(Integer memNo, Integer productNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(addOneInMem);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, productNo);					
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
	public List<MyfavoritelistVo> getAllByOneMem(Integer memNo) {
		List<MyfavoritelistVo> list = new ArrayList<MyfavoritelistVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getAllInMem);				
			pstmt.setInt(1, memNo);				
			ResultSet rs =pstmt.executeQuery();

			while(rs.next()) {
				MyfavoritelistVo myfavoritelistVo = new MyfavoritelistVo();
				myfavoritelistVo.setMemNo(rs.getInt(1));
				myfavoritelistVo.setProductNo(rs.getInt(2));
				list.add(myfavoritelistVo);		
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
	public List<Object> getAllByOneMemInJoin(Integer memNo) {
		
		String sql = "SELECT a.productNo,a.productName,a.productPrice,b.GamePlatformName FROM product a  "
				+ "join gameplatformtype b on a.gamePlatformNo = b.gamePlatformNo "
				+ "join myfavoritelist c on  a.productNo=c.ProductNo "
				+ "where MemNo = ?;";
		
		List<Object> list = new ArrayList<Object>();
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);				
			pstmt.setInt(1, memNo);				
			ResultSet rs =pstmt.executeQuery();

			while(rs.next()) {
				Map<String, Object>map = new HashMap<String, Object>();
				map.put("productName", rs.getString("productName"));
				Integer productNo =rs.getInt("productNo");
				map.put("productNo", productNo);
				map.put("productPrice", rs.getInt("productPrice"));
				map.put("GamePlatformName", rs.getString("GamePlatformName"));
				
				String imageUrl="/CGA101G1/product/showOneCover?ProductNO="+productNo;
				map.put("imageUrl",imageUrl);

				list.add(map);		
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
