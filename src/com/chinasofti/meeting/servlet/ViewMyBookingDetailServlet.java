package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.EmployeeDAO;
import com.chinasofti.meeting.dao.MeetingDAO;
import com.chinasofti.meeting.dao.MeetingParticipantsDAO;
import com.chinasofti.meeting.service.MeetingService;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;

public class ViewMyBookingDetailServlet extends HttpServlet {

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

		int meetingid=Integer.parseInt(request.getParameter("meetingid"));
		MeetingDAO meetingDao=new MeetingDAO();
		MeetingParticipantsDAO parDao=new MeetingParticipantsDAO();			
		Meeting meeting=meetingDao.selectById(meetingid);		
		List<Employee> employeesList=parDao.selectAllEmployeesByMeetingId(meeting.getMeetingid());
		request.setAttribute("meeting", meeting);
		request.setAttribute("employeesList", employeesList);
		request.getRequestDispatcher("mybookingdetail.jsp").forward(request, response);
	}

}
