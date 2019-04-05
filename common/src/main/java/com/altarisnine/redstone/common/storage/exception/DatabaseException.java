package com.altarisnine.redstone.common.storage.exception;

import com.altarisnine.redstone.common.storage.StorageType;

public class DatabaseException extends StorageException {
    public DatabaseException(String message) {
        super(message, StorageType.DATABASE);
    }
}
