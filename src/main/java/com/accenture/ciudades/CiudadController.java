package com.accenture.ciudades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CiudadController {
	@Autowired
	private CiudadService ciudadservice;

	@RequestMapping("/ciudades")
		public List<Ciudad> listarCiudades() {
			return ciudadservice.getAllCiudades();
	}
	 //This method helps to find any information on Service`s JSON using annotation @PathVariable
	@RequestMapping("/ciudad/{id}")
	public Ciudad getETopic (@PathVariable String id){
		return ciudadservice.getETopic(id);
	}
	

}
