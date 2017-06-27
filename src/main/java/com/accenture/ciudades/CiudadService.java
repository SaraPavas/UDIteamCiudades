package com.accenture.ciudades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CiudadService {
	@Autowired
	private CiudadRepository ciudadrepository;
	
	public List<Ciudad> getAllCiudades () {
		 //return Ciudad;
		 List<Ciudad> Ciudad = new ArrayList<>();
		 ciudadrepository.findAll()
		 .forEach(Ciudad::add);
		 return Ciudad;
	 }
	
	public Ciudad getETopic (String id){
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		 return ciudadrepository.findOne(id);
		}
	public void addCiudad(Ciudad ciudad) {
		//topics.add(topic);
		ciudadrepository.save(ciudad);
		
	}
	
	public void updateCiudad(String id, Ciudad ciudad) {
		ciudadrepository.save(ciudad);
	}
	 
	
}
