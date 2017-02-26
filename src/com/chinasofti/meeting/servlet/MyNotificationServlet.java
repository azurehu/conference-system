package com.chinasofti.meeting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.meeting.dao.MeetingDAO;
import com.chinasofti.meeting.dao.MeetingParticipantsDAO;
import com.chinasofti.meeting.service.MeetingRoomService;
import com.chinasofti.meeting.service.MeetingService;
import com.chinasofti.meeting.vo.Meeting;

public class MyNotificationServlet extends HttpServlet {

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
		
		HttpSession session=request.getSession();
		int participantsid=(Integer) session.getAttribute("employeeid");
		
		MeetingParticipantsDAO dao=new MeetingParticipantsDAO();
		List<Meeting> meetingsList=dao.selectAllNewMeetings(participantsid);
		List<Meeting> canceledMeetings=dao.selectAllCanceledMeetings(participantsid);
        
		MeetingRoomService roomService=new MeetingRoomService();
		
		List<String>  roomsNameList=new ArrayList<String>();
		for(Meeting m:meetingsList){
			roomsNameList.add(roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		Map<Meeting,String> meetingsMap=new HashMap<Meeting,String>();
		
		for(int i=0;i<meetingsList.size();i++){
			meetingsMap.put(meetingsList.get(i), roomsNameList.get(i));
		}
		
		request.setAttribute("meetingsMap", meetingsMap);
		
		List<String>  cancelRoomNameList=new ArrayList<String>();
		for(Meeting m:canceledMeetings){
			cancelRoomNameList.add(roomService.viewOneMeetingRoom(m.getRoomid()).getRoomname());
		}
		
		Map<Meeting,String> cancelMeetingsMap=new HashMap<Meeting,String>();
		
		for(int i=0;i<canceledMeetings.size();i++){
			cancelMeetingsMap.put(canceledMeetings.get(i),cancelRoomNameList.get(i));
		}
		request.setAttribute("cancelMeetingsMap", cancelMeetingsMap);
		
		request.getRequestDispatcher("mynotification.jsp").forward(request, response);
	}
}
