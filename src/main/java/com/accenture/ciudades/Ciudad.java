package com.accenture.ciudades;

import javax.persistence.*;

@Entity
//@Table(name="ciudades_table")
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String ident;
	private String nombre;
	private String departamento;
	private String habitantes;
	private String importancia;
	private String gentilicio;
	private String activo;
	
	
	public Ciudad() {
		super();
	}


	public Ciudad(String ident, String nombre, String departamento, String habitantes, String importancia,
			String gentilicio, String activo) {
		super();
		this.ident = ident;
		this.nombre = nombre;
		this.departamento = departamento;
		this.habitantes = habitantes;
		this.importancia = importancia;
		this.gentilicio = gentilicio;
		this.activo = activo;
	}


	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getHabitantes() {
		return habitantes;
	}


	public void setHabitantes(String habitantes) {
		this.habitantes = habitantes;
	}


	public String getImportancia() {
		return importancia;
	}


	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}


	public String getGentilicio() {
		return gentilicio;
	}


	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}


	public String getActivo() {
		return activo;
	}


	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
	


}
