package com.example.orders.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;


public class MongoService {
    private MongoClientSettings settings;
    private MongoClient client = null;
    private MongoDatabase database  = null;

    public MongoService(MongoClientSettings settings, String database) {
        this.settings = settings;
        this.client = MongoClients.create(settings);

        if(database != null) this.database = this.client.getDatabase(database);
    }

    public MongoService(ConnectionString connectionString, String database) {
        this(MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .build(), database);
    }

    public MongoService(ConnectionString connectionString) {
        this(MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .build(), null);
    }

    public MongoService(MongoClientSettings settings) {
        this(settings, null);
    }

    public MongoDatabase UseDatabase(String name) {
        this.database = this.client.getDatabase((name));
        return this.database;
    }

    public MongoDatabase GetActiveDatabase() {
        return this.database;
    }

}
