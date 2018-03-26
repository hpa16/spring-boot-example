package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("loginPage");
		registry.addViewController("/login").setViewName("loginPage");
		registry.addViewController("/registration").setViewName("registrationPage");
		registry.addViewController("/welcome").setViewName("welcomePage");
	}
}