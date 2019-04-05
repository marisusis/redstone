package com.altarisnine.redstone.common.storage.exception;

import com.altarisnine.redstone.common.storage.StorageType;

public class SyncException extends StorageException {
    public SyncException(String message) {
        super(message, StorageType.SYNC);
    }
}
