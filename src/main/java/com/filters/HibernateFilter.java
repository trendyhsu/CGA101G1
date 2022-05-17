package com.filters;

import static com.core.utils.HibernateUtil.*;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebFilter("/*")
public class HibernateFilter extends HttpFilter { 	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain){
		Session session = getSessionFactory().getCurrentSession();
	
		try {
			Transaction transaction =session.beginTransaction();
			chain.doFilter(req, res);
			transaction.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} 
	}

}
