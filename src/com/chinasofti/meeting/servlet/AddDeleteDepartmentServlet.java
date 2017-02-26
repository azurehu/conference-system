package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.DepartmentService;

public class AddDeleteDepartmentServlet extends HttpServlet {

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
//		使用过滤器后不再需要
		request.setCharacterEncoding("utf-8");
//		获得code值
		String code=request.getParameter("code");
		
		DepartmentService service=new DepartmentService();
		
		if(code!=null&&code.equals("add")){
			service.addDepartment(request.getParameter("departmentname"));
		}
		
		if(code!=null&&code.equals("delete")){
			service.deleteDepartment(Integer.parseInt(request.getParameter("departmentid")));
		}
		request.getRequestDispatcher("ViewAllDepartmentsServlet?code=viewalldepartments").forward(request, response);
	}

}
