package io.cloudtype.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BoardBreakApplication {

	public static void main(String[] args) {

		SpringApplication.run(BoardBreakApplication.class, args);
	}

}
