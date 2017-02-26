package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Employee;
import com.chinasofti.meeting.vo.Meeting;
import com.chinasofti.meeting.vo.MeetingRoom;

public class MeetingParticipantsDAO {
	 private Connection conn;	
	 
//		向表meetingparticipants中插入记录
		  public void insert(int meetingid,int employeeid){
			  conn=ConnectionFactory.getConnection();
			  String sql="insert into meetingparticipants values(?,?)";
			  try {		
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, meetingid);
				pstmt.setInt(2, employeeid);
				
				pstmt.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}	  
		  }
		  
		  
//				 查询某员工最近七天参加的所有会议
				 public List<Meeting> selectAllNewMeetings(int participantsid){
					 conn=ConnectionFactory.getConnection();
					 List<Meeting> meetingslist=new ArrayList<Meeting>();
					 Meeting meeting=null;	
					 try {
						PreparedStatement st=null;
						Timestamp now=new Timestamp(System.currentTimeMillis());
						Timestamp sevenDays=new Timestamp(System.currentTimeMillis()+7*24*3600*1000);
						String nowTime=now.toString();
						String sevenDaysTime=sevenDays.toString();
						
						String sql="select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and" +
								" meetingparticipants.employeeid=" +participantsid+
								" and meeting.starttime>'"+nowTime+"'and meeting.starttime<'"+sevenDaysTime+"' and status='0'";
						st= conn.prepareStatement(sql);
						ResultSet rs =st.executeQuery(sql);
						while(rs.next()){
							meeting=new Meeting();
							meeting.setMeetingid(rs.getInt("meetingid"));
							meeting.setMeetingname(rs.getString("meetingname"));
							meeting.setRoomid(rs.getInt("roomid"));
							meeting.setReservationistid(rs.getInt("reservationistid"));
							meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
							meeting.setStarttime(rs.getTimestamp("starttime"));
							meeting.setEndtime(rs.getTimestamp("endtime"));
							meeting.setReservationtime(rs.getTimestamp("reservationtime"));
							meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
							meeting.setDescription(rs.getString("description"));
							meeting.setStatus(rs.getString("status"));
							meetingslist.add(meeting);
						}
					 } catch (SQLException e) {
						    e.printStackTrace();
					}finally{
						ConnectionFactory.closeConnection();
					}
					 return meetingslist;
				 }
				 
//				 查询某员工被要求参加，但是又取消的会议
				 public List<Meeting> selectAllCanceledMeetings(int participantsid){
					 conn=ConnectionFactory.getConnection();
					 List<Meeting> meetingslist=new ArrayList<Meeting>();
					 Meeting meeting=null;	
					 try {
						PreparedStatement st=null;
						Timestamp now=new Timestamp(System.currentTimeMillis());
						String nowTime=now.toString();
						
						String sql="select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and" +
								" meetingparticipants.employeeid=" +participantsid+
								" and meeting.starttime>'"+nowTime+"'and status='1'";
				 		st = conn.prepareStatement(sql);
				 						 		
						ResultSet rs =st.executeQuery(sql);
						while(rs.next()){
							meeting=new Meeting();
							meeting.setMeetingid(rs.getInt("meetingid"));
							meeting.setMeetingname(rs.getString("meetingname"));
							meeting.setRoomid(rs.getInt("roomid"));
							meeting.setReservationistid(rs.getInt("reservationistid"));
							meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
							meeting.setStarttime(rs.getTimestamp("starttime"));
							meeting.setEndtime(rs.getTimestamp("endtime"));
							meeting.setReservationtime(rs.getTimestamp("reservationtime"));
							meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
							meeting.setDescription(rs.getString("description"));
							meeting.setStatus(rs.getString("status"));
							meetingslist.add(meeting);
						}
					 } catch (SQLException e) {
						    e.printStackTrace();
					}finally{
						ConnectionFactory.closeConnection();
					}
					 return meetingslist;
				 }
		  
//			 查询某员工参加的所有会议
			 public List<Meeting> selectAllMeetingsByParId(int participantsid){
				 conn=ConnectionFactory.getConnection();
				 List<Meeting> meetingslist=new ArrayList<Meeting>();
				 Meeting meeting=null;	
				 try {
					PreparedStatement st=null;
					String sql="select * from meeting,meetingparticipants where meeting.meetingid=meetingparticipants.meetingid and meetingparticipants.employeeid="+participantsid;
			 		st = conn.prepareStatement(sql);
					ResultSet rs =st.executeQuery(sql);
					while(rs.next()){
						meeting=new Meeting();
						meeting.setMeetingid(rs.getInt("meetingid"));
						meeting.setMeetingname(rs.getString("meetingname"));
						meeting.setRoomid(rs.getInt("roomid"));
						meeting.setReservationistid(rs.getInt("reservationistid"));
						meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
						meeting.setStarttime(rs.getTimestamp("starttime"));
						meeting.setEndtime(rs.getTimestamp("endtime"));
						meeting.setReservationtime(rs.getTimestamp("reservationtime"));
						meeting.setCanceledtime(rs.getTimestamp("canceledtime"));
						meeting.setDescription(rs.getString("description"));
						meeting.setStatus(rs.getString("status"));
						meetingslist.add(meeting);
					}
				 } catch (SQLException e) {
					    e.printStackTrace();
				}finally{
					ConnectionFactory.closeConnection();
				}
				 return meetingslist;
			 }
			 
//			 查询某会议中所有的参会员工
			 public List<Employee> selectAllEmployeesByMeetingId(int meetingid){
				 conn=ConnectionFactory.getConnection();
				 List<Employee> employeeslist=new ArrayList<Employee>();
				 Employee employee=null;	
				 try {
					PreparedStatement st=null;
					String sql="select * from employee,meetingparticipants where employee.employeeid=meetingparticipants.employeeid and meetingparticipants.meetingid="+meetingid;
			 		st = conn.prepareStatement(sql);
					ResultSet rs =st.executeQuery(sql);
					while(rs.next()){
						employee=new Employee();
						employee.setEmployeeid(rs.getInt("employeeid"));
						employee.setEmployeename(rs.getString("employeename"));
						employee.setUsername(rs.getString("username"));
						employee.setPhone(rs.getString("phone"));
						employee.setEmail(rs.getString("email"));
						employee.setStatus(rs.getString("status"));
						employee.setDepartmentid(rs.getInt("departmentid"));
						employee.setPassword(rs.getString("password"));
						employee.setRole(rs.getString("role"));
						employeeslist.add(employee);
					}
				 } catch (SQLException e) {
					    e.printStackTrace();
				}finally{
					ConnectionFactory.closeConnection();
				}
				 return employeeslist;
			 }
			 
			 
		  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingParticipantsDAO dao=new MeetingParticipantsDAO();
//		dao.insert(5, 8);
//		dao.insert(5, 9);
//		dao.insert(5, 10);
		
//		List<Meeting> list=dao.selectAllMeetingsByParId(8);
//		 for(Meeting meeting:list){
//			 System.out.println(meeting);
//		 }
		
//		List<Employee> list=dao.selectAllEmployeesByMeetingId(6);
//		for(Employee e:list){
//			System.out.println(e);
//	
		
//		Timestamp now=new Timestamp(System.currentTimeMillis());
//		System.out.println(now.toString());
		
		List<Meeting> list=dao.selectAllCanceledMeetings(8);
		for(Meeting m:list){
			System.out.println(m);
		}
		
		System.out.println("================");
		List<Meeting> list2=dao.selectAllNewMeetings(8);
		for(Meeting m:list2){
			System.out.println(m);
		}

	}

}
