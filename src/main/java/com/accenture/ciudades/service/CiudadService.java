package com.accenture.ciudades.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ciudades.bl.CiudadBl;
import com.accenture.ciudades.dao.CiudadRepository;
import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;




@Service
public class CiudadService implements CiudadBl {
	
	
	private final String ACTIVE_CIUDAD="true";
	private final String NO_ACTIVE_CIUDAD="false";
	

	
	
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



	@Override
	public void addNewCiudad(Ciudad ciudad) throws DaoException {
		try {
			String nombre = ciudad.getNombre();
			String departamento = ciudad.getDepartamento();
			String habitantes = ciudad.getHabitantes();
			String importancia = ciudad.getImportancia();
			String gentilicio = ciudad.getGentilicio();

			Ciudad c = new Ciudad();
			c = ciudadrepository.findByNombre(ciudad.getNombre()); 
		
			if(nombre == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el nombre de la ciudad");
			if(departamento == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el nombre del departamento");
			if(habitantes == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el número de habitantes");
			if(importancia == null || "".equals(nombre.trim())) throw new DaoException("Ingrese la posición de importancia");
			if(gentilicio == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el gentilicio");
			if(ciudadExiste(c)) throw new DaoException("La ciudad ya existe en la base de datos");
			
			if(c != null ){
				c.setActivo(ACTIVE_CIUDAD);
				ciudadrepository.save(c);
				
			}else{
				ciudad.setIdent(null);
				ciudad.setActivo(ACTIVE_CIUDAD);
				ciudadrepository.save(ciudad);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}
	
	public boolean ciudadExiste(Ciudad c){
		System.out.println("Antes del if ciudad existe\n");
		if(c != null){
			System.out.println("Antes del if getActivo existe\n");
			System.out.println(c.getActivo()+ "\n"+ACTIVE_CIUDAD);
			String isActive = c.getActivo();
			if(isActive.equals(ACTIVE_CIUDAD)){
			System.out.println("En el if de activo\n");
			return true;}
		}
		System.out.println("Retorna false\n");
		return false;

	}
	
	
	@Override
	public void updateExistingCiudad(String id, Ciudad ciudad) throws DaoException {
		try {
			Ciudad c = new Ciudad();
			c=ciudadrepository.findByIdent(ciudad.getIdent());
			System.out.println(c.getIdent());
			if(ciudadExiste(c)){
				ciudadrepository.save(ciudad);
			}else{
				throw new DaoException("No es posible modificar, la ciudad no existe en la base de datos");
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
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
