package com.makeawish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;

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
		internalResourceViewResolver.setOrder(2);
		return internalResourceViewResolver;
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEnumConverter());
		super.addFormatters(registry);
	}

	/**
	 * Override this method to customize/configure your own AsyncTaskExecutor
	 **/
	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(5000);
		configurer.setTaskExecutor(getMvcTaskExecutor());
	}

	/**
	 * @return {@code AsyncTaskExecutor}
	 */
	@Bean
	public AsyncTaskExecutor getMvcTaskExecutor() {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		poolTaskExecutor.setThreadNamePrefix("health-plus-plus-");
		return poolTaskExecutor;
	}

	@Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver viewResolver = new XmlViewResolver();
		viewResolver.setLocation(new ClassPathResource("views.xml"));
		viewResolver.setOrder(1);
		return viewResolver;
	}
}
