package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingRoomDAO {
//	DAO类关联连接工厂类
	 private Connection conn;
	 
//	 添加一个会议室,status默认为0，表示未被占用。当为1时，表示被占用
	 public void insert(MeetingRoom meetingroom){
		  conn=ConnectionFactory.getConnection();
		  String sql="insert into meetingroom"
				  +
					"(roomnum,roomname,capacity,status,description)" +
					" values(?,?,?,?,?)";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,meetingroom.getRoomnum());
			pstmt.setString(2,meetingroom.getRoomname());
			pstmt.setInt(3,meetingroom.getCapacity());
			pstmt.setString(4,meetingroom.getStatus());
			pstmt.setString(5,meetingroom.getDescription());

			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	
//	 根据ID查询会议室
	 public MeetingRoom selectByRoomid(int roomid){
		 conn=ConnectionFactory.getConnection();
		 MeetingRoom meetingroom=null;	
		 try {
			 PreparedStatement st=null;
			String sql="select * from meetingroom where roomid="+roomid+"";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
				meetingroom=new MeetingRoom();
				meetingroom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingroom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingroom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingroom.setRoomname(rs.getString("roomname"));
				meetingroom.setStatus(rs.getString("status"));
				meetingroom.setDescription(rs.getString("description"));		
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return meetingroom;
	 }
	 
//	 查询所有会议室
	 public List<MeetingRoom> selectAllMeetingRooms(){
		 conn=ConnectionFactory.getConnection();
		 List<MeetingRoom> list=new ArrayList<MeetingRoom>();
		 MeetingRoom meetingroom=null;	
		 try {
			 PreparedStatement st=null;
			String sql="select * from meetingroom ";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			while(rs.next()){
				meetingroom=new MeetingRoom();
				meetingroom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingroom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingroom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingroom.setRoomname(rs.getString("roomname"));
				meetingroom.setStatus(rs.getString("status"));
				meetingroom.setDescription(rs.getString("description"));	
				
				list.add(meetingroom);
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return list;
	 }
	 
//	 查询所有会议室
	 public List<MeetingRoom> selectMeetingRoomsByTime(Timestamp starttime,Timestamp endtime){
		 conn=ConnectionFactory.getConnection();
		 List<MeetingRoom> list=new ArrayList<MeetingRoom>();
		 String start=starttime.toString();
		 String end=endtime.toString();
		 MeetingRoom meetingroom=null;	
		 try {
			PreparedStatement st=null;
			 
			String sql="select *  from meetingroom " +
					"where  meetingroom.roomid not in" +
					" (select roomid  from meeting " +
					"where (starttime<'"+start+"' and endtime >'"+end+"')" +
							" or (starttime>'"+start+"' and starttime <'"+end+"')"+
							"or(endtime>'"+start+"' and endtime <'"+end+"') and status='0')";
			
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			while(rs.next()){
				meetingroom=new MeetingRoom();
				meetingroom.setRoomid(Integer.parseInt(rs.getString("roomid")));
				meetingroom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
				meetingroom.setCapacity(Integer.parseInt(rs.getString("capacity")));
				meetingroom.setRoomname(rs.getString("roomname"));
				meetingroom.setStatus(rs.getString("status"));
				meetingroom.setDescription(rs.getString("description"));	
				
				list.add(meetingroom);
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 System.out.println("MeetingROOM "+list.size());
		 return list;
	 }
	 
//	 根据ID更新会议室
	 public void updateMeetingroom(MeetingRoom meetingroom){
		  conn=ConnectionFactory.getConnection();
		  String sql="update meetingroom set roomnum=?,roomname=?,capacity=?,status=?,description=? where roomid="+meetingroom.getRoomid();
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingroom.getRoomnum());
			pstmt.setString(2, meetingroom.getRoomname());
			pstmt.setInt(3, meetingroom.getCapacity());
			pstmt.setString(4, meetingroom.getStatus());
			pstmt.setString(5,meetingroom.getDescription());
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	 
//	 根据ID更新会议室状态
	 public void updateMeetingroomStatus(int roomid,String status){
		  conn=ConnectionFactory.getConnection();
		  String sql="update meetingroom set status=? where roomid="+roomid;
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, status);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	 
	 public static void main(String[] args){
		 MeetingRoomDAO dao=new MeetingRoomDAO();
		 MeetingRoom meetingroom=new MeetingRoom(5,3301,"第XXX会议室",120,"1","最小会议室？？？");
//		 dao.insert(meetingroom);
//		 dao.updateMeetingroom(meetingroom);
//		 dao.updateMeetingroomStatus(5, "100");
//		 
//		 System.out.println(dao.selectByRoomid(6));
		 
//		 List<MeetingRoom> list=dao.selectAllMeetingRooms();
//		 for(MeetingRoom room:list){
//			 System.out.println(room);
//		 }
		 
		 List<MeetingRoom> list=dao.selectMeetingRoomsByTime(Timestamp.valueOf("2015-01-13 10:09:30"), Timestamp.valueOf("2015-01-14 10:09:30"));
		 for(MeetingRoom room:list){
			 System.out.println(room);
		 }
	 }
}
