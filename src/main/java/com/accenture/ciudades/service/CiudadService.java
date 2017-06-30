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

	/**
	 * Lista todas las ciudades activas de la base de datos
	 */
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



	 /**
	 * Inserta una nueva ciudad a la base de datos, teniendo en cuenta que los campos no esten en blanco o tenga espacios,
	 * si la ciudad existe y esta activa; si la ciudad está inactiva (Activo=false) se cambia de estado a activo (Activo=true),
	 * finalmente se agrega una nueva ciudad si no existe en la base de datos
	 * @param ciudad
	 */
	@Override
	public void addNewCiudad(Ciudad ciudad) throws DaoException {
			String nombre = ciudad.getNombre();
			String departamento = ciudad.getDepartamento();
			String habitantes = ciudad.getHabitantes();
			String importancia = ciudad.getImportancia();
			String gentilicio = ciudad.getGentilicio();

			Ciudad c = new Ciudad();
			c = ciudadrepository.findByNombre(ciudad.getNombre()); 
			
			if(nombre.equals("Nombre") || nombre == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el nombre de la ciudad");
			else if(departamento.equals("Departamento") || departamento == null || "".equals(departamento.trim())) throw new DaoException("Ingrese el nombre del departamento");
			else if(habitantes.equals("Habitantes") || habitantes == null || "".equals(habitantes.trim())) throw new DaoException("Ingrese el número de habitantes");
			else if(importancia.equals("Posición de Importancia") || importancia == null || "".equals(importancia.trim())) throw new DaoException("Ingrese la posición de importancia");
			else if(gentilicio.equals("Gentilicio") || gentilicio == null || "".equals(gentilicio.trim())) throw new DaoException("Ingrese el gentilicio");
			if(ciudadExiste(c)) throw new DaoException("La ciudad ya existe en la base de datos");
			
			if(c != null ){
				c.setActivo(ACTIVE_CIUDAD);
				ciudadrepository.save(c);
				
			}else{
				ciudad.setIdent(null);
				ciudad.setActivo(ACTIVE_CIUDAD);
				ciudadrepository.save(ciudad);
				
			}

	}
	/**
	 * Determina si la ciudad existe 
	 * @param c
	 * @return true si la ciudad existe en la base de datos, falso si no existe o si esta inactivo
	 */
	public boolean ciudadExiste(Ciudad c){
		
		if(c != null){
		
			String isActive = c.getActivo();
			if(isActive.equals(ACTIVE_CIUDAD)){
				
				return true;
			}
		}
		return false;

	}
	
	/**
	 * Actualiza una ciudad existente en la base de datos. Verifica que exista y se procede a actualizar
	 */
	@Override
	public void updateExistingCiudad(String id, Ciudad ciudad) throws DaoException {
		
			String nombre = ciudad.getNombre();
			String departamento = ciudad.getDepartamento();
			String habitantes = ciudad.getHabitantes();
			String importancia = ciudad.getImportancia();
			String gentilicio = ciudad.getGentilicio();
			
			if(nombre == null || "".equals(nombre.trim())) throw new DaoException("Ingrese el nombre de la ciudad");
			else if(departamento == null || "".equals(departamento.trim())) throw new DaoException("Ingrese el nombre del departamento");
			else if(habitantes == null || "".equals(habitantes.trim())) throw new DaoException("Ingrese el número de habitantes");
			else if(importancia == null || "".equals(importancia.trim())) throw new DaoException("Ingrese la posición de importancia");
			else if(gentilicio == null || "".equals(gentilicio.trim())) throw new DaoException("Ingrese el gentilicio");

			
			Ciudad c = new Ciudad();
			c=ciudadrepository.findByIdent(ciudad.getIdent());
			System.out.println(c.getActivo()+"\n");
			if(ciudadExiste(c)){
				ciudadrepository.save(ciudad);
			}else{
				throw new DaoException("No es posible modificar, la ciudad no existe en la base de datos");
			}
		
		
	}


	
	/**
	 * Filtra las ciudades por nombre
	 * @param strNombre
	 * @return ciudad encontrada
	 */
	public List <Ciudad> getCiudadbyNombre(String strNombre){
		//Ciudad ciudadEncontrada = new Ciudad();
		try {
			return ciudadrepository.findByNombreStartingWith(strNombre);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	/**
	 * Filtra por ciudad y por departamento
	 * @param palabra
	 * @return ciudad encontrada
	 * @throws DaoException 
	 */
	public List<Ciudad> getCiudadByNombreAndDepartamento(String palabra) throws DaoException {
		
		List<Ciudad> ciudad = new ArrayList<>();
		
		
			if(palabra.equals("Palabra clave: Ciudad/Departamento") || palabra == null || "".equals(palabra.trim())) throw new DaoException("Ingrese una palabra clave");
			ciudad.addAll(ciudadrepository.findByNombreStartingWith(palabra));
			ciudad.addAll(ciudadrepository.findByDepartamentoStartingWith(palabra));
			for(int i=0; i<ciudad.size(); i++){
				if(ciudad.get(i).getActivo().equals(NO_ACTIVE_CIUDAD)){
					ciudad.remove(i);
				}
			}
			
			if(ciudad.size()==0) throw new DaoException("No se encontró");
			
			return ciudad; 
		

		
	}
	/**
	 * Lista una ciudad por id siempre y cuando este activa
	 * @param i es el id de la ciudad
	 * @return
	 */
	public Ciudad getActiveByIdent(String i){
		try {
			
			return ciudadrepository.findByIdentAndActivo(i, ACTIVE_CIUDAD);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Eliminado lógico de una ciduad de la base de datos
	 * @param i: id de la ciudad
	 */
	public void deleteCiudad(String i){
		
		Ciudad ciudad = new Ciudad();
		try {
			/**
			 * Devuelve la ciudad por id y si esta activo=TRUE
			 */
			ciudad = ciudadrepository.findByIdentAndActivo(i, ACTIVE_CIUDAD);
			/**
			 * si trae la ciudad{ cambia el estado a no activo y lo almacena
			 */
			if(ciudad != null){
				ciudad.setActivo(NO_ACTIVE_CIUDAD);
				ciudadrepository.save(ciudad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
}
