package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.DepartmentDAO;
import com.chinasofti.meeting.service.DepartmentService;
import com.chinasofti.meeting.vo.Department;

public class ViewAllDepartmentsServlet extends HttpServlet {

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
		
		DepartmentService service=new DepartmentService();
		
		List<Department> departmentsList=service.viewAllDepartments();
		request.setAttribute("departmentsList", departmentsList);
		
		String code=request.getParameter("code");
		
		if(code!=null&&code.equals("regist")){
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		if(code!=null&&code.equals("viewalldepartments")){
			request.getRequestDispatcher("departments.jsp").forward(request, response);
		}
		
	}

}
