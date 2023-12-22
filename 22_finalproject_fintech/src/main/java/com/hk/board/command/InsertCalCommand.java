package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//일정추가 페이지에서 입력내용: ID, 일정날짜, 제목, 내용
//      <select> 2023 11 07 06 10 </select> 선택해서 입력 -> Command
//              DB: seq, id, title, content, mdate, regdate
@Data
public class InsertCalCommand {
	
	private int seq;

//	@NotNull(message = "제목을 입력하세요") //문자열만 가능
//	private int deptno;
//	
	@NotBlank(message = "내용을 입력하세요") //문자열만 가능
	private String content;
	
	private int money;
		
	
	//mdate
	@NotNull(message = "년도를 입력하세요")
	private int year;
	@NotNull(message = "월을 입력하세요")
	private int month;
	@NotNull(message = "일을 입력하세요")
	private int date;
	
	public InsertCalCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertCalCommand(int seq, @NotBlank(message = "내용을 입력하세요") String content, int money,
			@NotNull(message = "년도를 입력하세요") int year, @NotNull(message = "월을 입력하세요") int month,
			@NotNull(message = "일을 입력하세요") int date) {
		super();
		this.seq = seq;
		this.content = content;
		this.money = money;
		this.year = year;
		this.month = month;
		this.date = date;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "InsertCalCommand [seq=" + seq + ", content=" + content + ", money=" + money + ", year=" + year
				+ ", month=" + month + ", date=" + date + "]";
	}

	
	
	
}









