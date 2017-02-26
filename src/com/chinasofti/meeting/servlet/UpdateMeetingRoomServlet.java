package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.vo.MeetingRoom;

public class UpdateMeetingRoomServlet extends HttpServlet {

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

		int roomid=Integer.parseInt(request.getParameter("roomid"));
		int roomnum=Integer.parseInt(request.getParameter("roomnum"));
		int capacity=Integer.parseInt(request.getParameter("capacity"));
		String roomname=request.getParameter("roomname");
		String status=request.getParameter("status");
		String description=request.getParameter("description");
		MeetingRoomService service=new MeetingRoomService();
		service.updateMeetingRoom(new MeetingRoom(roomid,roomnum,roomname,capacity,status,description));
		request.getRequestDispatcher("ViewAllMeetingRoomsServlet").forward(request, response);
	}

}
