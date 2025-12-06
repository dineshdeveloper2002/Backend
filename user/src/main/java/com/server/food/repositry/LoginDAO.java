package com.server.food.repositry;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.server.food.constants.MongoDBConnection;
import com.server.food.model.Login;


@Repository
public class LoginDAO{
	
	@Autowired
	private MongoDBConnection mongoConnection;
	
	
	public Document authenicateUser(Login login) {
		
		Document query = new Document();
		query.append("username", login.getUsername());
		Document result = mongoConnection.getCollection("users").find(query).first();
		return result;
	
	}

//	public boolean authenicateUser(Login login) {
//		
//		Document query = new Document();
//		query.append("UPI", "Paid");
//		mongoConnection.getCollection("UPI").insertOne(query);
//		
//		System.out.println("Database details captured");
//		return true;
//	}
	
}
