package com.kmartita.config;

import com.kmartita.config.data.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Objects;

public class ConfigReader {

    private final static Configuration CONFIGURATION = new Yaml().loadAs(getResourceAsStream(), Configuration.class);

    public static Configuration getConfig() {
        return CONFIGURATION;
    }

    public static final String BASE_URL = Objects.requireNonNull(getConfig().getApi().getUrl(),
            "'api.url' property is not provided");
    public static final String TOKEN = Objects.requireNonNull(getConfig().getApi().getToken(),
            "'api.token' property is not provided");

    private static InputStream getResourceAsStream() {
        return ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/local.yaml");
    }
}
