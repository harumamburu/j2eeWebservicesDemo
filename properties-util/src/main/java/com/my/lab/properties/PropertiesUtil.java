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

/**
 * Provides a functionality to be able to store properties entries
 * in a map of &lt;String, Properties&gt;, where the String is a classpath reference to the properties file,
 * and fetch those properties values concurrently when needed.
 */
public class PropertiesUtil {

    private static final Map<String, Properties> PROPERTIES = new ConcurrentHashMap<>();

    /**
     * Loads properties file by the given classpath regerence
     * @param xmlConfigClassPath where a properties reader config file is stored on the classpath
     */
    public static void loadProperties(String xmlConfigClassPath) {
        try {
            // TODO: add a possibility to get a caller's classloader to load the properties from
            ClassLoader cLoader = PropertiesUtil.class.getClassLoader();

            // TODO: add xsd validation
            PropertiesType propsConfig = JAXB.unmarshal(cLoader.getResourceAsStream(xmlConfigClassPath), PropertiesType.class);
            for (FileType propsFile : propsConfig.getFile()) {
                String propsFileName = propsFile.getClasspath();

                InputStream propsResource = cLoader.getResourceAsStream(propsFile.getClasspath());
                Properties props = new Properties();
                props.load(propsResource);

                // TODO: add classloading monitoring to be able to coupe with same properties files from different modules
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

    /**
     * Gets a property value by its name, disregarding which file it came from
     * @param name key of a property to get its value
     * @return {@link String} property value or null if none was loaded with such name
     */
    public static String getProperty(String name) {
        /* Get All Properties from the map
         * map them to a single list of entries
         * filter it getting entries with the key provided
         * get first if any and get its optional
         * return optional's value ot null
         */
        // TODO: consider to get back to good-old loops
        return (String) PROPERTIES.values().stream().flatMap(
                properties -> properties.entrySet().stream())
                .filter(v -> v.getKey().equals(name))
                .findFirst().map(objectObjectEntry -> objectObjectEntry.getValue()).orElse(null);
    }

    /**
     * Gets a property value by its name,
     * @param name key of a property to get its value
     * @param fileClassPath classpath reference the the properties file that contained the searched property
     * @return {@link String} property value or null if none was loaded with such name
     */
    public static String getProperty(String name, String fileClassPath) {
        return PROPERTIES.get(fileClassPath).getProperty(name);
    }

}