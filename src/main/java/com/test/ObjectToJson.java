package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

@WebServlet("/ObjectToJson")
public class ObjectToJson extends HttpServlet {

	private static final long serialVersionUID = 2705557302139503921L;


	public void init() throws ServletException {
	

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");

		                   //      1           2              3               4               5                 6              7               8               9             10             11                  12           13                14
		String sql = "select a.productNO,a.GameTypeNo,c.GameTypeName,a.GamePlatformNo,d.GamePlatformName,a.ProductName,a.GameCompanyNo,e.GameCompanyName,a.ProductPrice,a.ProductState,a.ItemProdDescription,a.UpcNum,b.ProductPicContent,b.ProductPicNO from product a \r\n"
				+ "join (select ProductNo,ProductPicNO,ProductPicContent FROM productpic where ProductPicNO in(select min(ProductPicNO) FROM productpic group by ProductNo)) b on a.ProductNo=b.ProductNo\r\n"
				+ "join gametype c on a.GameTypeNo=c.GameTypeNo\r\n"
				+ "join gameplatformtype d on a.GamePlatformNo=d.GamePlatformNo\r\n"
				+ "join gamecompany e on a.GameCompanyNo=e.GameCompanyNo;";
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			Connection connection  = dataSource.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			PrintWriter out = response.getWriter();
			ResultSet resultset = preparedStatement.executeQuery();
		
		
			//�гy�@�Ӫ���ArrayList
			List<Product> products = new ArrayList();
			while (resultset.next()) {
				byte[] imageBytes = resultset.getBytes("ProductPicContent");

				
				//��Base64�r��
				String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
				
				

				//��J
				Product product = new Product();
				product.setProductNo(resultset.getString(1));
				product.setGameTypeNo(resultset.getString(2));
				product.setGameTypeName(resultset.getString(3));
				product.setGamePlatformNo(resultset.getString(4));
				product.setGamePlatformName(resultset.getString(5));
				product.setProductName(resultset.getString(6));
				product.setGameCompanyNo(resultset.getString(7));
				product.setGameCompanyName(resultset.getString(8));
				product.setProductPrice(resultset.getString(9));
				product.setProductState(resultset.getString(10));
				product.setItemProdDescription(resultset.getString(11));
				product.setUpcNum(resultset.getString(12));
				product.setProductPicContent(imageBase64);
				
				product.setProductPicNO(resultset.getString(14));
				products.add(product);	
			}
			//�নJson�榡
			Gson gson = new Gson();
				String json = gson.toJson(products);
				out.print(json);
//			System.out.println("2");

		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

class Product {

	private String ProductNo;
	private String GameTypeNo;
	private String GameTypeName;
	private String GamePlatformNo;
	private String GamePlatformName;
	private String ProductName;
	private String GameCompanyNo;
	private String GameCompanyName;
	private String ProductPrice;
	private String ProductState;
	private String ItemProdDescription;
	private String UpcNum;
	private Date SoldTime;
	private Date LaunchedTime;
	
	
	
	private List<Product> ProductPicList;
	private String ProductPicContent;
	private String ProductPicNO;
	private String ProductPic2;
	private String ProductPicNO2;
	private String ProductPic3;
	private String ProductPicNO3;
	
	

	public Product() {};

	public String getProductNo() {
		return ProductNo;
	}
	public void setProductNo(String ProductNo) {
		this.ProductNo = ProductNo;
	}

	public String getGameTypeNo() {
		return GameTypeNo;
	}
	public void setGameTypeNo(String GameTypeNo) {
		this.GameTypeNo = GameTypeNo;
	}
	
	
	public String getGameTypeName() {
		return GameTypeName;
	}
	public void setGameTypeName(String GameTypeName) {
		this.GameTypeName = GameTypeName;
	}
	
	public String getGamePlatformNo() {
		return GamePlatformNo;
	}
	public void setGamePlatformNo(String GamePlatformNo) {
		this.GamePlatformNo = GamePlatformNo;
	}
	
	public String getGamePlatformName() {
		return GamePlatformName;
	}
	public void setGamePlatformName(String GamePlatformName) {
		this.GamePlatformName = GamePlatformName;
	}
	
	
	
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}
	
	
	public String getGameCompanyNo() {
		return GameCompanyNo;
	}
	public void setGameCompanyNo(String GameCompanyNo) {
		this.GameCompanyNo = GameCompanyNo;
	}
	
	
	public String getGameCompanyName() {
		return GameCompanyName;
	}
	public void setGameCompanyName(String GameCompanyName) {
		this.GameCompanyName = GameCompanyName;
	}
	
	public String getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(String ProductPrice) {
		this.ProductPrice = ProductPrice;
	}
	
	
	public String getProductState() {
		return ProductState;
	}
	public void setProductState(String ProductState) {
		this.ProductState = ProductState;
	}
	
	public String getItemProdDescription() {
		return ItemProdDescription;
	}
	public void setItemProdDescription(String ItemProdDescription) {
		this.ItemProdDescription = ItemProdDescription;
	}
	
	public String getUpcNum() {
		return UpcNum;
	}
	public void setUpcNum(String UpcNum) {
		this.UpcNum = UpcNum;
	}

	public List<Product> getProductPicList() {
		return ProductPicList;
	}
	public void setProductPicList(List<Product> ProductPicList) {
		this.ProductPicList = ProductPicList;
	}
	
	
	
	public String getProductPicNO() {
		return ProductPicNO;
	}
	public void setProductPicNO(String ProductPicNO) {
		this.ProductPicNO = ProductPicNO;
	}
	
	public String getProductPicNO2() {
		return ProductPicNO2;
	}
	public void setProductPicNO2(String ProductPicNO) {
		this.ProductPicNO2 = ProductPicNO;
	}
	
	public String getProductPicNO3() {
		return ProductPicNO3;
	}
	public void setProductPicNO3(String ProductPicNO) {
		this.ProductPicNO3 = ProductPicNO;
	}
	

	public String getProductPicContent() {
		return ProductPicContent;
	}
	public void setProductPicContent(String ProductPicContent) {
		this.ProductPicContent = ProductPicContent;
	}
	
	public String getProductPic2() {
		return ProductPic2;
	}
	public void setProductPic2(String ProductPic) {
		this.ProductPic2 = ProductPic;
	}
	
	public String getProductPic3() {
		return ProductPic3;
	}
	public void setProductPic3(String ProductPic) {
		this.ProductPic3 = ProductPic;
	}

}
