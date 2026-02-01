package com.server.food.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.server.food.model.Login;
import com.server.food.model.LoginResponse;
import com.server.food.model.User;
import com.server.food.service.LoginService;
import com.server.food.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private LoginService service;
	
	@Autowired
	private UserService userService;

	@PostMapping("api/login")
	public LoginResponse login(@RequestBody Login login ) {
		boolean auth = service.authenicateUser(login);
		LoginResponse res = new LoginResponse();
		res.setSucess(auth?true:false);
		res.setMessage(auth?"Login Sucess":"Unauthorized access");
		return res;
	}
		
	@PostMapping("/signup")
	public LoginResponse signUpUser(@RequestBody Login login) {
		
		LoginResponse res = new LoginResponse();
		boolean checkUser = service.authenicateUser(login);
		if(checkUser==true) {
			res.setMessage("User already exist");
			return res;
		}
		else {
		boolean auth = userService.adduser(login);
		res.setSucess(auth?true:false);
		res.setMessage(auth?"User added succesfully":"Unauthorized access");
		return res;
		}
	}
	
}
