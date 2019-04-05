package com.altarisnine.networkcore.common.storage;

import java.util.List;
import java.util.Map;

public interface Storable {

    Map<String, String> getSyncValues();
    Map<String, String> getDatabaseValues();

    List<StorageField> getFields();
}
