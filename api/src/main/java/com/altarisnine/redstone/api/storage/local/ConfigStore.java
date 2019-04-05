package com.altarisnine.redstone.api.storage.local;

import com.altarisnine.redstone.api.configuration.Configuration;
import com.altarisnine.redstone.api.configuration.serialization.ConfigSerializable;

public interface ConfigStore extends ConfigSerializable {

    void writeToConfiguration(Configuration configuration, String path);

}
