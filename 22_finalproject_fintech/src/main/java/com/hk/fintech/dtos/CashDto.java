package com.hk.fintech.dtos;

import java.util.Date;

public class CashDto {
	
	private String useremail;
	private String content;
	private String mio;
	private String mdate;
	private int money;
	
	public CashDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CashDto(String useremail, String content, String mio, String mdate, int money) {
		super();
		this.useremail = useremail;
		this.content = content;
		this.mio = mio;
		this.mdate = mdate;
		this.money = money;
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

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "CashDto [useremail=" + useremail + ", content=" + content + ", mio=" + mio + ", mdate=" + mdate
				+ ", money=" + money + "]";
	}

	
	
	
	

}
