package com.ayantsoft.payroll.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date>{
	
	private Logger log = Logger.getLogger(DateJsonDeserializer.class);

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = jsonparser.getText();
		try {
			return format.parse(date);
		} catch (Exception e) {
			log.error("DateJsonDeserializer ERROR");
			throw new RuntimeException(e);
		}
	}
}