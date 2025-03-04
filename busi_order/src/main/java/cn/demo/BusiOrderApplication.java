package cn.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.demo.dao")
public class BusiOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusiOrderApplication.class, args);
    }

}
