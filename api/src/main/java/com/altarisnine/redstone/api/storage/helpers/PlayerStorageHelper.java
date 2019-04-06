package com.altarisnine.redstone.api.storage.helpers;

import com.altarisnine.redstone.api.storage.StorageGroup;
import com.altarisnine.redstone.api.storage.StorageHelper;

import java.util.UUID;

public final class PlayerStorageHelper extends StorageHelper {

    @Override
    public StorageGroup initForSync(StorageGroup group) {
        group.set("displayRank", group.get("group").getValue(), false);
        return group;
    }

    @Override
    public StorageGroup initForDatabase(StorageGroup group) {
        return group.onlyPersistant();
    }

    @Override
    public StorageGroup getDefault() {
        // Create blank storage group for namespace players and with a random uuid;
        StorageGroup group = new StorageGroup("players", UUID.randomUUID().toString());

        group.set("rank", "DEFAULT", true);
        group.set("displayRank", "DEFAULT", true);
        group.set("username", "Notch", true);
        group.set("key", UUID.nameUUIDFromBytes(new byte[]{0}).toString(), true);
        group.set("muted", "false", true);

        return group;
    }
}
