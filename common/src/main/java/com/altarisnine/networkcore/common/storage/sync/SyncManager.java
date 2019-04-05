package com.altarisnine.networkcore.common.storage.sync;

import com.altarisnine.networkcore.api.storage.StorageGroup;
import com.altarisnine.networkcore.api.storage.sync.Sync;
import com.altarisnine.networkcore.common.NetworkCore;
import lombok.Getter;
import redis.clients.jedis.Jedis;

public final class SyncManager implements Sync {
    private NetworkCore plugin;

    @Getter private Jedis jedis;

    public SyncManager(NetworkCore instance) {
        plugin = instance;
    }

    public void init() {
        plugin.getLogger().info("Connection to Redis ServerInfo...");

        // Create Jedis connection
        jedis = new Jedis("localhost");
    }

    public void close() {
        plugin.getLogger().info("Closing Redis Connection...");

        // Close the connection
        jedis.close();
    }

    @Override
    public void writeGroup(StorageGroup group) {
        group.getData().forEach((field, value) -> writeGroupValue(group.getNamespace(), group.getId(), field, value.getValue()));
    }

    @Override
    public StorageGroup readGroup(String namespace, String id) {
        // Create a storage group with the namespace and id
        StorageGroup group = new StorageGroup(namespace, id);

        // Iterate over the values and add to group
        jedis.hkeys(group.getAddress()).forEach(key -> group.set(key, readGroupValue(namespace, id, key), true));

        return group;
    }

    @Override
    public void writeGroupValue(String namespace, String id, String field, String value) {
        jedis.hset(StorageGroup.addressFor(namespace, id), field, value);
    }

    @Override
    public String readGroupValue(String namespace, String id, String field) {
        return jedis.hget(StorageGroup.addressFor(namespace, id), field);
    }

    @Override
    public void writeValue(String field, String value) {
        jedis.set(field, value);
    }

    @Override
    public String readValue(String field) {
        return jedis.get(field);
    }
}
