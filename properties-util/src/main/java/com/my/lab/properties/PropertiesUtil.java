package com.my.lab.properties;

import com.my.lab.properties.xml.PropertiesType;
import com.my.lab.properties.xml.FileType;

import javax.xml.bind.JAXB;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesUtil {

    private static final Map<String, Properties> PROPERTIES = new ConcurrentHashMap<>();

    public static void loadProperties(String xmlConfig) {
        try {
            ClassLoader cLoader = PropertiesUtil.class.getClassLoader();

            PropertiesType propsConfig = JAXB.unmarshal(cLoader.getResourceAsStream(xmlConfig), PropertiesType.class);
            for (FileType propsFile : propsConfig.getFile()) {
                String propsFileName = propsFile.getClasspath();

                InputStream propsResource = cLoader.getResourceAsStream(propsFile.getClasspath());
                Properties props = new Properties();
                props.load(propsResource);

                if (!PROPERTIES.containsKey(propsFileName)) {
                    PROPERTIES.put(propsFileName, props);
                } else {
                    PROPERTIES.get(propsFileName).putAll(props);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        /* Get All Properties from the map
         * map them to a single list of entries
         * filter it getting entries with the key provided
         * get first if any and get its optional
         * return optional's value ot null
         */
        return (String) PROPERTIES.values().stream().flatMap(
                properties -> properties.entrySet().stream())
                .filter(v -> v.getKey().equals(name))
                .findFirst().map(objectObjectEntry -> objectObjectEntry.getValue()).orElse(null);
    }

    public static String getProperty(String name, String fileClassPath) {
        return PROPERTIES.get(fileClassPath).getProperty(name);
    }

}