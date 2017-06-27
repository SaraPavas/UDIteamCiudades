package com.accenture.ciudades.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.service.CiudadService;





@RestController
public class CiudadController {
	
	@Autowired
	private CiudadService ciudadservice;

	@RequestMapping("/ciudades")
		public List<Ciudad> listarCiudades() {
			return ciudadservice.getAllCiudades();
	}
	 //This method helps to find any information on Service`s JSON using annotation @PathVariable
	@RequestMapping("/ciudades/{id}")
	public Ciudad getETopic (@PathVariable String id){
		return ciudadservice.getETopic(id);
	}
	@RequestMapping (method = RequestMethod.POST, value = "/ciudades")
	public void addTopic(@RequestBody Ciudad ciudad){
		ciudadservice.addCiudad(ciudad);
	}
	@RequestMapping (method = RequestMethod.PUT, value = "/ciudades/{id}")
	public void updateTopic(@RequestBody Ciudad ciudad, @PathVariable String id){
		ciudadservice.updateCiudad(id, ciudad);
	}
	

}
