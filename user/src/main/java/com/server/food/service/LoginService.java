package com.server.food.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.food.model.Login;
import com.server.food.repositry.LoginDAO;


@Service
public class LoginService {
	
	
	@Autowired
	private LoginDAO logindao;

	public boolean authenicateuser( Login login) {
		
		boolean res;
		Document auth = logindao.authenicateUser(login);
		if(auth==null) {
			return false;
		}
		else {
			res= login.getUsername().equals(auth.getString("username"));
		}
		
		return res;
	}
	
	public boolean adduser(Login login) {
		
		boolean res =logindao.addUser(login);
		return res;
	}
}
