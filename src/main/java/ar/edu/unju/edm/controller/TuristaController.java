package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristasService;
@Qualifier("TuristasServiceMySQL")
@Controller
public class TuristaController {
	private static final Log LOGGER = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	ITuristasService turistaService;
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
		return("turista");
	}
	
	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista(@Valid @ModelAttribute("unTurista") Turista nuevoTurista, BindingResult resultado ,Model model) {
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("unTurista", nuevoTurista);
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
			return("turista");
		}
		else 
		{
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			turistaService.guardarTurista(nuevoTurista);
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
			return "redirect:/turista/mostrar";
		}
	}
	
	@GetMapping("/turista/editar/{idTurista}")
	public String editarTurista(Model model, @PathVariable(name="idTurista") int idTurista) throws Exception {
		try {
			Turista turistaEncontrado = turistaService.encontrarUnTurista(idTurista);
			model.addAttribute("unTurista", turistaEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTurista", turistaService.crearTurista());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
		return("turista");
	}
	
	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("unTurista") Turista turistaModificado, Model model) {
		try {
			turistaService.modificarTurista(turistaModificado);
			model.addAttribute("unTurista", new Turista());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTurista", turistaModificado);
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
		return "redirect:/turista/mostrar";
	}

	@GetMapping("/turista/eliminarTurista/{idTurista}")
	public String eliminarTurista(Model model, @PathVariable(name="idTurista") int id) {
		LOGGER.info("METHOD: ingresando el metodo Eliminar");
		try {
			turistaService.eliminarTurista(id);		
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/turista/mostrar";
	}
}
