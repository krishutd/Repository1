package com.example.SpringCloudAwsLambdaDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudAwsLambdaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAwsLambdaDemoApplication.class, args);
	}

}
