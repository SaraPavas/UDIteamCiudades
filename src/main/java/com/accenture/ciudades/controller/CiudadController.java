package com.accenture.ciudades.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/**
	 * Es la vista principal cuando el usuario ingresa a la aplicación web, lista todas las 
	 * ciudades activas en la base de datos
	 * @return
	 */
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
	/**
	 * Redirige a la vista con los campos para editar una ciudad, es llamado cuando el usuario
	 * clickea la etiqueta editar de una ciudad en la lsita de la vista principal
	 * @param id es el id de la ciudad que va a ser editada
	 * @return
	 */
	@RequestMapping("/update/{id}")
    public ModelAndView uPdate(@PathVariable String id) {
		/**
		 * message es un objeto enviado a la vista para almacenar una excepción en caso que el usuario
		 * no diligencie correctamente los campos
		 */
		String message = "";
        ModelAndView mav = new ModelAndView();
        /**
         * Trae a la vista los datos de la ciudad con el id
         */
        Ciudad ciudad = ciudadrepository.findByIdent(id);
        mav.setViewName("update");
        mav.addObject("ciudad",ciudad);
        mav.addObject("message", message);
        return mav;
    }
	/**
	 * Guarda la infromación contenida en los campos, se asigna a una ciudad y se almacena en la base de datos 
	 * @param ciudad corresponde a la ciudad almacenada por el usuario en la vista
	 * @return
	 */
	@RequestMapping(value="/ciudades",params={"update"})
    public ModelAndView uPdateSaved(@ModelAttribute Ciudad ciudad) {
		String message = "";
		ModelAndView modelandview = new ModelAndView();
		/**
		 * Busca el id de la ciudad que esta editando y cambia el estado por el correspondiente 
		 */
		ciudad.setActivo(ciudadrepository.findOne(ciudad.getIdent()).getActivo());
        try {
			ciudadservice.updateExistingCiudad(ciudad.getIdent(), ciudad);
			/**
			 * Si no se lanza una excepción se redirige a ciudades
			 */
			modelandview.setViewName("redirect:/ciudades");
		} catch (DaoException e) {
			/**
			 * Si se lanza una excepción se sale de la vista y se envia el mensaje de la
			 * excepcion a la vista
			 */
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
			modelandview.setViewName("update");
		}
        modelandview.addObject("message", message);
        return modelandview;
    }
	/**
	 * Elimina una ciudad de la base de datos cuando el usuario
	 * clickea eliminar
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable String id) {
        ciudadservice.deleteCiudad(id);
      
        return new ModelAndView("redirect:/ciudades");
    }
	/**
	 * *Redirige a la vista con los campos para agregar una ciudad, es llamado cuando el usuario
	 * clickea la etiqueta agregar nueva ciudad  en la lsita de la vista principal
	 * @return
	 */
	@RequestMapping("/add")
    public ModelAndView addNewCity() {
        ModelAndView mav = new ModelAndView();
        Ciudad ciudad = new Ciudad();
        String message = "";
		ciudad.setIdent(null);
		ciudad.setNombre("Nombre");
		ciudad.setDepartamento("Departamento");
		ciudad.setHabitantes("Habitantes");
		ciudad.setImportancia("Posición de Importancia");
		ciudad.setGentilicio("Gentilicio");
		ciudad.setActivo("true");
		mav.addObject("ciudad",ciudad);
        mav.setViewName("add");
        mav.addObject("message", message);
        return mav;
    }
	/**
	 * Guarda la infromación contenida en los campos, se asigna a una ciudad y se almacena en la base de datos 
	 * @param ciudad
	 * @return
	 */
	@RequestMapping(value="/ciudades",params={"save"})
    public ModelAndView newSaved(@ModelAttribute Ciudad ciudad) {
		
		String message = "";
		ModelAndView modelandview = new ModelAndView();
			try {
				
				ciudadservice.addNewCiudad(ciudad);
				modelandview.setViewName("redirect:/ciudades");
				
			} catch (DaoException e) {
				
				e.printStackTrace();
				message = e.getMessage();
				modelandview.setViewName("add");
			}
			modelandview.addObject("message", message);
			
			// TODO Auto-generated catch block
			
	
        
        return modelandview;
    }
	/**
	 * Lleva a la vista buscar
	 * @return
	 */
	@RequestMapping("/search")
    public ModelAndView searchCity() {
		ModelAndView mav = new ModelAndView();
		String message = "";
        Ciudad ciudad = new Ciudad();
        ciudad.setIdent(null);
		ciudad.setNombre("Palabra clave: Ciudad/Departamento");
		ciudad.setDepartamento("Palabra clave: Ciudad/Departamento");
		ciudad.setHabitantes("Palabra clave: Ciudad/Departamento");
		ciudad.setImportancia("Palabra clave: Ciudad/Departamento");
		ciudad.setGentilicio("Palabra clave: Ciudad/Departamento");
		ciudad.setActivo("true");
		mav.addObject("ciudad",ciudad);
        mav.setViewName("search");
        mav.addObject("message", message);
        return mav;
    }
	/**
	 * Cuando el usuario oprime el botón buscar, encuentra la ciudad con la palabra ingresada en la base de datos
	 * 
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/searched",params={"search"})
    public ModelAndView searchedCity(@ModelAttribute Ciudad c) {
		ModelAndView mav = new ModelAndView();
		String palabra = c.getNombre();
		String message = "";
		List <Ciudad> ciudad = new ArrayList<>();
		try {
			ciudad = ciudadservice.getCiudadByNombreAndDepartamento(palabra);
			mav.setViewName("listar");
			mav.addObject("ciudades", ciudad);
		} catch (DaoException e) {
			e.printStackTrace();
			message = e.getMessage();
			mav.setViewName("search");
		}
		mav.addObject("message", message);
		
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
