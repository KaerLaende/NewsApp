package com.github.KaerLaende.NewsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestNewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(NewsAppApplication::main).with(TestNewsAppApplication.class).run(args);
	}

}
