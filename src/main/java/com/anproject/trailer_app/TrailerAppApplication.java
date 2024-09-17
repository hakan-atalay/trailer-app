package com.anproject.trailer_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties
public class TrailerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailerAppApplication.class, args);
	}

}
