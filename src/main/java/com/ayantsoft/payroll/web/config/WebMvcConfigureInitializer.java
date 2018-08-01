package com.ayantsoft.payroll.web.config;

import javax.servlet.Filter;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebMvcConfigureInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	private Logger log = Logger.getLogger(WebMvcConfigureInitializer.class);
	
	public WebMvcConfigureInitializer(){
		log.error("WebMvcConfigureInitializer OBJECT CREATED");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebMvcConfigure.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	 @Override
	    protected Filter[] getServletFilters() {
	    	Filter [] singleton = { new CORSFilter()};
	    	return singleton;
	    }
}