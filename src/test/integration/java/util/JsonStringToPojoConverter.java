package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringToPojoConverter {

    public static <T> T map(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T readValue = null;
        try {
            readValue = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return readValue;
    }
}
