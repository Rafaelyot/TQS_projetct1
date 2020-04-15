package com.air_quality.backend;

import org.apache.commons.io.FileUtils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;


public class TestUtils {


    public static Object get(Object obj, Object getObject, String fieldName, String getterName) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, getObject);
        return obj.getClass().getMethod(getterName).invoke(obj);
    }

    public static Object set(Object obj, Object setObject, Class setterType, String fieldName, String setterName) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        obj.getClass().getMethod(setterName, setterType).invoke(obj, setObject);
        final Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(obj);
    }

    public static String readJsonAsString(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

    }


    public static void assertComparingFieldValues(Object result, Object expected) {
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }


}
