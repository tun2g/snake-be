package com.example.tram.util;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void copyNonNullFields(Object source, Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
