package com.my.lab.properties;

import com.my.lab.properties.exception.PropertiesLoadingException;
import com.my.lab.properties.exception.PropertiesReadingException;
import com.my.lab.properties.exception.UnmarshallingException;
import com.my.lab.properties.xml.PropertiesType;
import com.my.lab.properties.xml.FileType;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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
    private static final JAXBContext CONTEXT;
    private static final Schema SCHEMA;
    private static final String SCHEMA_NAME = "properties-loader-config.xsd";

    static {JAXBContext contextTemp = null; Schema schemaTemp = null;
        try {
            contextTemp = JAXBContext.newInstance(PropertiesType.class);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaTemp = schemaFactory.newSchema(PropertiesUtil.class.getClassLoader().getResource(SCHEMA_NAME));
        } catch (JAXBException | SAXException exc) {
            contextTemp = null;
            schemaTemp = null;
        } finally {
            CONTEXT = contextTemp;
            SCHEMA = schemaTemp;
        }
    }

    /**
     * Loads properties file by the given resourcePath reference
     * @param xmlConfigClassPath where a properties reader config file is stored on the classLoader path
     */
    public static void loadProperties(String xmlConfigClassPath) throws PropertiesLoadingException {
        ClassLoader cLoader = Thread.currentThread().getContextClassLoader();

        // get an object representation of xml file describing properties
        PropertiesType propsConfig = getPropertiesConfig(cLoader, xmlConfigClassPath);

        // for each property from properties-describing xml
        for (FileType propsFile : propsConfig.getFile()) {
            // Load properties
            String propertyResourcePath = propsFile.getResourcePath();
            Properties props = loadProperties(cLoader, propertyResourcePath);

            // save the properties or add to existing ones (will override key matches)
            String propsFileReference = getPropertiesFileSignature(propertyResourcePath);
            if (!PROPERTIES.containsKey(propsFileReference)) {
                PROPERTIES.put(propsFileReference, props);
            } else {
                PROPERTIES.get(propsFileReference).putAll(props);
            }
        }
    }

    /**
     * Gets props file name + classloader string representation
     * to allow same file names for different classloaders
     * @param propsFile
     * @return
     */
    private static String getPropertiesFileSignature(String propsFile) {
        // TODO: consider to remove
        ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
        return propsFile + "_" + cLoader.toString();
    }

    /**
     * Loads properties from classloader's resource stream
     * @param cLoader classloader to get resource stream from
     * @param propertyResourcePath properties file path for the classloader
     * @return loaded {@link Properties}
     * @throws PropertiesReadingException
     */
    private static Properties loadProperties(ClassLoader cLoader, String propertyResourcePath) throws PropertiesReadingException {
        try {
            InputStream propsResource = cLoader.getResourceAsStream(propertyResourcePath);
            Properties props = new Properties();
            props.load(propsResource);
            return props;
        } catch (IOException exc) {
            throw new PropertiesReadingException("failed to read properties from " + propertyResourcePath, exc);
        }
    }

    /**
     * Gets the resource stream from a passed classloader
     * and tries to unmarshal properties-describing xml from that stream
     * @param cLoader contextual classloader
     * @param xmlConfigClassPath path to a xml describing properties files
     * @return object representation of properties-config xml
     * @throws UnmarshallingException
     */
    private static PropertiesType getPropertiesConfig(ClassLoader cLoader, String xmlConfigClassPath)
            throws UnmarshallingException {
        try {
            PropertiesType propsConfig;
            if (CONTEXT == null || SCHEMA == null) {
                propsConfig = JAXB.unmarshal(cLoader.getResourceAsStream(xmlConfigClassPath), PropertiesType.class);
            } else {
                Unmarshaller unmarshaller = CONTEXT.createUnmarshaller();
                unmarshaller.setSchema(SCHEMA);
                propsConfig = (PropertiesType) unmarshaller.unmarshal(cLoader.getResourceAsStream(xmlConfigClassPath));
            }
            return propsConfig;
        } catch (JAXBException e) {
            throw new UnmarshallingException("failed to unmarshall " + xmlConfigClassPath, e);
        }
    }

    /**
     * Gets a property value by its name, disregarding which file it came from
     * @param name key of a property to get its value
     * @return {@link String} property value or null if none was loaded with such name
     */
    public static String getProperty(String name) {
                // Get All Properties from the map
        return (String) PROPERTIES.values().stream().flatMap(
                //map them to a single list of entries
                properties -> properties.entrySet().stream())
                //filter it getting entries with the key provided
                .filter(entry -> entry.getKey().equals(name))
                // get first if any and get its value's optional //return optional's value ot null
                .findFirst().map(optionalEntry -> optionalEntry.getValue()).orElse(null);
    }

    /**
     * Gets a property value by its name,
     * @param name key of a property to get its value
     * @param fileClassPath classpath reference the the properties file that contained the searched property
     * @return {@link String} property value or null if none was loaded with such name
     */
    public static String getProperty(String name, String fileClassPath) {
        return PROPERTIES.get(getPropertiesFileSignature(fileClassPath)).getProperty(name);
    }

}