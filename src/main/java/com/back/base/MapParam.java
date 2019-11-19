package com.back.base;

import java.util.HashMap;
import java.util.HashSet;

public class MapParam extends HashMap<String,Object> {

    /**
     * 作为Key的字段对应MapParam的Key
     */
    public static final String  KEY_FIELD = "mapKeyField";
    /**
     * 作为Value的字段对应MapParam的Key
     */
    public static final String VALUE_FIELD = "mapValueField";

    public MapParam() {

    }

    /**
     * 指定keyField和valueField
     * @param keyField Map中key对应的字段
     * @param valueField Map中value对应的字段
     */
    public MapParam(String keyField, String valueField) {
        this.put(KEY_FIELD, keyField);
        this.put(VALUE_FIELD, valueField);
    }

}
