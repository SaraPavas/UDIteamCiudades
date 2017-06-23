package com.accenture.ciudades;

import javax.persistence.*;

@Entity
@Table(name="ciudades_table")
public class Ciudad {
	
	@Id
	private String id;
	private String nombre;
	private String departamento;
	private String nHabitantes;
	private String posImport;
	private String gentilicio;
	
	
	public Ciudad() {
		super();
	}
	
	public Ciudad(String id, String nombre, String departamento, String nHabitantes, String posImport,
			String gentilicio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.nHabitantes = nHabitantes;
		this.posImport = posImport;
		this.gentilicio = gentilicio;
	}

	public String get_id() {
		return id;
	}
	public void set_id(String id) {
		this.id = id;
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
	public String getGentilicio() {
		return gentilicio;
	}
	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}
	public String getnHabitantes() {
		return nHabitantes;
	}
	public void setnHabitantes(String nHabitantes) {
		this.nHabitantes = nHabitantes;
	}
	public String getPosImport() {
		return posImport;
	}
	public void setPosImport(String posImport) {
		this.posImport = posImport;
	}
	
	


}
