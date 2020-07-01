package org.dell.kube.pages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PageApplication {

	@Bean
	public IPageRepository iPageRepository(){
		return new InMemoryPageRepository();
	}
	public static void main(String[] args) {
		SpringApplication.run(PageApplication.class, args);
	}
}
