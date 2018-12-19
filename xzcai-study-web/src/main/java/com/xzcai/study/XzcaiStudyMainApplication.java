package com.xzcai.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: Mr.Wang
 * @create: 2018-09-29
 **/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource({"classpath:spring/*.xml"})
public class XzcaiStudyMainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(XzcaiStudyMainApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(XzcaiStudyMainApplication.class);
    }
}
