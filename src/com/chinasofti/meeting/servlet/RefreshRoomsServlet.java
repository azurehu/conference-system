package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.dao.MeetingDAO;
import com.chinasofti.meeting.dao.MeetingRoomDAO;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.MeetingRoom;

public class RefreshRoomsServlet extends HttpServlet {

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
		Timestamp starttime=Timestamp.valueOf(request.getParameter("starttime"));
		Timestamp endtime=Timestamp.valueOf(request.getParameter("endtime"));
		
		MeetingRoomDAO dao=new MeetingRoomDAO();
		List<MeetingRoom> roomList=dao.selectMeetingRoomsByTime(starttime, endtime);
//		将查询得到的部门信息，以XML文档的格式返回到浏览器
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		out.println("<?xml version='1.0' encoding='" + "utf-8" + "' ?>");

		//符合XML规范，有根节点，否则解析有问题
		out.println("<departments>");
		for (MeetingRoom m : roomList) {
			out.println("<option>");
			out.println("<value>" + m.getRoomid()+ "</value>");
			out.println("<text>" + m.getRoomname() + "</text>");
			out.println("</option>");

		}
		out.println("</departments>");
		out.close();
	}

}
