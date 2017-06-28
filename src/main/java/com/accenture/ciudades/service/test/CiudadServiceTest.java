package com.accenture.ciudades.service.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.ciudades.bl.CiudadBl;
import com.accenture.ciudades.dao.CiudadRepository;
import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;
import com.accenture.ciudades.service.CiudadService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CiudadServiceTest {

	@Autowired
	CiudadBl ciudadbl;
	@Autowired
	CiudadRepository ciudadrepository;
	@Autowired
	CiudadService ciudadservice;
	@Test
	public void addNewCityTest(){
		Ciudad c = new Ciudad(null,"pasto","nariño","3000","2000","pastusos",null);
		try {
			ciudadbl.addNewCiudad(c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	@Test
	public void addNewInactiveCityTest(){
	
		Ciudad c = new Ciudad();
		c = ciudadrepository.findByIdent("3");
		try {
			ciudadbl.addNewCiudad(c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	@Test
	public void addNewActiveCityTest(){
	
		Ciudad c = new Ciudad();
		c = ciudadrepository.findByIdent("1");
		try {
			ciudadbl.addNewCiudad(c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	@Test
	public void upDateExisitingCityTest(){
	
		Ciudad c = new Ciudad();
		c = ciudadrepository.findByIdent("1");
		c.setGentilicio("Antioqueño");
		try {
			ciudadbl.updateExistingCiudad(c.getIdent(), c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	@Test
	public void upDateNoExisitingCityTest(){
	
		Ciudad c = new Ciudad();
		c = ciudadrepository.findByIdent("5");
		c.setGentilicio("barranquillero");
		try {
			ciudadbl.updateExistingCiudad(c.getIdent(), c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	@Test
	public void searchEByNombreANdDepartamentoLetraInicialTest(){
		/**
		 * Departamentos que inician con M
		 */
		String palabra = "m";
		List <Ciudad> ciudades = new ArrayList<>();
		ciudades=ciudadservice.getCiudadByNombreAndDepartamento(palabra);
		for (int i=0;i<ciudades.size();i++){
			System.out.println(ciudades.get(i).getNombre());
		}
	}
	@Test
	public void searchEByNombreANdDepartamentoporDepartamentoTest(){
		/**
		 * Departamentos y ciudades que inician con M
		 */
		String palabra = "Antiquia";
		List <Ciudad> ciudades = new ArrayList<>();
		ciudades=ciudadservice.getCiudadByNombreAndDepartamento(palabra);
		for (int i=0;i<ciudades.size();i++){
			System.out.println(ciudades.get(i).getNombre());
		}
	}
	@Test
	public void searchEByNombreANdDepartamentoporCiudadTest(){
		/**
		 * Departamentos y ciudades que inician con M
		 */
		String palabra = "Cartagena";
		List <Ciudad> ciudades = new ArrayList<>();
		ciudades=ciudadservice.getCiudadByNombreAndDepartamento(palabra);
		for (int i=0;i<ciudades.size();i++){
			System.out.println(ciudades.get(i).getNombre());
		}
	}
	@Test
	public void deletebyIdTest(){
		/**
		 * Se elimina la ciudad 5
		 */
		String ident = "5";
		List <Ciudad> ciudades = new ArrayList<>();
		//Se listan todas ciudades
		try {
			ciudades=ciudadservice.ListarActivos();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		for (int i=0;i<ciudades.size();i++){
			System.out.println(ciudades.get(i).getNombre());
		}
		
		ciudadservice.deleteCiudad(ident);
		//Se listan todas las ciudades despues de eliminar
		try {
			ciudades=ciudadservice.ListarActivos();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		for (int i=0;i<ciudades.size();i++){
			System.out.println(ciudades.get(i).getNombre());
		}
	}
	@Test
	public void FindBynameTest(){
		Ciudad c = new Ciudad(null,"santa marta","nariño","3000","2000","pastusos","false");
		Ciudad ce = new Ciudad();
			String nombre = c.getNombre();
			
			ce=ciudadrepository.findByNombre(nombre);
			if(ce != null){
			ce.setActivo("true");
			ciudadrepository.save(ce);
			System.out.println(ce.getActivo()+c.getIdent());}
		
	}
	@Test
	public void FindByname2Test(){
		Ciudad c = new Ciudad("0","leticia","nariño","3000","2000","pastusos","false");
		Ciudad ce = new Ciudad();
			String nombre = c.getNombre();
			
			ce=ciudadrepository.findByNombre(nombre);
			if(ce != null){
			ce.setActivo("true");
			ciudadrepository.save(ce);
			System.out.println(ce.getActivo()+c.getIdent());}
			else{
				c.setIdent(null);
				c.setActivo("true");
				ciudadrepository.save(c);
			}
		
	}
}
