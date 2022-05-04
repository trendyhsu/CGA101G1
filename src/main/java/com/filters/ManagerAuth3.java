package com.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerVO;
import com.managerauth.model.ManagerAuthService;
import com.managerauth.model.ManagerAuthVO;
import com.member.model.MemVO;

/**
 * Servlet Filter implementation class ManagerAuth
 */

public class ManagerAuth3 extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 【取得 session】
				HttpSession session = req.getSession();
				// 【從 session 判斷此user是否登入過】
				ManagerVO managerVO = (ManagerVO)session.getAttribute("managerVO");
				ManagerAuthService managerAuthService = new ManagerAuthService();
				List<ManagerAuthVO> Auths=managerAuthService.getFunction(managerVO.getManagerNo());
				
				for(ManagerAuthVO Auth:Auths)  {
					
					if(Auth.getManagerAuthrizationFunctionNo() == 73003) {
						chain.doFilter(request, response);
						return;
					}
				} 
				session.setAttribute("message", "你沒有拜訪此管理頁面的權限");
				res.sendRedirect(req.getContextPath() + "/backend/index.jsp");
				
				
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
