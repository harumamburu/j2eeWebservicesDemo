package com.my.lab.properties;

import com.my.lab.properties.exception.UnmarshallingException;
import com.my.lab.properties.xml.FileType;
import com.my.lab.properties.xml.PropertiesType;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsMapContaining.hasValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class PropertiesUtilTest {

    private final static String PROP_CONFIG_NAME = "props.xml";
    private final static String PROP_FILE_NAME = "property.properties";

    @Spy
    private PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
    private static Properties testProperties;
    private static String key = "key";
    private static String value = "value";

    @BeforeClass
    public static void loadTestProperties() throws IOException {
        testProperties = new Properties();
        testProperties.put(key, value);
    }

    @Test
    public void testPropertiesUtilWithSchemaInstantiation() throws NoSuchFieldException, IllegalAccessException {
        JAXBContext context = (JAXBContext) getFiledValue(PropertiesUtil.class, "CONTEXT", propertiesUtil);
        Schema schema = (Schema) getFiledValue(PropertiesUtil.class, "SCHEMA", propertiesUtil);

        assertThat(Arrays.asList(context, schema), everyItem(not(is(nullValue()))));
    }

    @Test
    public void testPropertiesLoadingFromXmlDescriptor() throws Exception {
        FileType fileType = mock(FileType.class);
        when(fileType.getResourcePath()).thenReturn(PROP_FILE_NAME);
        PropertiesType propsConfig = mock(PropertiesType.class);
        when(propsConfig.getFiles()).thenReturn(Arrays.asList(fileType));

        doReturn(propsConfig).when(propertiesUtil).
                getPropertiesConfig(any(ClassLoader.class), eq(PROP_CONFIG_NAME));
        doReturn(testProperties).when(propertiesUtil).loadProperties(any(ClassLoader.class), eq(PROP_FILE_NAME));
        doReturn(PROP_FILE_NAME).when(propertiesUtil).getPropertiesFileSignature(PROP_FILE_NAME);

        propertiesUtil.loadProperties(PROP_CONFIG_NAME);
        Map<String, Properties> properties = (Map) getFiledValue(PropertiesUtil.class, "PROPERTIES", propertiesUtil);

        assertThat(properties, hasValue(testProperties));
    }

    @Test
    public void testPropertiesConfigDescriptorLoading() throws UnmarshallingException {
        PropertiesType propertiesConfig = propertiesUtil.
                getPropertiesConfig(Thread.currentThread().getContextClassLoader(), PROP_CONFIG_NAME);
        List<String> propFiles = propertiesConfig.getFiles()
                .stream().map(FileType::getResourcePath).collect(Collectors.toList());
        assertThat(propFiles, allOf(containsInAnyOrder(PROP_FILE_NAME), hasSize(1)));
    }

    @Test
    public void testPropertyLoadingByName() throws Exception {
        Map<String, Properties> properties = mock(Map.class);
        properties.put(PROP_FILE_NAME, testProperties);

        assertEquals(value, propertiesUtil.getProperty(key));
    }

    @Test
    public void testPropertyLoadingByNameAndFileName() throws Exception {
        Map<String, Properties> properties = mock(Map.class);
        properties.put(PROP_FILE_NAME, testProperties);
        doReturn(PROP_FILE_NAME).when(propertiesUtil).getPropertiesFileSignature(PROP_FILE_NAME);

        assertEquals(value, propertiesUtil.getProperty(key, PROP_FILE_NAME));
    }

    private Object getFiledValue(Class<?> targetClass, String fieldName, Object target)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}