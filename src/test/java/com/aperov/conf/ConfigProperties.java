package com.aperov.conf;

import java.io.IOException;
import java.util.Properties;

/**
 * Copyright (c) 2022.
 * Class for working with config file (config.properties)
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */
public final class ConfigProperties {
    private static final String DRIVER_TYPE = "driver.type";
    private static final String UI_URL = "ui_url";
    private static final String API_URL = "api_url";
    private static final String MAX_WAIT_TIME = "max_wait_time";
    private static ConfigProperties instance = null;
    private Properties props;

    private ConfigProperties() {
        try {
            props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration file", e);
        }
    }

    public static ConfigProperties getInstance() {
        if (instance == null) {
            instance = new ConfigProperties();
        }
        return instance;
    }


    private String getProperty(final String key) {
        return props.getProperty(key);
    }


    public String getDriverType() {
        return getProperty(DRIVER_TYPE);
    }


    public String getUIEnvAddress() {
        return getProperty(UI_URL);
    }
    public String getApiEnvAddress() {
        return getProperty(API_URL);
    }
    public int getMaxWaitTime() {
        return Integer.parseInt(getProperty(MAX_WAIT_TIME)) * 1000;
    }

}
