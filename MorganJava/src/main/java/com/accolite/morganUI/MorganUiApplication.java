package com.accolite.morganUI;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan({"com.accolite.morganUI.*"})
public class MorganUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorganUiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}


}
