package com.douzone.mysite;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.douzone.mysite.BootApp;

// jenkins에서 jar war 파일로 둘다 빌드시켜주는 것 
public class BootInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootApp.class);
	}
}
