//package com.test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Set;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.manager.model.ManagerVO;
//import com.managerauth.model.ManagerAuthService;
//import com.managerauth.model.ManagerAuthVO;
//import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;
//import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
//
///**
// * Servlet implementation class Test
// */
//@WebServlet("/test/testForward")
//public class Test extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Test() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		// 從input hidden 取得
//		Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));
//		
//		
//		// 資料庫中目前managerNo有的權限清單
//		ManagerAuthService managerAuthService = new ManagerAuthService();
////		Set<ManagerAuthVO> set = managerAuthService.getFunction(managerNo);
//
//		// 刪除所有managerNo的權限
//		managerAuthService.deleteAllByManagerNo(managerNo);
//		
//		// 從checkbox取得勾選的list
//		String[] managerAuthNos = request.getParameterValues("name");
//		
//		// 查詢現在有沒有
//		for(String managerAuth:managerAuthNos) {
//			managerAuthService.addManagerAuth(managerNo, Integer.valueOf(managerAuth));
//		}
//		
//	}
//
//}
