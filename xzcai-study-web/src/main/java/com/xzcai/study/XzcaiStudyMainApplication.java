package com.xzcai.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: Mr.Wang
 * @create: 2018-09-29
 **/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class XzcaiStudyMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(XzcaiStudyMainApplication.class,args);
    }
}
