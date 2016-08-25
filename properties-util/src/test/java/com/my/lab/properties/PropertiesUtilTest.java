package com.my.lab.properties;

import com.my.lab.properties.exception.PropertiesReadingException;
import com.my.lab.properties.exception.UnmarshallingException;
import com.my.lab.properties.xml.FileType;
import com.my.lab.properties.xml.PropertiesType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.validation.Schema;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.collection.IsMapContaining.hasValue;
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
    private PropertiesUtil propertiesUtil;
    private static Properties testProperties;
    private static String key = "key";
    private static String value = "value";

    @BeforeClass
    public static void loadTestProperties() throws IOException {
        testProperties = new Properties();
        testProperties.put(key, value);
    }

    @Test
    public void testPropertiesUtilInstantiationWithSchema() throws NoSuchFieldException, IllegalAccessException {
        JAXBContext context = (JAXBContext) getFiledValue(PropertiesUtil.class, "CONTEXT", propertiesUtil);
        Schema schema = (Schema) getFiledValue(PropertiesUtil.class, "SCHEMA", propertiesUtil);

        assertThat(Arrays.asList(context, schema), everyItem(is(not(nullValue()))));
    }

    @Test
    public void testPropertiesSavingToMap() throws Exception {
        Map<String, Properties> propertiesMap = mockPropertiesSavingAndSave(testProperties);
        assertThat(propertiesMap, hasValue(testProperties));
    }

    @Test
    public void testPropertiesAdditionToMap() throws Exception {
        mockPropertiesSavingAndSave(testProperties);
        Properties additionalTestProperties = (Properties) testProperties.clone();
        additionalTestProperties.put(value, key);
        Map<String, Properties> propertiesMap = mockPropertiesSavingAndSave(additionalTestProperties);

        assertThat(propertiesMap, allOf(hasValue(testProperties), hasValue(additionalTestProperties)));
    }

    private Map<String, Properties> mockPropertiesSavingAndSave(Properties props) throws Exception {
        FileType fileType = mock(FileType.class);
        when(fileType.getResourcePath()).thenReturn(PROP_FILE_NAME);
        PropertiesType propsConfig = mock(PropertiesType.class);
        when(propsConfig.getFiles()).thenReturn(Arrays.asList(fileType));

        doReturn(propsConfig).when(propertiesUtil).
                getPropertiesConfig(any(ClassLoader.class), eq(PROP_CONFIG_NAME));
        doReturn(props).when(propertiesUtil).loadPropertiesFromPropertyFile(any(ClassLoader.class), eq(PROP_FILE_NAME));
        doReturn(PROP_FILE_NAME).when(propertiesUtil).getPropertiesFileSignature(PROP_FILE_NAME);

        propertiesUtil.loadPropertiesFromConfigFile(PROP_CONFIG_NAME);
        return  (Map<String, Properties>) getFiledValue(PropertiesUtil.class, "PROPERTIES", propertiesUtil);
    }

    @Test
    public void testPropertiesConfigDescriptorParsingWithSchema() throws UnmarshallingException {
        PropertiesType propertiesConfig = propertiesUtil.
                getPropertiesConfig(Thread.currentThread().getContextClassLoader(), PROP_CONFIG_NAME);
        String[] propFiles = propertiesConfig.getFiles()
                .stream().map(FileType::getResourcePath).toArray(String[]::new);

        assertThat(propFiles, allOf(hasItemInArray(PROP_FILE_NAME), arrayWithSize(1)));
    }

    @Test(expected = UnmarshallingException.class)
    public void testPropertiesConfigDescriptorParsingFailure() throws UnmarshallingException {
        propertiesUtil.getPropertiesConfig(Thread.currentThread().getContextClassLoader(), "");
    }

    @Test
    public void testPropertiesLoadingFromPropertyFile() throws PropertiesReadingException {
        ClassLoader cLoader = mock(ClassLoader.class);
        when(cLoader.getResourceAsStream(PROP_FILE_NAME)).thenReturn(new ByteArrayInputStream(
                (key + "=" + value).getBytes()));

        Properties props = propertiesUtil.loadPropertiesFromPropertyFile(cLoader, PROP_FILE_NAME);
    }

    @Test(expected = PropertiesReadingException.class)
    public void testPropertiesLoadingFromPropertyFileFailure() throws PropertiesReadingException {
        propertiesUtil.loadPropertiesFromPropertyFile(Thread.currentThread().getContextClassLoader(), "");
    }

    @Test
    public void testPropertyFetchingByName() throws Exception {
        mockPropertiesSavingAndSave(testProperties);
        assertThat(value, is(propertiesUtil.getProperty(key)));
    }

    @Test
    public void testPropertyFetchingByNameAndFileName() throws Exception {
        mockPropertiesSavingAndSave(testProperties);
        assertThat(value, is(propertiesUtil.getProperty(key, PROP_FILE_NAME)));
    }

    private Object getFiledValue(Class<?> targetClass, String fieldName, Object target)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}