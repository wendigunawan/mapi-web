package com.mapi.ihrd;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class IhrdApplication extends SpringBootServletInitializer {

    private static final Logger logger = LogManager.getLogger(IhrdApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IhrdApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IhrdApplication.class);
    }

}
