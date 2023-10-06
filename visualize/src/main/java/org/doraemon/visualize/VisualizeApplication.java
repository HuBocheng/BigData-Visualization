package org.doraemon.visualize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.doraemon.visualize.mapper")
public class VisualizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualizeApplication.class, args);
    }

}
