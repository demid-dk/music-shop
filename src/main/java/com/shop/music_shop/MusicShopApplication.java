package com.shop.music_shop;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaRepositories("com.shop.music_shop.repo")
public class MusicShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(MusicShopApplication.class, args);
	}

}
