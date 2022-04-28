package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemVO;

public class MemberLoginFilter implements Filter{

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		if (memVO == null) {
			session.setAttribute("initlocation", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontend/memLogin/login.html");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}