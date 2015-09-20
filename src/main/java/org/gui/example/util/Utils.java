package org.gui.example.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;

import java.util.Random;

public class Utils {

    public static final Random RANDOM = new Random();

    public static final long MILLS_DAY = 24L * 3600L * 1000L;

    public static final long MILLS_HOUR = 3600L * 1000L;

    public static final long MILLS_MINUTE = 60L * 1000L;

    public static final ObjectMapper JSON_MAPPER = new ObjectMapper().
            configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

    public static final String toJSON(Object object) {
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static final<S,E> S extend(S origin, E extend){
//    	
//    	return origin;
//    }
}
