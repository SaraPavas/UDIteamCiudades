package com.accenture.ciudades.exception;

import org.apache.log4j.Logger;

public class DaoException extends Exception {
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		logger.error(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		logger.error(cause.getMessage());
		// TODO Auto-generated constructor stub
	}

	
}
