package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDAO;
import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Department;
import com.chinasofti.meeting.vo.Employee;

public class RegistServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		设置请求的编码格式保证中文编码正确
//		使用过滤器后，不再需要
//		request.setCharacterEncoding("utf-8");
		
//		获取注册页面填写的请求参数
		String employeename=request.getParameter("employeename");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int deptid=Integer.parseInt(request.getParameter("deptid"));
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		
		Employee employee=new Employee(employeename,username,password,deptid,email,phone,"0","2");
		EmployeeService service=new EmployeeService();
		int flag=service.regist(employee);
		
		if(flag==1){
			request.setAttribute("msg", "注册成功，正在审核。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "用户名已存在，请重新注册。");
			DepartmentDAO dao=new DepartmentDAO();		
			List<Department> departmentsList=dao.selectAll();
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}

}
