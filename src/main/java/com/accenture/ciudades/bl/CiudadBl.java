package com.accenture.ciudades.bl;

import java.util.List;

import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;

public interface CiudadBl {
	
	List<Ciudad> ListarActivos() throws DaoException;
	void addNewCiudad(Ciudad ciudad) throws DaoException;
//	boolean ciudadExiste(Ciudad ciudad) throws DaoException;

}
