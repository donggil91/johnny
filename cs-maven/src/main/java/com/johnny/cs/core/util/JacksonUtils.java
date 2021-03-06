package com.johnny.cs.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JacksonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JacksonUtils(){}

    public static ObjectMapper getMapper(){
        return mapper;
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
