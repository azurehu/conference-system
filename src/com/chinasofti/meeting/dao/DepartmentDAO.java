package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Department;

public class DepartmentDAO {
//	 DAO类关联连接工厂类
	 private Connection conn;	
	 
//	 查询所有部门信息，返回到集合中
	 public List<Department> selectAll(){
		 conn=ConnectionFactory.getConnection();
		 List<Department> departmentsList=new ArrayList<Department>();
		 try {
				Statement st=null;
				String sql="select * from department";
		 		st = conn.createStatement();
				ResultSet rs =st.executeQuery(sql);
				Department department;
				while(rs.next()){
					department=new Department();
					department.setDepartmentid(rs.getString("departmentid"));
					department.setDepartmentname(rs.getString("departmentname"));
					departmentsList.add(department);
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}
		 return departmentsList;
	 }
	 
//	 向表department中插入记录
	  public void insert(String departmentname){
		  conn=ConnectionFactory.getConnection();
		  String sql="insert into department (departmentname) values(?)";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departmentname);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	  
//	 根据id删除一个部门
	  public void delete(int departmentid){
		  conn=ConnectionFactory.getConnection();
		  String sql="delete from department where departmentid=?";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departmentid);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}	  
	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DepartmentDAO dao=new DepartmentDAO();
//		List<Department> list=dao.selectAll();
//		for(Department d:list){
//			System.out.println(d);
//		}
		
//		dao.insert("管理部");
		dao.delete(6);

	}

}
