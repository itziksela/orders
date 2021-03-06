package com.example.orders.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoService {
    private MongoClient client = null;
    private MongoDatabase database  = null;

    public MongoService(MongoClientSettings settings, String database) {
        this.client = MongoClients.create(settings);

        if(database != null) this.database = this.client.getDatabase(database);
    }

    public MongoService(ConnectionString connectionString, String database) {
        this(MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build())))
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
