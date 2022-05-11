package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerVO;

public class ManagerLoginFilter implements Filter{


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		ManagerVO managerVO=(ManagerVO)session.getAttribute("managerVO");
		if (managerVO == null) {
			session.setAttribute("initlocation", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/backend/managerlogin/managerlogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}