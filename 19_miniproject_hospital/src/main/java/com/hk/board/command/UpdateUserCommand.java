package com.hk.board.command;


public class UpdateUserCommand {


	private String name;	

	private String number;
 
    private String password;
    
    private String id;	

	public UpdateUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateUserCommand(String name, String number, String password, String id) {
		super();
		this.name = name;
		this.number = number;
		this.password = password;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UpdateUserCommand [name=" + name + ", number=" + number + ", password=" + password + ", id=" + id + "]";
	}

	
		
		
		
}
