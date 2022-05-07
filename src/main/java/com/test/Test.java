//package com.test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
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
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public Test() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Integer managerNo = 71016;
//		// 全部的
//		ManagerAuthrizationFunctionService managerAuthrizationFunctionService = new ManagerAuthrizationFunctionService();
//		List<ManagerAuthrizationFunctionVO> auList = managerAuthrizationFunctionService.getAll();
////		System.out.println(auList.size());
//		// 會員有的
//		ManagerAuthService managerAuthService = new ManagerAuthService();
//		List<ManagerAuthVO> managerList = managerAuthService.getFunction(managerNo);
////		System.out.println(managerList.size());
//
//		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
//
//		// 會員跑回圈
//		for (int j = 0; j < managerList.size(); j++) {
////			System.out.println(managerAuthVO.getManagerAuthrizationFunctionNo());
//			// 全部的迴圈
//			for (int i = 0; i < auList.size(); i++) {
//				if (managerList.get(j).getManagerAuthrizationFunctionNo()
//						+ 0 == auList.get(i).getManagerAuthrizationFunctionNo() + 0) {
//					map.put(auList.get(i).getManagerAuthrizationFunctionNo(), 1);
//				}
//
//			}
//
//		}
//		System.out.println(map);
//		// 補 0 進去
//		for (int i = 0; i < auList.size(); i++) {
//			if (map.get(auList.get(i).getManagerAuthrizationFunctionNo()) == null) {
//				map.put(auList.get(i).getManagerAuthrizationFunctionNo(), 0);
//			}
//
//		}
//	}
//
//}
