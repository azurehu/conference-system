package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.meeting.util.ConnectionFactory;
import com.chinasofti.meeting.vo.Employee;

public class EmployeeDAO {
	
//	DAO类关联连接工厂类
	 private Connection conn;	 
	 
//	 通过用户名和密码查询，返回Employee对象，把查到的所有属性返回，以便后续使用。如果返回值为null，表示用户名密码出错
	 public Employee selectByNamePwd(String username,String pwd){
		 conn=ConnectionFactory.getConnection();
		 Employee employee=null;
		
		 try {
			PreparedStatement st=null;
			String sql="select * from employee where username='"+username+"' and  password='"+pwd+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
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
			}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return employee;
	 }
	 
//	 通过用户名查询，返回Employee对象，把查到的所有属性返回，以便后续使用。如果返回值为null，表示用户名不存在
	 public Employee selectByUsername(String username){
		 conn=ConnectionFactory.getConnection();
		 Employee employee=null;	
		 try {
			 PreparedStatement st=null;
			String sql="select * from employee where username='"+username+"'";
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
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
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return employee;
	 }
	 
//	 通过用户名查询，返回Employee对象，把查到的所有属性返回，以便后续使用。如果返回值为null，表示用户名不存在
	 public Employee selectById(int id){
		 conn=ConnectionFactory.getConnection();
		 Employee employee=null;	
		 try {
			 PreparedStatement st=null;
			String sql="select * from employee where employeeid="+id;
	 		st = conn.prepareStatement(sql);
			ResultSet rs =st.executeQuery(sql);
			if(rs.next()==true){
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
			}
		 } catch (SQLException e) {
			    e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection();
		}
		 return employee;
	 }
	 
//	查询所有正在审核的员工信息，返回到集合中。 
	 public List<Employee> selectAllEmployee(){
		 conn=ConnectionFactory.getConnection();
		 List<Employee> employeeslist=new ArrayList<Employee>();
		 Employee employee=null;	
		 try {
			PreparedStatement st=null;
			String sql="select * from employee where role='2' and status='0'";
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
	 
//		 根据部门查询当前部门的员工
		 public List<Employee> selectEmployeesByDept(int departmentid){
			 conn=ConnectionFactory.getConnection();
			 List<Employee> employeeslist=new ArrayList<Employee>();
			 Employee employee=null;	
			 try {
				PreparedStatement st=null;
				String sql="select * from employee where departmentid="+departmentid;
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
	 
//	 根据姓名、用户名、状态， 查询所有员工信息，返回到集合中。 
	 public List<Employee> selectEmployeesByNameStatus(String employeename,String username,String status){
		 conn=ConnectionFactory.getConnection();
		 
		 List<Employee> employeeslist=new ArrayList<Employee>();
		 Employee employee=null;	
		 try {
			PreparedStatement st=null;
			String sql=null;
			String usernamesql,employeenamesql,statussql;
			
			if(employeename==null||employeename.equals("")){
				employeenamesql="";
			}else{
				employeenamesql=" and employeename='"+employeename+"'";
			}
			
			
			if(username==null||username.equals("")){
				usernamesql="";
			}else{
				usernamesql=" and username='"+username+"'";
			}
				
			
			if(status==null||status.equals("")||status.equals("3")){
				statussql="";
			}else{
				statussql=" and status='"+status+"'";
			}
			
			sql="select * from Employee where role='2' "+usernamesql+employeenamesql+statussql;
			
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
	 
	 
//	 根据姓名、用户名、状态， 查询当前页的员工信息，返回到集合中。 
	 public List<Employee> selectEmployeesOfOnePage(String employeename,String username,String status,int start,int end){
		 conn=ConnectionFactory.getConnection();
		 
		 List<Employee> employeeslist=new ArrayList<Employee>();
		 Employee employee=null;	
		 try {
			PreparedStatement st=null;
			String sql=null;
			String usernamesql,employeenamesql,statussql;
			
			if(employeename==null||employeename.equals("")){
				employeenamesql="";
			}else{
				employeenamesql=" and employeename='"+employeename+"'";
			}
			
			
			if(username==null||username.equals("")){
				usernamesql="";
			}else{
				usernamesql=" and username='"+username+"'";
			}
				
			
			if(status==null||status.equals("")||status.equals("3")){
				statussql="";
			}else{
				statussql=" and status='"+status+"'";
			}
			
//			limit是MySQL中用来分页查询的，第一个int参数表示开始的索引，从0开始，第二个参数表示要查询的条数
			sql="select * from Employee where role='2' "+usernamesql+employeenamesql+statussql+" limit "+start+
					" ,"+end;
			
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
	 
	 
//		更新员工
		  public void updateStatus(int employeeid,String status){
			  conn=ConnectionFactory.getConnection();
			  String sql="update employee set status='"+status+"'where employeeid="+employeeid;
			  try {		
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionFactory.closeConnection();
			}	  
		  }
	 
//	向表employee中插入记录，其中status和role使用默认值
	  public void insert(Employee employee){
		  conn=ConnectionFactory.getConnection();
		  String sql="insert into employee"
				  +
					"(employeename,username,password,phone,email,departmentid,status,role)" +
					" values(?,?,?,?,?,?,?,?)";
		  try {		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,employee.getEmployeename());
			pstmt.setString(2,employee.getUsername());
			pstmt.setString(3,employee.getPassword() );
			pstmt.setString(4,employee.getPhone() );
			pstmt.setString(5,employee.getEmail());
			pstmt.setInt(6,employee.getDepartmentid());			
//			注册成功后，默认为正在审核，status为0
			pstmt.setString(7,"0");
//			注册时，默认为员工角色，role值为2
			pstmt.setString(8,"2");
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
	 * 写代码测试类中的方法
	 */
	public static void main(String[] args) {
		EmployeeDAO dao=new EmployeeDAO();
//		Employee e=dao.selectByNamePwd("wangxh", "1");
//		if(e!=null){
//			System.out.println(e);
//		}else{
//			System.out.println("登录失败");
//		}
//		Employee e=dao.selectByUsername("wangxh");
//		System.out.println(e);
//		
//		dao.insert(new Employee("黄美玲","huangml","1",1,"13567898765","huangml@qq.com","0","2"));
		
//		List<Employee> list=dao.selectAllEmployee();
//		for(Employee e:list){
//			System.out.println(e);
//		}
		
//		dao.updateStatus(11,"2");
		
//		List<Employee> list=dao.selectEmployeesByNameStatus(null, "yangyk", null);
//		for(Employee e:list){
//			System.out.println(e);
//		}
		
//		List<Employee> list=dao.selectEmployeesOfOnePage(null, null, null,3,6);
//		for(Employee e:list){
//			System.out.println(e);
//		}
		
		List<Employee> list=dao.selectEmployeesByDept(2);
		for(Employee e:list){
			System.out.println(e);
		}
	}

}
