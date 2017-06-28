package com.accenture.ciudades.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad,String>{
	
	//@Query("select * from ciudad where u.username like '%username%'")
	List<Ciudad> findByNombreContaining(String nombre) throws DaoException;
	List<Ciudad> findByDepartamentoContaining(String departamento) throws DaoException;
	List<Ciudad> findByActivo(String status) throws DaoException;
	Ciudad findByIdentAndActivo(String i, String status) throws DaoException;
	Ciudad findByNombre(String nombre);
}
