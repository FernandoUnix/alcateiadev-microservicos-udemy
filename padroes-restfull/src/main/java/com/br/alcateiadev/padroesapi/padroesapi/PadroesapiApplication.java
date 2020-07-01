package com.br.alcateiadev.padroesapi.padroesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PadroesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesapiApplication.class, args);
	}

}
