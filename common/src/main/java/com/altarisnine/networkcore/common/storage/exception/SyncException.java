package com.altarisnine.networkcore.common.storage.exception;

import com.altarisnine.networkcore.common.storage.StorageType;

public class SyncException extends StorageException {
    public SyncException(String message) {
        super(message, StorageType.SYNC);
    }
}
