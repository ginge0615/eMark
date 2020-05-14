package com.emart.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * @author
 * use:jackson
 */
public class JSONChange {

	public static <T> T jsonToObj(String jsonStr, Class<T> cls) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();	
	    return mapper.readValue(jsonStr, cls);
	}

	public static String objToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}