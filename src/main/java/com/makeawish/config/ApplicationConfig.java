package com.makeawish.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;

import com.makeawish.converters.StringToEnumConverter;
import com.makeawish.interceptor.LoggingInterceptor;

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

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// addPatterns means invoking the interceptor for a particular URL pattern. * in
		// this case meaning for all requests.
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
		registry.addInterceptor(new ThemeChangeInterceptor());//theme
		registry.addInterceptor(new LocaleChangeInterceptor());//locale
	}
	
	/**
	 *
	 */
	@Override
	@Bean
	public ThemeResolver themeResolver() {
		CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
		cookieThemeResolver.setCookieName("theme");
		cookieThemeResolver.setDefaultThemeName("client-theme-1");
		return cookieThemeResolver;
	}
	
	@Bean
	@Override
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.US);
		cookieLocaleResolver.setCookieName("locale");
		return cookieLocaleResolver;
	}
	
}
