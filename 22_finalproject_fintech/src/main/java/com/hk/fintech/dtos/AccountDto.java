package com.hk.fintech.dtos;

public class AccountDto {

    private String useremail;
    private String tran_date; // Date를 String으로 변경
    private String inout_type;
    private int tran_amt; // int를 String으로 변경
    private String print_content;
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountDto(String useremail, String tran_date, String inout_type, int tran_amt, String print_content) {
		super();
		this.useremail = useremail;
		this.tran_date = tran_date;
		this.inout_type = inout_type;
		this.tran_amt = tran_amt;
		this.print_content = print_content;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public String getInout_type() {
		return inout_type;
	}
	public void setInout_type(String inout_type) {
		this.inout_type = inout_type;
	}
	public int getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(int tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	@Override
	public String toString() {
		return "AccountDto [useremail=" + useremail + ", tran_date=" + tran_date + ", inout_type=" + inout_type
				+ ", tran_amt=" + tran_amt + ", print_content=" + print_content + "]";
	}

    
}
