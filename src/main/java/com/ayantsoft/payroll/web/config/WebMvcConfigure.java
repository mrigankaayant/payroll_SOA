package com.ayantsoft.payroll.web.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ayantsoft.payroll" })
public class WebMvcConfigure extends WebMvcConfigurerAdapter {
	private Logger log = Logger.getLogger(WebMvcConfigure.class);

	public WebMvcConfigure() {
		log.info("WebMvcConfigure OBJECT CREATED");
	}

	public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		try {
			MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new Hibernate4Module());
			messageConverter.setObjectMapper(objectMapper);

			return messageConverter;
		} catch (Exception e) {
			log.error("jacksonMessageConverter ERROR : ", e);
			return null;
		}
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		try {
			converters.add(jacksonMessageConverter());
			super.configureMessageConverters(converters);
		} catch (Exception e) {
			log.error("configureMessageConverters ERROR : ", e);
		}
	}
}