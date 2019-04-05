package com.altarisnine.networkcore.common.storage.exception;

import com.altarisnine.networkcore.common.storage.StorageType;

public abstract class StorageException extends RuntimeException {
    protected StorageException(String message, StorageType type) {
        super(message);
    }
}
