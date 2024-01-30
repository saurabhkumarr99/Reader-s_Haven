package com.haven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableCaching
@Configuration
@EnableRedisHttpSession
public class ReadersHavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadersHavenApplication.class, args);
	}
	

}


