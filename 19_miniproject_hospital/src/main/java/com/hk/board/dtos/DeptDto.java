package com.hk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value="deptDto")
public class DeptDto {
	
	private int deptno;
	private String deptname;
	
	public DeptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DeptDto(int deptno, String deptname) {
		super();
		this.deptno = deptno;
		this.deptname = deptname;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	@Override
	public String toString() {
		return "deptDto [deptno=" + deptno + ", deptname=" + deptname + "]";
	}
	
	
	
}
