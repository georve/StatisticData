package com.auto.helper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AttrCopy {

    public static void  copyAttributes(Object from, Object to) throws IllegalAccessException {
        Map<String, Field> toFieldNameMap = new HashMap<>();
        for(Field f : to.getClass().getDeclaredFields()) {
            toFieldNameMap.put(f.getName(), f);
        }
        for(Field f : from.getClass().getDeclaredFields()) {
            Field ff = toFieldNameMap.get(f.getName());
            f.setAccessible(true);
            boolean include = f.getDeclaredAnnotation(AttrCopyExclude.class) == null;
            if(include && ff != null && ff.getType().equals(f.getType())) {
                ff.setAccessible(true);
                ff.set(to, f.get(from));
            }
        }
    }
}
