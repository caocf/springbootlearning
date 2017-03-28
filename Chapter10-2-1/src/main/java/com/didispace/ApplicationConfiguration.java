package com.didispace;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.didispace")
public class ApplicationConfiguration 
//			implements WebApplicationInitializer
		{
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}

//	@Override
//	public void onStartup(ServletContext servletContext)
//			throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}

}
