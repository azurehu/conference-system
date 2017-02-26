package com.chinasofti.meeting.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.dao.MeetingDAO;
import com.chinasofti.meeting.dao.MeetingParticipantsDAO;
import com.chinasofti.meeting.vo.Meeting;

public class MeetingService {
	private MeetingDAO meetingDao=new MeetingDAO();
	private MeetingParticipantsDAO parDao=new MeetingParticipantsDAO();
	
	public void bookMeeting(Meeting meeting,List<Integer> employeeidList){
		int meetingid=meetingDao.insert(meeting);
		for(Integer employeeid:employeeidList){
			parDao.insert(meetingid, employeeid);
		}
	}
	
	public void cancelMeeting(int meetingid){
		meetingDao.update(meetingid, "1", new Timestamp(System.currentTimeMillis()));
	
	}
	
	public List<Meeting> viewMyBookingInfo(int reservationistid){
		return meetingDao.selectAllMeetingsByReserId(reservationistid);
	}
	
	public List<Meeting> viewMymeetingsInfo(int participantsid){
		return parDao.selectAllMeetingsByParId(participantsid);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingService service=new MeetingService();
//		Meeting meeting=new Meeting("运营会wuwuw",5,8,10,Timestamp.valueOf("2015-01-02 11:09:00"),Timestamp.valueOf("2015-01-03 11:09:00"),Timestamp.valueOf("2015-01-02 11:09:00"),Timestamp.valueOf("2015-01-02 11:09:00"),"看当月运营情况","0");
//		List<Integer> idList=new ArrayList<Integer>();
//		idList.add(8);
//		idList.add(9);
//		
//		service.bookMeeting(meeting, idList);
		
		service.cancelMeeting(23);

	}

}
