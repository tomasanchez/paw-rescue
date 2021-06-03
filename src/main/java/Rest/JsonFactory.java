package Rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFactory {

  private ObjectMapper objectMapper;

  public JsonFactory() {
    this.objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public String toJson(Object object) {
    try {
      String jsonString = this.objectMapper.writeValueAsString(object);
      return jsonString;
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error creating a json", e);
    }
  }

  public <T> T fromJson(String json, Class<T> typeReference) {
    try {
      return this.objectMapper.readValue(json, typeReference);
    } catch (IOException e) {

      throw new RuntimeException("Error reading a json", e);
    }
  }
  
  

}
