package com.server.food.model;

public class LoginResponse {
	
	public boolean sucess;
	public String message;
	public boolean getSucess() {
		return sucess;
	}
	public void setSucess(Boolean sucess) {
		this.sucess = sucess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
