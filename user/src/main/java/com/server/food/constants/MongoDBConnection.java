package com.server.food.constants;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnection {

    private MongoDatabase database;
    private MongoClient client;

    public MongoDBConnection(@Value("${spring.data.mongodb.database}") String databaseName,
                             @Value("${spring.data.mongodb.uri}") String mongoUri) {
        this.client = MongoClients.create(mongoUri);
        this.database = client.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        MongoDatabase database = getDatabase(databaseName);
        return database.getCollection(collectionName);
    }
    
    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
    
    public MongoDatabase getDatabase(String databaseName) {
        return client.getDatabase(databaseName);
    }
    
    
    public MongoClient getClient() {
        return client;
    }
}