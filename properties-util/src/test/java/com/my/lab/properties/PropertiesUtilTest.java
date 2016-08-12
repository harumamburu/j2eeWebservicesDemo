package com.my.lab.properties;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public final class PropertiesUtilTest {
    @Test
    public void loadProperties() throws Exception {

    }

    @Test
    public void getProperty() throws Exception {

    }

    @Test
    public void getProperty1() throws Exception {

    }

    private Object invokePrivateMethod(Class<?> targetClass, String methodName, Object... argumentObjects) {
        try {
            Method targetMethod = targetClass.getDeclaredMethod(methodName,
                    Stream.of(argumentObjects).map(Object::getClass).toArray(Class[]::new));
            targetMethod.setAccessible(true);
            return targetMethod.invoke(null, argumentObjects);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}