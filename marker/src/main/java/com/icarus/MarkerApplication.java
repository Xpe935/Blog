package com.icarus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.icarus.mapper")
public class MarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkerApplication.class, args);
    }

}
