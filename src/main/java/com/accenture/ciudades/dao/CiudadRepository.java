package com.accenture.ciudades.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ciudades.dto.Ciudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad,String>{
	

}
