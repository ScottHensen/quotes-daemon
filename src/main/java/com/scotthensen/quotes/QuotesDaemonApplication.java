package com.scotthensen.quotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuotesDaemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesDaemonApplication.class, args);
	}
}
