package com.cedexis.ads_util;

import java.math.BigInteger;
import java.util.Map;
import java.util.Properties;
import java.util.List;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;


/**
 * Created on 7/10/16.
 */

/*
TODO deal with debug/prod sections
 */
public class YamlConfigRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(YamlConfigRunner.class);
    private final Map<String, Object> map;

    private final Properties properties;

    /**
     * Load 'config.yml' from classpath
     */
    public YamlConfigRunner() {
        this("config.yml");
    }

    /**
     * load <i>configFile</i> from classpath
     * @param configFile Name of yaml config file to load from classpath
     */
    public YamlConfigRunner(String configFile) {
        try {
            properties = new Properties();

            Yaml yaml = new Yaml();

            map = (Map<String, Object>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
        } catch(Exception exception) {
            LOGGER.error("Unable to load config file", exception);
            throw new RuntimeException(String.format("Unable to load config file '%s'", configFile));
        }

        flatten("", map);

    }

    private void flatten(String outerKey, Map<String, Object> flattenMap) {
        flattenMap.forEach((key, val) -> {
            String newKey;
            if(outerKey == "") {
                newKey = key;
            } else {
                newKey = outerKey + "/" + key;
            }

            if(val instanceof LinkedHashMap) {
                Map<String, Object> innerMap = (Map<String, Object>)val;
                flatten(newKey, innerMap);
            } else {
                properties.put(newKey, val);
            }
        });
    }

    private void TypeWarning(String key, String expected, String found) {
        LOGGER.warn("Unable to load value '{}'. Expected type: '{}' Found type: '{}'", key, expected, found);
    }

    /**
     * Returns typeof for given key
     * @param key config key to lookup
     * @return typeof for given key
     */
    public String getValueType(String key) {
        return properties.get(key).getClass().getName();
    }

    /**
     * Get a String value
     * @param key config key to lookup
     * @return String value or null if not found
     */
    public String getString(String key) {
        if(properties.get(key) instanceof String) {
            return (String) properties.get(key);
        } else {
            TypeWarning(key, "String", properties.get(key).getClass().getName());
            return null;
        }
    }

    /**
     * Get an Integer value
     * @param key config key to lookup
     * @return Integer value or null if not found
     */
    public Integer getInt(String key) {
        if(properties.get(key) instanceof Integer) {
            return (Integer) properties.get(key);
        } else {
            TypeWarning(key, "Integer", properties.get(key).getClass().getName());
            return null;
        }
    }

    /**
     * Get a Long value
     * @param key config key to lookup
     * @return Long value or null if not found
     */
    public Long getLong(String key) {
        if(properties.get(key) instanceof Long) {
            return (Long) properties.get(key);
        } else {
            TypeWarning(key, "Long", properties.get(key).getClass().getName());
            return null;
        }
    }

    /**
     * Get a Double value
     * @param key config key to lookup
     * @return Double value or null if not found
     */
    public Double getDouble(String key) {
        if(properties.get(key) instanceof Double) {
            return (Double) properties.get(key);
        } else {
            TypeWarning(key, "Double", properties.get(key).getClass().getName());
            return null;
        }
    }

    /**
     * Get a BigInteger value
     * @param key config key to lookup
     * @return BigInteger value or null if not found
     */
    public BigInteger getBigInt(String key) {
        if(properties.get(key) instanceof BigInteger) {
            return (BigInteger) properties.get(key);
        } else {
            TypeWarning(key, "BigInteger", properties.get(key).getClass().getName());
            return null;
        }
    }

    /**
     * Get a List value
     * @param key config key to lookup
     * @return List value or null if not found
     */
    public List getList(String key) {
        if(properties.get(key) instanceof List) {
            return (List) properties.get(key);
        } else {
            TypeWarning(key, "List", properties.get(key).getClass().getName());
            return null;
        }
    }
}
