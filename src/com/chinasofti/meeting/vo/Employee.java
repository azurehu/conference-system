package com.chinasofti.meeting.vo;

public class Employee {
	private Integer employeeid;
	private String employeename;
	private String username;
	private String password;
	private Integer departmentid;
	private String email;
	private String phone;
	
//	status表示员工的状态，0表示正在审核，1表示审核通过，2表示审核未通过，默认为正在审核
	private String status="0";
	
//	role表示员工的角色，1表示为管理员，2表示为员工
	private String role="2";
	
	public Employee() {
		super();
	}
	
	public Employee(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public Employee(String employeename, String username, String password,
			Integer departmentid, String email, String phone, String status,
			String role) {
		super();
		this.employeename = employeename;
		this.username = username;
		this.password = password;
		this.departmentid = departmentid;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.role = role;
	}

	public Employee(Integer employeeid, String employeename, String username,
			String password, Integer departmentid, String email, String phone,
			String status, String role) {
		super();
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.username = username;
		this.password = password;
		this.departmentid = departmentid;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.role = role;
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

//	覆盖toString方法，方便代码测试过程中，打印输出Employee对象查看
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeid: "+employeeid+"  username: "+username+"  password: "+password+" " +
				"status: "+status+" role: "+role;
				
	}
	
	
}
