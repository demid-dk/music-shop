package com.shop.music_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.shop.music_shop.repo")
public class MusicShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(MusicShopApplication.class, args);
	}

}
