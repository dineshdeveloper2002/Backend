package com.server.food.controller;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.server.food.model.User;
import com.server.food.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("api/adduser")
	public User addUser(@RequestBody User user) {
		try {
			service.adduser(user);
			System.out.println("user added");
			return user;
		}
		
		catch(Exception e) {
			System.out.println("Exception Occured"+e);
			user.setMessage("Exception occured");
			return user;
		}
	}	
	
	@PostMapping("api/getuser")
	public ResponseEntity<List<User>> getUser(@RequestBody User user) {
		try {
			List<User> users = service.getUserList(user);
			System.out.println("User listed");
			return ResponseEntity.ok(users);
		}
		
		catch(Exception e) {
			System.out.println("Exception occured "+e);
			return null;
		}
	}
	
	@PostMapping("api/updateuserdetails")
	public BodyBuilder updateUserDetails(@RequestBody User user){
		try {
			boolean res = service.updateUser(user);
			return ResponseEntity.ok();	
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@GetMapping("api/users")
	public ResponseEntity<List<User>> searchUser(@RequestParam(required = false) ObjectId id){
		try {
			List<User>res= service.getUserSearch(id);
			return ResponseEntity.ok(res);
		}
		catch(Exception e) {
			System.out.println("Exception occured"+e);
			return null;
		}
	}
	
		
//	@GetMapping("api/users/{id}")
//	public ResponseEntity<List<User>> searchUser(@PathVariable ObjectId id){
//		try {
//			List<User>res= service.getUserSearch(id);
//			return ResponseEntity.ok(res);
//		}
//		catch(Exception e) {
//			System.out.println("Exception occured"+e);
//			return null;
//		}
//	}
//	
	

	
}
