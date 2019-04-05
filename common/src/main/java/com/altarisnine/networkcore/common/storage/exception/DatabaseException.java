package com.altarisnine.networkcore.common.storage.exception;

import com.altarisnine.networkcore.common.storage.StorageType;

public class DatabaseException extends StorageException {
    public DatabaseException(String message) {
        super(message, StorageType.DATABASE);
    }
}
