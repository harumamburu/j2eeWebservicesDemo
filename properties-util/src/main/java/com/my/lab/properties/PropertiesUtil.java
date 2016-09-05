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
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides a functionality to be able to store properties entries
 * in a map of &lt;String, Properties&gt;, where the String is a classpath reference to the properties file,
 * and fetch those properties values concurrently when needed.
 */
public class PropertiesUtil {

    private final Map<String, Properties> PROPERTIES = new ConcurrentHashMap<>();
    private final JAXBContext CONTEXT;
    private final Schema SCHEMA;
    private final String SCHEMA_NAME = "properties-loader-config.xsd";

    PropertiesUtil() {
        JAXBContext contextTemp = null;
        Schema schemaTemp = null;
        try {
            contextTemp = JAXBContext.newInstance(PropertiesType.class);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaTemp = schemaFactory.newSchema(PropertiesUtil.class.getClassLoader().getResource(SCHEMA_NAME));
        } catch (JAXBException | SAXException | NullPointerException exc) {
            // Context and Schema might be null, then default JAXB.unmarshal will be used
            contextTemp = null;
            schemaTemp = null;
        } finally {
            CONTEXT = contextTemp;
            SCHEMA = schemaTemp;
        }
    }

    private static final PropertiesUtil INSTANCE = new PropertiesUtil();

    public static PropertiesUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Loads properties file by the given resourcePath reference
     * @param xmlConfigClassPath where a properties reader config file is stored on the classLoader path
     */
    public synchronized void loadPropertiesFromConfigFile(String xmlConfigClassPath) throws PropertiesLoadingException {
        ClassLoader cLoader = Thread.currentThread().getContextClassLoader();

        // get an object representation of xml file describing properties
        PropertiesType propsConfig = getPropertiesConfig(cLoader, xmlConfigClassPath);

        // for each property from properties-describing xml
        for (FileType propsFile : propsConfig.getFiles()) {
            // Load properties
            String propertyResourcePath = propsFile.getResourcePath();
            Properties props = loadPropertiesFromPropertyFile(cLoader, propertyResourcePath);

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
     * Gets the resource stream from a passed classloader
     * and tries to unmarshal properties-describing xml from that stream
     * @param cLoader contextual classloader
     * @param xmlConfigClassPath path to a xml describing properties files
     * @return object representation of properties-config xml
     * @throws UnmarshallingException when properties describing xml unmarshalling fails
     */
    PropertiesType getPropertiesConfig(ClassLoader cLoader, String xmlConfigClassPath)
            throws UnmarshallingException {
        try {
            InputStream propsConfigResource = getClassPathResourceStream(cLoader, xmlConfigClassPath);
            PropertiesType propsConfig;
            if (CONTEXT == null || SCHEMA == null) {
                propsConfig = JAXB.unmarshal(propsConfigResource, PropertiesType.class);
            } else {
                Unmarshaller unmarshaller = CONTEXT.createUnmarshaller();
                unmarshaller.setSchema(SCHEMA);
                propsConfig = (PropertiesType) unmarshaller.unmarshal(propsConfigResource);
            }
            return propsConfig;
        } catch (IOException | JAXBException | IllegalArgumentException e) {
            throw new UnmarshallingException("failed to unmarshall " + xmlConfigClassPath, e);
        }
    }

    /**
     * Loads properties from classloader's resource stream
     * @param cLoader classloader to get resource stream from
     * @param propertyResourcePath properties file path for the classloader
     * @return loaded {@link Properties}
     * @throws PropertiesReadingException when property reading failure occurs
     */
    Properties loadPropertiesFromPropertyFile(ClassLoader cLoader, String propertyResourcePath)
            throws PropertiesReadingException {
        try {
            InputStream propsResource = getClassPathResourceStream(cLoader, propertyResourcePath);
            Properties props = new Properties();
            props.load(propsResource);
            return props;
        } catch (IOException exc) {
            throw new PropertiesReadingException("failed to read properties from " + propertyResourcePath, exc);
        }
    }

    /**
     * Attempts to load a resource as InputStream from a passed ClassLoader
     * @param cLoader to load resource stream from
     * @param resourceName to load
     * @return resource InputStream from a passed ClassLoader
     * @throws IOException in case when resource name is empty or no such resource were found
     */
    private InputStream getClassPathResourceStream(ClassLoader cLoader, String resourceName) throws IOException {
        if (resourceName.isEmpty()) {
            throw new IOException();
        }
        return Optional.ofNullable(cLoader.getResourceAsStream(resourceName)).orElseThrow(IOException::new);
    }

    /**
     * Gets props file name + classloader string representation
     * to allow same file names for different classloaders
     * @param propsFile properties file resource path
     * @return properties file descriptor in the format of <pre><code>propsFile + "_" + cLoader.toString()</code></pre>
     */
    String getPropertiesFileSignature(String propsFile) {
        // TODO: consider removing
        ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
        return propsFile + "_" + cLoader.toString();
    }

    /**
     * Gets a property value by its name, disregarding which file it came from
     * @param name key of a property to get its value
     * @return {@link String} property value or null if none was loaded with such name
     */
    public String getProperty(String name) {
        Properties properties = PROPERTIES.values().stream().
                filter(props -> props.containsKey(name)).
                findFirst().orElse(new Properties());
        return properties.getProperty(name);
    }

    /**
     * Gets a property value by its name,
     * @param name key of a property to get its value
     * @param fileResourcePath classpath reference the the properties file that contained the searched property
     * @return {@link String} property value or null if none was loaded with such name
     */
    public String getProperty(String name, String fileResourcePath) {
        return Optional.ofNullable(PROPERTIES.get(getPropertiesFileSignature(fileResourcePath)))
                .orElse(new Properties()).getProperty(name);
    }

}