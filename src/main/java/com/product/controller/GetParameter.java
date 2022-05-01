package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;



import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPic.model.ProductPicService;

/**
 * Servlet implementation class GetParameter
 */
@WebServlet("/product/GetParameter")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GetParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	public GetParameter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");		
		List<String> errorMsgs = new LinkedList<String>();
		
		
		errorMsgs.clear();
		request.removeAttribute("errorMsgs");
		
		
		
		request.setAttribute("errorMsgs", errorMsgs);
		ProductVO productVO = new ProductVO();
		PrintWriter out = response.getWriter();

		/*--------------前端輸入資訊獲取--------------------*/

		String productName = request.getParameter("ProductName");
		String nameRule = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:-)(\\-\\)]{1,30}$";
		if (productName.trim().length() == 0 || productName == null) {
			errorMsgs.add("商品名稱: 不能空白");
		} else if (!productName.trim().matches(nameRule)) {
			errorMsgs.add("商品名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
		}
		productVO.setProductName(productName);
		out.print("<h1>" + productName + "</h1>");

	
		Integer productPrice = null;
		try {
			String productPrice_String = request.getParameter("ProductPrice");
		
			productPrice = Integer.valueOf(productPrice_String);
			String priceRule = "^\\w{2,5}$";
			
			if (!productPrice_String.matches(priceRule)) {
				errorMsgs.add("商品價錢: 不能低於99元或是超過10萬元");
			}
			out.print("<h1>" + productPrice + "</h1>");

		} catch (NumberFormatException e1) {

			errorMsgs.add("商品價錢: 不能為空白");
		}
		productVO.setProductPrice(productPrice);
		
		
		

		String gameTypeNo_String = request.getParameter("GameTypeNo");
		System.out.println(gameTypeNo_String);
		Integer gameTypeNo = Integer.valueOf(gameTypeNo_String);
		out.print("<h1>" + gameTypeNo + "</h1>");
		productVO.setGameTypeNo(gameTypeNo);
		
		

		Integer gamePlatformNo = Integer.valueOf(request.getParameter("GamePlatformNo"));
		if (gamePlatformNo == null) {
			out.print("<h1>GamePlatformNo沒有東西</h1>");
		} else {
			out.print("<h1>" + gamePlatformNo + "</h1>");
		}
		productVO.setGamePlatformNo(gamePlatformNo);

		
		
		Integer gameCompanyNo = Integer.valueOf(request.getParameter("GameCompanyNo"));
		if (gameCompanyNo == null) {
			out.print("<h1>GameCompanyNo沒有東西</h1>");
		} else {
			out.print("<h1>" + gameCompanyNo + "</h1>");
		}
		productVO.setGameCompanyNo(gameCompanyNo);

		
		Integer productState = Integer.valueOf(request.getParameter("ProductState"));
		if (productState == null) {
			out.print("<h1>ProductState沒有東西</h1>");
		} else {
			out.print("<h1>" + productState + "</h1>");
		}
		productVO.setProductState(productState);
		

		String itemProdDescription = request.getParameter("ItemProdDescription");
		if (itemProdDescription.trim().length() == 0 || itemProdDescription == null) {
			errorMsgs.add("遊戲內容描述: 不能空白");
		}  else {
			out.print("<h1>" + itemProdDescription + "</h1>");
		}
		productVO.setItemProdDescription(itemProdDescription);

		String upcNum = request.getParameter("UpcNum");
		String upcNumRule = "^[(0-9)]{1,30}$";
		if (upcNum.trim().length() == 0 || upcNum == null) {
			errorMsgs.add("upc號碼: 不能空白");
		} else if (!upcNum.trim().matches(upcNumRule)) {
			errorMsgs.add("upc號碼: 只能包含數字, 且長度須在1到30之間");
		}
		productVO.setUpcNum(upcNum);

		/*--------------前端輸入資訊結束--------------------*/

		String compare = "select ProductNo from product where ProductNo = ?;";
		System.out.println("查詢比對是要編輯還是新增");
		try {
			String productNoCompare = (request.getParameter("ProductNo").equals("") ? "0"
					: request.getParameter("ProductNo"));

			System.out.println("有商品編號嗎?字串版: " + productNoCompare);
			Integer productNo = Integer.valueOf(productNoCompare);
			System.out.println("有商品編號嗎?數字版: " + productNo);
			PreparedStatement preparedStatement0 = connection.prepareStatement(compare);
			preparedStatement0.setString(1, productNoCompare);
			ResultSet resultset = preparedStatement0.executeQuery();

			if (!(errorMsgs.size() == 0)) {
				System.out.println("錯誤訊息forward開始");
				if (resultset.next()) {
					System.out.println("錯誤訊息forward到修改頁面開始");
					/***** 帶著錯誤訊息導到修改頁面 *******/
					for(String r:errorMsgs) {
						System.out.println(r);
					}
					response.sendRedirect("/CGA101G1/backend/product/productMod.jsp");
					return;
				} else {
					System.out.println("錯誤訊息forward到新增頁面開始");
					/***** 帶著錯誤訊息導到新增頁面 *******/
					
					request.setAttribute("productVO", productVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/product/productAdd.jsp");
					failureView.forward(request, response);
					return; // 程式中斷
//					response.sendRedirect("/CGA101G1/backend/product/productAdd.jsp");
				}
			};
			System.out.println(errorMsgs.isEmpty());

			if (resultset.next()&&(errorMsgs.isEmpty())) {
				System.out.println("開改修改");

				if ((request.getParameter("ProductState").equals("1"))) {
					/*--------------要更新且上架--------------------*/
					resultset.close();
					preparedStatement0.close();

////					String ProductNo = request.getParameter("ProductNo");
//					// 1 2 3 4 5 6 7 8
//					String update = "UPDATE product SET ProductName = ?, ProductPrice = ?, GameTypeNo = ?, GamePlatformNo = ?, GameCompanyNo = ?, ProductState = ?,LaunchedTime = now(), ItemProdDescription = ? WHERE (ProductNo = ?); ";

					System.out.println("開始更新的程式碼且要上架");

					ProductService productService = new ProductService();
					productService.updateProductAndSold(productNo, gameTypeNo, gamePlatformNo, gameCompanyNo,
							productName, productPrice, productState, itemProdDescription, upcNum);

					out.print("更新成功");

//					更新封面
					Integer productImage1NO = Integer.valueOf(request.getParameter("productImage1NO"));
					Part picPart1 = request.getPart("productImage1");
					InputStream picInputStream = picPart1.getInputStream();

					ProductPicService productPicService = new ProductPicService();
					if (picInputStream.available() != 0) {
						byte[] picbyte1 = new byte[picInputStream.available()];
						picInputStream.read(picbyte1);
						productPicService.changeProductPic(productImage1NO, picbyte1);
						out.print("封面更新成功");
					} else {
						out.print("封面沒有更新");
					}

//					更新圖片2
					Integer productImage2NO = Integer.valueOf(request.getParameter("productImage2NO"));
					Part picPart2 = request.getPart("productImage2");
					InputStream pic2InputStream = picPart2.getInputStream();

					ProductPicService productPic2Service = new ProductPicService();
					if (pic2InputStream.available() != 0) {
						byte[] picbyte2 = new byte[pic2InputStream.available()];
						pic2InputStream.read(picbyte2);
						productPic2Service.changeProductPic(productImage2NO, picbyte2);
						out.print("圖片2更新成功");
					} else {
						out.print("圖片2沒有更新");
					}

//					更新圖片3
					Integer productImage3NO = Integer.valueOf(request.getParameter("productImage3NO"));
					Part picPart3 = request.getPart("productImage3");
					InputStream pic3InputStream = picPart3.getInputStream();

					ProductPicService productPic3Service = new ProductPicService();
					if (pic3InputStream.available() != 0) {
						byte[] picbyte3 = new byte[pic3InputStream.available()];
						pic3InputStream.read(picbyte3);
						productPic3Service.changeProductPic(productImage3NO, picbyte3);
						out.print("圖片3更新成功");
					} else {
						out.print("圖片3沒有更新");
					}

					// 重導 response.sendRedirect("backend/product/productUpAndMod.html");
					response.setHeader("Refresh",
							"2;URL=http://localhost:8081/CGA101G1/backend/product/productMod.jsp");

				} else {

					/*--------------要更新且下架--------------------*/
					resultset.close();
					preparedStatement0.close();

//					String ProductNo = request.getParameter("ProductNo");
					// 1 2 3 4 5 6 7 8
//					String update = "UPDATE product SET ProductName = ?, ProductPrice = ?, GameTypeNo = ?, GamePlatformNo = ?, GameCompanyNo = ?, ProductState = ?,SoldTime = now(), ItemProdDescription = ? WHERE (ProductNo = ?); ";

					System.out.println("開始下架的程式碼");
					ProductService productService = new ProductService();
					productService.updateProductNotSold(productNo, gameTypeNo, gamePlatformNo, gameCompanyNo,
							productName, productPrice, productState, itemProdDescription, upcNum);

					out.print("下架成功");

//					更新封面
					System.out.println("開始更新圖");
					System.out.println(request.getParameter("productImage1NO"));
					Integer productImage1NO = Integer.valueOf(request.getParameter("productImage1NO"));
					System.out.println(productImage1NO);
					Part picPart1 = request.getPart("productImage1");
					InputStream picInputStream = picPart1.getInputStream();

					ProductPicService productPicService = new ProductPicService();
					if (picInputStream.available() != 0) {
						byte[] picbyte1 = new byte[picInputStream.available()];
						picInputStream.read(picbyte1);
						productPicService.changeProductPic(productImage1NO, picbyte1);
						out.print("封面更新成功");
					} else {
						out.print("封面沒有更新");
					}

//					更新圖片2
					Integer productImage2NO = Integer.valueOf(request.getParameter("productImage2NO"));
					Part picPart2 = request.getPart("productImage2");
					InputStream pic2InputStream = picPart2.getInputStream();

					ProductPicService productPic2Service = new ProductPicService();
					if (pic2InputStream.available() != 0) {
						byte[] picbyte2 = new byte[pic2InputStream.available()];
						pic2InputStream.read(picbyte2);
						productPic2Service.changeProductPic(productImage2NO, picbyte2);
						out.print("圖片2更新成功");
					} else {
						out.print("圖片2沒有更新");
					}

//					更新圖片3
					Integer productImage3NO = Integer.valueOf(request.getParameter("productImage3NO"));
					Part picPart3 = request.getPart("productImage3");
					InputStream pic3InputStream = picPart3.getInputStream();

					ProductPicService productPic3Service = new ProductPicService();
					if (pic3InputStream.available() != 0) {
						byte[] picbyte3 = new byte[pic3InputStream.available()];
						pic3InputStream.read(picbyte3);
						productPic3Service.changeProductPic(productImage3NO, picbyte3);
						out.print("圖片3更新成功");
					} else {
						out.print("圖片3沒有更新");
					}

					// 重導
//					response.sendRedirect("backend/product/productUpAndMod.html");
					response.setHeader("Refresh",
							"2;URL=http://localhost:8081/CGA101G1/backend/product/productMod.jsp");

				}

			} else if((errorMsgs.isEmpty())){
				System.out.println("開始新增資料");

//				判斷是否上架 未來要檢查有沒有是int
				if (request.getParameter("ProductState").equals("1")) {
					/*--------------新增且上架--------------------*/
					System.out.println("新增且上架");
					ProductService productService = new ProductService();
					productService.AddNewProductAndSold(gameTypeNo, gamePlatformNo, gameCompanyNo, productName,
							productPrice, productState, itemProdDescription, upcNum);

					System.out.println("新增且上架資料結束，開始新增圖片");

//			圖1
					Part picPart1 = request.getPart("productImage1");
					InputStream picInputStream = picPart1.getInputStream();
					byte[] picbyte1 = new byte[picInputStream.available()];
					picInputStream.read(picbyte1);

//			insert產品圖

					ProductPicService productPicService = new ProductPicService();
					productPicService.addProductPic(picbyte1);
					out.print("<h1>圖1塞成功</h1>");

					// 圖2
					Part picPart2 = request.getPart("productImage2");
					InputStream picInputStream2 = picPart2.getInputStream();
					byte[] picbyte2 = new byte[picInputStream2.available()];
					picInputStream2.read(picbyte2);

//			insert產品圖2
					ProductPicService productPic2Service = new ProductPicService();
					productPic2Service.addProductPic(picbyte2);
					out.print("<h1>圖2塞成功</h1>");

					// 圖3
					Part picPart3 = request.getPart("productImage3");
					InputStream picInputStream3 = picPart3.getInputStream();
					byte[] picbyte3 = new byte[picInputStream3.available()];
					picInputStream3.read(picbyte3);

//			insert產品圖3
					ProductPicService productPic3Service = new ProductPicService();
					productPic3Service.addProductPic(picbyte3);
					out.print("<h1>圖3塞成功</h1>");
					
					HttpSession session  = request.getSession();
					session.removeAttribute("productVO");
//					 重導 
					 response.sendRedirect("/CGA101G1/backend/product/productAdd.jsp");
//					response.setHeader("Refresh",
//							"2;URL=http://localhost:8081/CGA101G1/backend/product/productAdd.html");

				} else {
					/*--------------新增但是不上架--------------------*/
					ProductService productService = new ProductService();
					productService.AddNewProductNotSold(gameTypeNo, gamePlatformNo, gameCompanyNo, productName,
							productPrice, productState, itemProdDescription, upcNum);
					out.print("<h1>基本資料塞成功</h1>");

//					圖1
					Part picPart1 = request.getPart("productImage1");
					InputStream picInputStream = picPart1.getInputStream();
					byte[] picbyte1 = new byte[picInputStream.available()];
					picInputStream.read(picbyte1);

//					insert產品圖

					ProductPicService productPicService = new ProductPicService();
					productPicService.addProductPic(picbyte1);
					out.print("<h1>圖1塞成功</h1>");

					// 圖2
					Part picPart2 = request.getPart("productImage2");
					InputStream picInputStream2 = picPart2.getInputStream();
					byte[] picbyte2 = new byte[picInputStream2.available()];
					picInputStream2.read(picbyte2);

//					insert產品圖2
					ProductPicService productPic2Service = new ProductPicService();
					productPic2Service.addProductPic(picbyte2);
					out.print("<h1>圖2塞成功</h1>");

					// 圖3
					Part picPart3 = request.getPart("productImage3");
					InputStream picInputStream3 = picPart3.getInputStream();
					byte[] picbyte3 = new byte[picInputStream3.available()];
					picInputStream3.read(picbyte3);

//					insert產品圖3
					ProductPicService productPic3Service = new ProductPicService();
					productPic3Service.addProductPic(picbyte3);
					out.print("<h1>圖3塞成功</h1>");
					
					
					HttpSession session  = request.getSession();
					session.removeAttribute("productVO");

					// 重導 
					response.sendRedirect("/CGA101G1/backend/product/productAdd.jsp");
//					response.setHeader("Refresh",
//							"2;URL=http://localhost:8081/CGA101G1/backend/product/productAdd.html");
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			connection = dataSource.getConnection();

//			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Taipei","openthedidi", "OPENthe2004");
		} catch (SQLException | NamingException e) {

			e.printStackTrace();
		}
	}

}
