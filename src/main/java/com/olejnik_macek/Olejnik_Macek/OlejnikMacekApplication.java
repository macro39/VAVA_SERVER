package com.olejnik_macek.Olejnik_Macek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OlejnikMacekApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlejnikMacekApplication.class, args);
	}

}
