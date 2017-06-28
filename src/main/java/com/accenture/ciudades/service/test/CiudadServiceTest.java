package com.accenture.ciudades.service.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.ciudades.bl.CiudadBl;
import com.accenture.ciudades.dao.CiudadRepository;
import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CiudadServiceTest {

	@Autowired
	CiudadBl ciudadbl;
	@Autowired
	CiudadRepository ciudadrepository;
	@Test
	public void AddnewTest(){
		Ciudad c = new Ciudad(null,"bucaramanga","nariño","3000","2000","pastusos","false");
		try {
			ciudadbl.addNewCiudad(c);
		} catch (DaoException e) {
			e.printStackTrace();
			fail(e.getMessage());
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
