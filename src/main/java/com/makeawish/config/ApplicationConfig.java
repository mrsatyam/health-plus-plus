package com.makeawish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.makeawish.converters.StringToEnumConverter;

@Configuration
@ComponentScan(basePackages = "com.makeawish")
public class ApplicationConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("css/**", "images/**").addResourceLocations("classpath:/static/css/",
				"classpath:/static//images/");
	}

	@Bean
	protected InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setViewClass(JstlView.class);
		return internalResourceViewResolver;
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEnumConverter());
		super.addFormatters(registry);
	}
}
