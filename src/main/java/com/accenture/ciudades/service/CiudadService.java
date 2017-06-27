package com.accenture.ciudades.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ciudades.bl.CiudadBl;
import com.accenture.ciudades.dao.CiudadRepository;
import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;



@Service
public class CiudadService implements CiudadBl {
	
	
	private final String ACTIVE_CIUDAD="TRUE";
	private final String NO_ACTIVE_CIUDAD="FALSE";
	
	
	@Autowired
	private CiudadRepository ciudadrepository;
	
	
	@Override
	public List<Ciudad> ListarActivos() throws DaoException {
		//List<Ciudad> ciudad = new ArrayList<>();
		try {
			return ciudadrepository.findByActivo(ACTIVE_CIUDAD);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	

	



	public List<Ciudad> getAllCiudades () {
		 //return Ciudad;
		 List<Ciudad> ciudad = new ArrayList<>();
		 ciudadrepository.findAll()
		 .forEach(ciudad::add);
		 return ciudad;
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
	
	public List <Ciudad> getCiudadbyNombre(String strNombre){
		//Ciudad ciudadEncontrada = new Ciudad();
		try {
			return ciudadrepository.findByNombreContaining(strNombre);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public List<Ciudad> getCiudadByNombreAndDepartamento(String palabra) {
		
		List<Ciudad> ciudad = new ArrayList<>();
		
		try {
			ciudad.addAll(ciudadrepository.findByNombreContaining(palabra));
			ciudad.addAll(ciudadrepository.findByDepartamentoContaining(palabra));
			return ciudad; 
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Ciudad getActiveByIdent(String i){
		try {
			
			return ciudadrepository.findByIdentAndActivo(i, ACTIVE_CIUDAD);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteCiudad(String i){
		
		Ciudad ciudad = new Ciudad();
		try {
			ciudad = ciudadrepository.findByIdentAndActivo(i, ACTIVE_CIUDAD);
			if(ciudad != null){
				ciudad.setActivo(NO_ACTIVE_CIUDAD);
				ciudadrepository.save(ciudad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
}
