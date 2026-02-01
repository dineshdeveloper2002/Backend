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
	
	public boolean addUser(Login login) {
		
		Document query = new Document();
		query.append("username", login.getUsername());
		query.append("password", login.getPassword());
		query.append("firstname", login.getFirstname());
		query.append("lastname", login.getLastname());
		query.append("role", login.getRole());
		query.append("companyname", login.getCompanyName());
		login.setId(query.getObjectId("_id"));
		mongoConnection.getCollection("users").insertOne(query);
		return true;

	}
	
	public boolean editUser(Login login) {
		//find query 
		Document filter = new Document("_id",login.getId());
		Document updateQuery = new Document("$set",new Document("username",login.getUsername()));
		mongoConnection.getCollection("users").updateOne(filter, updateQuery);
		return true;
	}	
	
	
}
