package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class AddUserCommand {
	
	@NotBlank(message = "이름을 입력하세요")
	private String name;
	
	@NotBlank(message = "전화번호를 입력하세요")
	private String num;

    @NotBlank(message = "아이디를 입력하세요")
    private String id;

   @NotBlank(message = "비밀번호를 입력하세요")
   private String password;
 
   public AddUserCommand() {
      super();
      // TODO Auto-generated constructor stub
   }

	public AddUserCommand(@NotBlank(message = "이름을 입력하세요") String name, @NotBlank(message = "전화번호를 입력하세요") String num,
			@NotBlank(message = "아이디를 입력하세요") String id, @NotBlank(message = "비밀번호를 입력하세요") String password) {
		super();
		this.name = name;
		this.num = num;
		this.id = id;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AddUserCommand [name=" + name + ", num=" + num + ", id=" + id + ", password=" + password + "]";
	}

  
}