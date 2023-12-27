package com.hk.fintech.dtos;

import java.util.Date;

public class AccountDto {

	private String useremail;
	private Date tran_date;
	private String inout_type;
	private int tran_amt;
	private String print_content;
	private int after_balance_amt;
	private int acc_sum;
	
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDto(String useremail, Date tran_date, String inout_type, int tran_amt, String print_content,
			int after_balance_amt, int acc_sum) {
		super();
		this.useremail = useremail;
		this.tran_date = tran_date;
		this.inout_type = inout_type;
		this.tran_amt = tran_amt;
		this.print_content = print_content;
		this.after_balance_amt = after_balance_amt;
		this.acc_sum = acc_sum;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public Date getTran_date() {
		return tran_date;
	}

	public void setTran_date(Date tran_date) {
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

	public int getAfter_balance_amt() {
		return after_balance_amt;
	}

	public void setAfter_balance_amt(int after_balance_amt) {
		this.after_balance_amt = after_balance_amt;
	}

	public int getAcc_sum() {
		return acc_sum;
	}

	public void setAcc_sum(int acc_sum) {
		this.acc_sum = acc_sum;
	}

	@Override
	public String toString() {
		return "AccountDto [useremail=" + useremail + ", tran_date=" + tran_date + ", inout_type=" + inout_type
				+ ", tran_amt=" + tran_amt + ", print_content=" + print_content + ", after_balance_amt="
				+ after_balance_amt + ", acc_sum=" + acc_sum + "]";
	}
	
	
	
}
