package com.altarisnine.networkcore.api.configuration.file;

import com.altarisnine.networkcore.api.configuration.InvalidConfigurationException;
import com.altarisnine.networkcore.api.configuration.objects.ConfigurationImpl;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;

public abstract class FileConfiguration extends ConfigurationImpl {

    private File file;

    protected FileConfiguration() {
        super();
    }

    public abstract void loadFromString(String contents) throws InvalidConfigurationException;

    public void write(File file) throws IOException {
        Files.createParentDirs(file);

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8)) {
            writer.write(saveToString());
        }
    }

    public void writeToLast() throws IOException {
        write(file);
    }

    public void load(String file) throws IOException, InvalidConfigurationException {
        this.file = new File(file);
        load(this.file);
    }

    public void load(File file) throws IOException, InvalidConfigurationException {
        this.file = file;
        final FileInputStream stream = new FileInputStream(file);
        load(new InputStreamReader(stream, Charsets.UTF_8));
    }

    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        try (BufferedReader input = (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader)) {
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }

            loadFromString(builder.toString());
        }
    }

    public abstract String saveToString();
}
