package com.server.food.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.food.model.Login;
import com.server.food.model.User;
import com.server.food.repositry.userDAO;


@Service
public class UserService {

	@Autowired
	public userDAO userdao;
	
   public boolean adduser(User user) {
		
		boolean res = userdao.saveUser(user);
		return res;
	}
   
   public boolean adduser(Login login) {
	   return true;
   }
	
	public boolean updateUser(User user) {
		
		boolean res = userdao.updateUser(user);
		return true;
	}
	
	public boolean deleteUser(User user) {
		
		boolean res = userdao.deleteUser(user);
		return true;
	}
	
	public List<User> getUserList(User user) {
		 List<User> res = userdao.getUser(user);
		 return res;
	}
	
	public List<User> getUserSearch(ObjectId id){
		
		List<User> user = userdao.SearchUser(id);
		return user;
		
	}
	
	
}
