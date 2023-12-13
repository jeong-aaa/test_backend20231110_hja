package com.hk.board.dtos;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Alias(value="calDto")
public class CalDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	private String mdate;
	private Date regdate;
	private int deptno;
	
//	private List<DeptDto> deptDto;
	private DeptDto deptDto;
	
	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CalDto(int seq, String id, String title, String content, String mdate, Date regdate, int deptno,
			DeptDto deptDto) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.mdate = mdate;
		this.regdate = regdate;
		this.deptno = deptno;
		this.deptDto = deptDto;
	}

//	public CalDto(int seq, String id, String title, String content, String mdate, Date regdate, List<DeptDto> deptDto) {
//		super();
//		this.seq = seq;
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.mdate = mdate;
//		this.regdate = regdate;
//		this.deptDto = deptDto;
//	}

	public DeptDto getDeptDto() {
		return deptDto;
	}

	public void setDeptDto(DeptDto deptDto) {
		this.deptDto = deptDto;
	}

	public int getSeq() {
		return seq;
	}

	
   public void setSeq(int seq) {
      this.seq = seq;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getMdate() {
      return mdate;
   }

   public void setMdate(String mdate) {
      this.mdate = mdate;
   }

   public Date getRegdate() {
      return regdate;
   }

   public void setRegdate(Date regdate) {
      this.regdate = regdate;
   }

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "CalDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", mdate=" + mdate
				+ ", regdate=" + regdate + ", deptno=" + deptno + ", deptDto=" + deptDto + "]";
	}
	

//	public List<DeptDto> getDeptDto() {
//		return deptDto;
//	}
//
//	public void setDeptDto(List<DeptDto> deptDto) {
//		this.deptDto = deptDto;
//	}

	
	
}

