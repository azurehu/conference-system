package com.chinasofti.meeting.vo;

public class Department {
	private String departmentid;
	private String departmentname;
	
	public Department() {
		super();
	}
	public Department(String departmentid) {
		super();
		this.departmentid = departmentid;
	}
	public Department(String departmentid, String departmentname) {
		super();
		this.departmentid = departmentid;
		this.departmentname = departmentname;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return departmentid+" "+departmentname;
	}
	
	
	
}
