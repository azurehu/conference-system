package com.chinasofti.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCookieFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		
		String username=null;
		String password=null;
		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("username")){
					username=cookie.getValue();
				}
				
				if(cookie.getName().equals("password")){
					password=cookie.getValue();
				}
			}
		}
		
		if(username==null||password==null){
			arg2.doFilter(arg0, arg1);	
		}else{
			request.getRequestDispatcher("LoginServlet?username="+username+"&pwd="+password).forward(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
