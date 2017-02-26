package com.chinasofti.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Employee;

public class LoginedFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		EmployeeService service=new EmployeeService();
		Employee e=service.getLoginedEmployee();
		if(e==null){
			request.setAttribute("msg", "请先登录。");
			request.getRequestDispatcher("login.jsp").forward(req, res);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
