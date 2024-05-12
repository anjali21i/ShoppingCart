package com.core.shoppingcartapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingcartapiApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(ShoppingcartapiApplication.class, args);
		
		Object dataSource = context.getBean("dataSource");
		
//		SpringApplication.run(ShoppingcartApplication.class, args);
	}

}
