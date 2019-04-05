package com.altarisnine.networkcore.common.storage.database;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.configuration.ConfigurationSection;
import com.altarisnine.networkcore.api.storage.StorageGroup;
import com.altarisnine.networkcore.api.storage.database.Database;
import com.altarisnine.networkcore.api.storage.database.DatabaseException;
import com.altarisnine.networkcore.common.NetworkCore;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.mongodb.client.model.Filters.eq;

public final class DatabaseManager implements Database {
    private NetworkCore plugin;

    private MongoClient client;
    private MongoDatabase database;

    // MongoDB collections
    private MongoCollection<Document> playerCollection;

    private HashMap<String, MongoCollection<Document>> collections;

    private final ConfigurationSection config;

    public DatabaseManager(NetworkCore instance) {
        this.plugin = instance;
        collections = new HashMap<>();
        config = plugin.getConfig().getSection("mongodb");
    }

    public void init() {
        plugin.getLogger().info("Loading MongoDB...");

        if (config.getBoolean("auth")) {
            // Create MongoDB credentials
//            MongoCredential credential = MongoCredential.createCredential("networkcore", "networkcore", "n3tw0rkc0r3".toCharArray());
            MongoCredential credential = MongoCredential.createCredential(config.getString("username"), config.getString("authdb"), config.getString("password").toCharArray());

            // Create MongoDB client with credentials
            client = MongoClients.create(
                    MongoClientSettings.builder().applyToClusterSettings(
                            builder -> builder.hosts(Collections.singletonList(new ServerAddress(config.getString("host"), config.getInteger("port"))))
                    ).credential(credential)
                            .build()
            );
        } else {
            // Create client without credentials
            // Create MongoDB client
            client = MongoClients.create(
                    MongoClientSettings.builder().applyToClusterSettings(
                            builder -> builder.hosts(Collections.singletonList(new ServerAddress(config.getString("host"), config.getInteger("port"))))
                    )
                            .build()
            );
        }

        // Get the database
        database = client.getDatabase("networkcore");

        // Load the collections
        playerCollection = database.getCollection("players");

        collections.put("players", database.getCollection("players"));
    }

    public void close() {
        plugin.getLogger().info("Closing MongoDB Connection...");
        client.close();
    }

    public void loadType(String key) {
        // Get the collection that holds the type
        MongoCollection<Document> collection = database.getCollection(key);

        // Add the collection to the list of active collections
        collections.put(key, collection);
    }

    public boolean containsPlayer(UUID uuid) {
        AtomicBoolean res = new AtomicBoolean(false);

        playerCollection.find(eq("key", uuid.toString())).forEach((Block<? super Document>) (block) -> res.set(true));

        return res.get();
    }

    public boolean containsPlayerByName(String username) {
        AtomicBoolean res = new AtomicBoolean(false);

        playerCollection.find(eq("username", username)).forEach((Block<? super Document>) (block) -> {
            res.set(true);
        });

        return res.get();
    }

    @Override
    public Set<Document> getAllDocuments(String collectionName) {
        if (!collections.containsKey(collectionName)) throw new DatabaseException("That collection is not registered!");

        Set<Document> result = new HashSet<>();

        MongoCollection<Document> collection = collections.get(collectionName);

        final FindIterable<Document> documents = playerCollection.find();

        for (Document document : documents) {
            result.add(document);
        }

        return result;
    }

    @Override
    public void write(StorageGroup group) {
        group = group.onlyPersistant();

        Core.getApi().getLogger().debug(group.toString());

        // Get the group's namespace and key
        final String namespace = group.getNamespace();
        final String id = group.getId();

        Document document = new Document();

        synchronized (document) {
            group.getData().forEach((k, v) -> {
                document.append(k, v.getValue()); // PERFORMANCE move usage of isPersistent here to prevent creation of new StorageGroup instances by way of #onlyPersistant
            });

            plugin.getApi().getLogger().debug(document.toJson());

            ReplaceOptions options = new ReplaceOptions();

            // Replace with upsert to insert document if it does not exist already
            options.upsert(true);

            if (!collections.containsKey(namespace))
                throw new DatabaseException("that collection does not exist! [" + namespace + ']');

            // Send to database // TODO rename field name from key to id
            collections.get(namespace).replaceOne(eq("key", id), document, options);
        }

    }

    @Override // TODO method
    public StorageGroup read(String namespace, String id) {
        Document document = collections.get(namespace).find(eq("key", id)).first();
        return toGroup(namespace, Objects.requireNonNull(document));
    }

    @Override
    public void writeGroupValue(String namespace, String id, String field, String value) {
        UpdateOptions options = new UpdateOptions();

        options.upsert(true);

        Document document = new Document(field, value);

        collections.get(namespace).updateOne(eq("key", id), document, options);
    }

    @Override
    public String readGroupValue(String namespace, String id, String field) {
        return null;
    }

    private StorageGroup toGroup(String namespace, Document document) {
        StorageGroup group = new StorageGroup(namespace, document.getString("key"));

        document.forEach((string, object) -> {
            if (object instanceof String) group.set(string, document.getString(string), true);
        });

        return group;
    }

}
