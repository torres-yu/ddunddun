package io.torres.ddunddun.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.torres.ddunddun.exception.WERuntimeException;
import io.torres.ddunddun.result.ResultResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class JsonUtils {

    private JsonUtils(){}

    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String toJsonString(Map<String, String> map) throws WERuntimeException {
        String value = null;
        try {
            value = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new WERuntimeException(new ResultResponse(
                    "50099",
                    e.getMessage()
            ));
        }
        return value;
    }

    public static String toJsonString(Object obj) throws WERuntimeException {
        String retValue = null;
        try {
            retValue = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new WERuntimeException(new ResultResponse(
                    "50099",
                    e.getMessage()
            ));
        }
        return retValue;
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(byte [] byteArray, Class<T> type ) throws WERuntimeException {
        T t = null;
        try {
            t = objectMapper.readValue(new InputStreamReader(new ByteArrayInputStream(byteArray)), type);
        } catch ( IOException e) {
            throw new WERuntimeException(new ResultResponse(
                    "50091",
                    e.getMessage()
            ));
        }
        return t;
    }
}
