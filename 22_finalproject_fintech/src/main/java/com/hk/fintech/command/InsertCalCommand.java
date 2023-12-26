package com.hk.fintech.command;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class InsertCalCommand {
	
	private int seq;
	
	private String useremail;

	@NotBlank(message = "내용을 입력하세요") //문자열만 가능
	private String content;
	
	@NotBlank(message = "거래구분을 선택하세요")
	private String mio;
	

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

	public InsertCalCommand(int seq, String useremail, @NotBlank(message = "내용을 입력하세요") String content,
			@NotBlank(message = "거래구분을 선택하세요") String mio, int money, @NotNull(message = "년도를 입력하세요") int year,
			@NotNull(message = "월을 입력하세요") int month, @NotNull(message = "일을 입력하세요") int date) {
		super();
		this.seq = seq;
		this.useremail = useremail;
		this.content = content;
		this.mio = mio;
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

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMio() {
		return mio;
	}

	public void setMio(String mio) {
		this.mio = mio;
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
		return "InsertCalCommand [seq=" + seq + ", useremail=" + useremail + ", content=" + content + ", mio=" + mio
				+ ", money=" + money + ", year=" + year + ", month=" + month + ", date=" + date + "]";
	}
	
	

	
	
}









