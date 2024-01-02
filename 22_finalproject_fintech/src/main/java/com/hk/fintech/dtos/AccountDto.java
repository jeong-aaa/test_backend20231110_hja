package com.hk.fintech.dtos;

public class AccountDto {

    private String useremail;
    private String tran_date;       // 날짜를 문자열로 표현
    private String inout_type;
    private int tran_amt;           // 거래 금액을 정수로 표현
    private String print_content;

    // 기본 생성자
    public AccountDto() {
        super();
    }

    // 매개변수가 있는 생성자
    public AccountDto(String useremail, String tran_date, String inout_type, int tran_amt, String print_content) {
        super();
        this.useremail = useremail;
        this.tran_date = tran_date;
        this.inout_type = inout_type;
        this.tran_amt = tran_amt;
        this.print_content = print_content;
    }

    // Getter 및 Setter 메서드

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

    // 디버깅 및 로깅을 위한 toString 메서드

    @Override
    public String toString() {
        return "AccountDto [useremail=" + useremail + ", tran_date=" + tran_date + ", inout_type=" + inout_type
                + ", tran_amt=" + tran_amt + ", print_content=" + print_content + "]";
    }
}
