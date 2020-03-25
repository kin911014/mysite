package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.mysite.config.web.MvcConfig;
import com.douzone.security.SecurityConfig;

@Configuration
@ComponentScan({"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import({MvcConfig.class, SecurityConfig.class})
public class WebConfig {

}
