package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
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
