package com.lshs.sbtemf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.lshs.sbtemf.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class SbtemfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbtemfApplication.class, args);
	}
}
