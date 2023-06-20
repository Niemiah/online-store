package com.solvd.online.store.util.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonParser {
    private ObjectMapper mapper;

    public JsonParser() {
        mapper = new ObjectMapper();
    }

    //Deserialization of a JSON file into a Java object.
    public <T> T readValue(String filePath, Class<T> valueType) {
        T obj = null;
        try {
            obj = mapper.readValue(new File(filePath), valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Serialization of a Java object into JSON file.
    public <T> void writeValue(String filePath, T value) {
        try {
            mapper.writeValue(new File(filePath), value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}