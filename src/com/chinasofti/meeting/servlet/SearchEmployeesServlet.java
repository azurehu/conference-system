package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDAO;

import com.chinasofti.meeting.service.EmployeeService;
import com.chinasofti.meeting.vo.Employee;

public class SearchEmployeesServlet extends HttpServlet {

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
		
			EmployeeService service=new EmployeeService();
		
			String employeename=request.getParameter("employeename");
			String username=request.getParameter("username");
			String status=request.getParameter("status");
			
//			当前页码，如果pageNum为空，则表示第一次查询，则显示第一页
			String pageNumStr=request.getParameter("pageNum");
			int pageNum=0;
			if(pageNumStr==null||pageNumStr.equals("")){
				pageNum=1;
			}else{
				pageNum=Integer.parseInt(pageNumStr);
			}
			
//			每页的记录数量
			int pageSize=service.getPageSize();			
//			起始记录索引
			int start=(pageNum-1)*pageSize;	
			
//			查询的数量，即每页的行数
			int end=pageSize;
//			获得所有记录数量，先调用DAO中的search方法
			service.searchEmployees(employeename, username, status);
			int countOfEmployees=service.getCountOfEmployees();
//			页数
			int countOfPages=service.getCountOfPages();
			
			List<Employee> list=service.searchEmployeesOfOnePage(employeename, username, status,start,end);
			request.setAttribute("employeesList", list);
			
//			使用search标记调用了SearchEmployeesServlet,即显示结果表格
			request.setAttribute("search", "1");
//			存储页数、所有记录的数量、当前页码
			request.setAttribute("countOfPages", countOfPages);
			request.setAttribute("countOfEmployees", countOfEmployees);
			request.setAttribute("pageNum", pageNum);
			request.getRequestDispatcher("searchemployees.jsp").forward(request, response);
	}

}
