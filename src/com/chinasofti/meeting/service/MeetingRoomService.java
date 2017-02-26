package com.chinasofti.meeting.service;

import java.util.List;

import com.chinasofti.meeting.dao.MeetingRoomDAO;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomService {

	private MeetingRoomDAO dao=new MeetingRoomDAO();
	
	public List<MeetingRoom> viewAllMeetingRooms(){
		return dao.selectAllMeetingRooms();
	}
	
	public MeetingRoom viewOneMeetingRoom(int roomid){
		return dao.selectByRoomid(roomid);
	}
	
	public void addMeetingRoom(MeetingRoom meetingroom){
		dao.insert(meetingroom);
	}
	
	public void updateMeetingRoom(MeetingRoom meetingroom){
		dao.updateMeetingroom(meetingroom);
	}
}
