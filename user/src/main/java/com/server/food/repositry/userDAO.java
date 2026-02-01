package com.server.food.repositry;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.server.food.constants.MongoDBConnection;
import com.server.food.model.User;

@Repository
public class userDAO {
	
	@Autowired
	private MongoDBConnection mongoconnection;
	public boolean saveUser(User user) {
		Document query = new Document();
		query.append("employeeID",user.employeeID);
		query.append("username", user.username);
		query.append("email",user.email);
		query.append("role", user.role);
		mongoconnection.getCollection("users").insertOne(query);
		System.out.println("User Added sucessfully");
		return true;
	}
	
	public boolean deleteUser(User user) {
		System.out.println("User removed sucessfully");
		return true;
	}
	
	public List<User> getUser(User user) {
		FindIterable<Document> res =mongoconnection.getCollection("users").find();
		List<User> results = new ArrayList<User>();
		for(Document ele:res) {
			User userdetails = new User();
			userdetails.setId((ele.getObjectId("_id")).toString());
			userdetails.setEmployeeID(ele.getString("employeeID"));
			userdetails.setEmail(ele.getString("email"));
			userdetails.setRole(ele.getString("role"));
			userdetails.setUsername(ele.getString("username"));
			results.add(userdetails);
		};
		return results;
	}
	
	public Boolean updateUser(User user){
		Document filterquery = new Document("_id",new ObjectId(user.getId()));
		Document fields = new Document();
		fields.append("employeeID", user.getEmployeeID());
		fields.append("email", user.getEmail());
		fields.append("role", user.getRole());
		fields.append("username", user.getUsername());
		Document updateFilter = new Document("$set",fields);
		mongoconnection.getCollection("users").updateOne(filterquery, updateFilter);
		return true;
	}
	
	public List<User> SearchUser(ObjectId id) {

		if(id==null) {
			FindIterable<Document> data = mongoconnection.getCollection("users").find();
			List<User> list = new ArrayList<User>();
			for(Document res:data) {
				User userdetails = new User();
				userdetails.setId(res.getObjectId("_id").toString());
				userdetails.setEmployeeID(res.getString("employeeID"));
				userdetails.setEmail(res.getString("email"));
				userdetails.setRole(res.getString("role"));
				userdetails.setUsername(res.getString("username"));
				list.add(userdetails);
			}
			return list;
		}
		else {
			
			Document query = new Document("_id",id);
			FindIterable<Document> data = mongoconnection.getCollection("users").find(query);
			List<User> li = new ArrayList<User>();
			for(Document res:data) {
				User userdetails = new User();
				userdetails.setId(res.getObjectId("_id").toString());
				userdetails.setEmployeeID(res.getString("employeeID"));
				userdetails.setEmail(res.getString("email"));
				userdetails.setRole(res.getString("role"));
				userdetails.setUsername(res.getString("username"));
				li.add(userdetails);
			}
			return li;
			
		}
		
	}
}
