package com.altarisnine.networkcore.api.storage.local;

import com.altarisnine.networkcore.api.configuration.Configuration;
import com.altarisnine.networkcore.api.configuration.serialization.ConfigSerializable;

public interface ConfigStore extends ConfigSerializable {

    void writeToConfiguration(Configuration configuration, String path);

}
