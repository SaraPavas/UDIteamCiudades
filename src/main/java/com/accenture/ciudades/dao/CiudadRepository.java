package com.accenture.ciudades.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad,String>{
	

	List<Ciudad> findByNombreStartingWith(String nombre) throws DaoException;
	List<Ciudad> findByDepartamentoStartingWith(String departamento) throws DaoException;
	/**
	 * Encuentra las ciudades activas en la base de datos (sin borrado lógico)
	 * @param status Estado (Activo=true, Inactivo=false)
	 * @return Una lista de las ciudades activas
	 * @throws DaoException
	 */
	List<Ciudad> findByActivo(String status) throws DaoException;
	/**
	 * Busca una ciudad con id y con estado.
	 * @param i
	 * @param status
	 * @return
	 * @throws DaoException
	 */
	Ciudad findByIdentAndActivo(String i, String status) throws DaoException;
	Ciudad findByNombre(String nombre);
	/**
	 * Buscar por id
	 * @param ident
	 * @return La ciudad correspondiente al id
	 */
	Ciudad findByIdent(String ident);
	List<Ciudad> findByNombreAndActivoContaining(String nombre,String activo) throws DaoException;
	List<Ciudad> findByDepartamentoAndActivoContaining(String departamento, String activo) throws DaoException;
}
