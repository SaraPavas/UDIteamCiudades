package com.accenture.ciudades.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.ciudades.dao.CiudadRepository;
import com.accenture.ciudades.dto.Ciudad;
import com.accenture.ciudades.exception.DaoException;
import com.accenture.ciudades.service.CiudadService;





@Controller
public class CiudadController {
	
	@Autowired
	private CiudadService ciudadservice;
	@Autowired
	private CiudadRepository ciudadrepository;
	@RequestMapping(method = RequestMethod.GET, value="/ciudades")
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("listar");
        try {
			mav.addObject("ciudades", ciudadservice.ListarActivos());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mav;
    }

//	@RequestMapping(method = RequestMethod.GET, value="/update")
	@RequestMapping("/update/{id}")
    public ModelAndView uPdate(@PathVariable String id) {
        ModelAndView mav = new ModelAndView();
        Ciudad ciudad = ciudadrepository.findByIdent(id);
        mav.setViewName("update");
        mav.addObject("ciudad",ciudad);
      
        return mav;
    }
	@RequestMapping(value="/ciudades",params={"update"})
    public ModelAndView uPdateSaved(@ModelAttribute Ciudad ciudad) {
		System.out.println(ciudad.getIdent()+"\n");
		System.out.println(ciudad.getActivo()+"\n");
		ciudad.setActivo(ciudadrepository.findOne(ciudad.getIdent()).getActivo());
		System.out.println(ciudad.getActivo()+"\n");
        try {
			ciudadservice.updateExistingCiudad(ciudad.getIdent(), ciudad);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ModelAndView("redirect:/ciudades");
    }
	@RequestMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable String id) {
        ciudadservice.deleteCiudad(id);
      
        return new ModelAndView("redirect:/ciudades");
    }
	@RequestMapping("/add")
    public ModelAndView addNewCity() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        return mav;
    }
//	@RequestMapping("/ciudades")
//		public List<Ciudad> listarCiudades() {
//			return ciudadservice.getAllCiudades();
//	}
//	 //This method helps to find any information on Service`s JSON using annotation @PathVariable
//	@RequestMapping("/ciudades/{id}")
//	public Ciudad getETopic (@PathVariable String id){
//		return ciudadservice.getETopic(id);
//	}
//	@RequestMapping (method = RequestMethod.POST, value = "/ciudades")
//	public void addTopic(@RequestBody Ciudad ciudad){
//		ciudadservice.addCiudad(ciudad);
//	}
//	@RequestMapping (method = RequestMethod.PUT, value = "/ciudades/{id}")
//	public void updateTopic(@RequestBody Ciudad ciudad, @PathVariable String id){
//		ciudadservice.updateCiudad(id, ciudad);
//	}
//	@RequestMapping("/ciudades/search/{nombre}")
//	public List <Ciudad> getCiudadNombre(@PathVariable String nombre){
//		return ciudadservice.getCiudadbyNombre(nombre);
//	}
//	@RequestMapping("/ciudades/search2/{palabra}")
//	public List <Ciudad> getCiudadNombreDepartamento(@PathVariable String palabra){
//		return ciudadservice.getCiudadByNombreAndDepartamento(palabra);
//	}
//	@RequestMapping("/ciudades/actives")
//	public List <Ciudad> getActives(){
//		try {
//			return ciudadservice.ListarActivos();
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	@RequestMapping("/ciudades/actives/{id}")
//	public Ciudad getActiveByIent(@PathVariable String id){
//		return ciudadservice.getActiveByIdent(id);
//	}
//	@RequestMapping("/ciudades/delete/{id}")
//	public void deleteActiveCiudad(@PathVariable String id){
//		ciudadservice.deleteCiudad(id);
//	}
//	
//	@RequestMapping (method = RequestMethod.POST, value = "/ciudades/new")
//	public void addNewCity(@RequestBody Ciudad ciudad){
//		try {
//			ciudadservice.addNewCiudad(ciudad);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	@RequestMapping (method = RequestMethod.PUT, value = "/ciudades/update/{id}")
//	public void updateCiudad(@RequestBody Ciudad ciudad, @PathVariable String id){
//		try {
//			ciudadservice.updateExistingCiudad(id, ciudad);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//	}
}
