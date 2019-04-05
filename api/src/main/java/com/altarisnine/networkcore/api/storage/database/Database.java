package com.altarisnine.networkcore.api.storage.database;

import com.altarisnine.networkcore.api.storage.StorageGroup;
import org.bson.Document;

import java.util.Set;

/**
 * The interface
 */
public interface Database {

    /**
     * Gets all documents with the specified collection name
     *
     * @param collectionName the collection name
     * @return the all documents
     */
    Set<Document> getAllDocuments(String collectionName);

    /**
     * Write a storage group to MongoDB
     *
     * @param group the group
     */
    void write(StorageGroup group);

    /**
     * Read a storage group by its key from MongoDB
     *
     * @param namespace namespace of the storage group
     * @param id id of the storage group
     * @return the storage group
     */
    StorageGroup read(String namespace, String id);

    void writeGroupValue(String namespace, String id, String field, String value);
    String readGroupValue(String namespace, String id, String field);



}
