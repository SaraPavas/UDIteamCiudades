package com.accenture.ciudades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.accenture.ciudades.controller.CiudadController;

@SpringBootApplication
//@ComponentScan(basePackageClasses=CiudadController.class)
public class CiudadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiudadesApplication.class, args);
	}
}
