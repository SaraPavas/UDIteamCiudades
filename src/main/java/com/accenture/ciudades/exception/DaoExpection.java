package com.accenture.ciudades.exception;

import org.apache.log4j.Logger;

public class DaoExpection extends Exception {
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public DaoExpection(String message, Throwable cause) {
		super(message, cause);
		logger.error(message);
		// TODO Auto-generated constructor stub
	}

	public DaoExpection(String message) {
		super(message);
		logger.error(message);
		// TODO Auto-generated constructor stub
	}

	public DaoExpection(Throwable cause) {
		super(cause);
		logger.error(cause.getMessage());
		// TODO Auto-generated constructor stub
	}

	
}
