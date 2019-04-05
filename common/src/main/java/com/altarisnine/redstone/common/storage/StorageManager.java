package com.altarisnine.redstone.common.storage;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.storage.Storage;
import com.altarisnine.redstone.api.storage.StorageGroup;
import com.altarisnine.redstone.api.storage.StorageHelper;
import com.altarisnine.redstone.api.storage.StoredBy;
import com.altarisnine.redstone.common.RedstoneCore;
import org.bson.Document;

import java.util.Set;

public final class StorageManager implements Storage {
    private RedstoneCore core;

    public StorageManager(RedstoneCore instance) {
        core = instance;
    }

    @Override
    public void init(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            init(clazz);
        }
    }

    @Override
    public void init(Class<?> clazz) {
        StoredBy anno = getAnnotation(clazz);

        core.getLogger().debug("Initializing storage type: " + anno.typeIdentifier());

        core.getDatabaseManager().loadType(anno.typeIdentifier());
    }

    @Override
    public void loadAll(Class<?> clazz) {
        StoredBy anno = getAnnotation(clazz);

        // Get the helper and identifier
        Class<? extends StorageHelper> helperClass = anno.helper();
        String typeIdentifier = anno.typeIdentifier();

        Redstone.getApi().getLogger().debug("Attempting to get all documents [" + typeIdentifier + ']');
        Set<Document> documents = core.getDatabaseManager().getAllDocuments(typeIdentifier);

        for (Document document : documents) {
            StorageGroup group = toGroup(typeIdentifier, document);

            try {
                final StorageHelper storageHelper = helperClass.newInstance();
                StorageGroup syncGroup = storageHelper.initForSync(group);
                core.getSyncManager().writeGroup(syncGroup);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private StoredBy getAnnotation(Class<?> clazz) {
        // Get annotation from class
        StoredBy anno = clazz.getAnnotation(StoredBy.class);

        // Check if the annotation actually exists
        if (anno == null) throw new IllegalArgumentException("That is not able to be stored...");

        return anno;
    }


    private StorageGroup toGroup(String namespace, Document document) {
        StorageGroup group = new StorageGroup(namespace, document.getString("key"));

        document.forEach((string, object) -> {
            if (object instanceof String) group.set(string, document.getString(string), true);
        });

        return group;
    }
}
