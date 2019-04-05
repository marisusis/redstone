package com.altarisnine.redstone.common.storage.exception;

import com.altarisnine.redstone.common.storage.StorageType;

public abstract class StorageException extends RuntimeException {
    protected StorageException(String message, StorageType type) {
        super(message);
    }
}
