package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jfsoft.mapper")
public class QueueNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueNumberApplication.class);
    }

}
