package com.crash;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.Arrays;
//import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CrashCourseApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CrashCourseApplication.class, args);
		
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(CrashCourseApplication.class, args);
//		Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
