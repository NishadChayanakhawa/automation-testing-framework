package io.nishadc.automationtestingframework.testngcustomization.beans;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonPropertyBaseClass {
	@SuppressWarnings("unchecked")
	public Map<String,Object> toMap() {
		ObjectMapper mapper=new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.convertValue(this, Map.class);
	}
	
	@Override
	public String toString() {
		try {
			ObjectMapper mapper=new ObjectMapper();
			return mapper
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString(this);
		} catch(IOException e) {
			return "";
		}
	}
}
