package com.hk.fintech.command;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class AddUserCommand {

	@NotBlank(message = "이름을 입력하세요")
	private String username;
	
	@NotBlank(message = "이메일을 입력하세요")
	private String useremail;

	@NotBlank(message = "비밀번호를 입력하세요")
	@Length(min = 8 , max = 16, message = "8자리이상, 16자이하로 입력하세요")
	private String userpassword;

	public AddUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddUserCommand(@NotBlank(message = "이름을 입력하세요") String username,
			@NotBlank(message = "이메일을 입력하세요") String useremail,
			@NotBlank(message = "비밀번호를 입력하세요") @Length(min = 8, max = 16, message = "8자리이상, 16자이하로 입력하세요") String userpassword) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	@Override
	public String toString() {
		return "AddUserCommand [username=" + username + ", useremail=" + useremail + ", userpassword=" + userpassword
				+ "]";
	}

	

}