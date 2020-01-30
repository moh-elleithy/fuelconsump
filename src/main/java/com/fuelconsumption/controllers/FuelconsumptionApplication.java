package com.fuelconsumption.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

/**
 * The main function which is a starting point for Spring boot
 * 
 * @author mohamed.elleithy
 *
 */

@SpringBootApplication
@ComponentScan({"com.fuelconsumption"})
@EntityScan("com.fuelconsumption.model.entities")
@EnableJpaRepositories("com.fuelconsumption.model.repo")
public class FuelconsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelconsumptionApplication.class, args);
	}

}
