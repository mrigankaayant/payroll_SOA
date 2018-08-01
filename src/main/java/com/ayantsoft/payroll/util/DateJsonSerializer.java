package com.ayantsoft.payroll.util;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateJsonSerializer extends JsonSerializer<Date>{

	private Logger log = Logger.getLogger(DateJsonSerializer.class);
	
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		try{
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			jgen.writeString(formatter.format(value));
		}catch(Exception e){
			log.error("DateJsonSerializer ERROR");
			throw new RuntimeException(e);
		}
	}
}