package com.altarisnine.networkcore.api.configuration.file;

public class JsonConfiguration extends FileConfiguration {

    public JsonConfiguration() {
        throw new IllegalStateException("JSON configuration format not implemented yet!");
    }

    @Override
    public void loadFromString(String contents) {

    }

    @Override
    public String saveToString() {
        return null;
    }


}
