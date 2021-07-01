package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristasService;
@Controller
public class TuristaController {
	private static final Log LOGGER = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	ITuristasService turistaService;
	//ESTA PARTE ES LA DE CREAR CUENTA
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		//UserDetails userTurista = (UserDetails) authentication.getPrincipal();
		//System.out.println(userTurista.getUsername());
		return("turista");
	}
	//ESTA ES LA PARTE DE MIPERFIL DONDE PUEDO MODIFICAR SUS DATOS
	@GetMapping("/turista/miperfil")
	public String miPerfilTurista(Model model,Authentication authentication)throws Exception{
		UserDetails userTurista = (UserDetails) authentication.getPrincipal();
		model.addAttribute("unTurista",turistaService.encontrarUnTurista(userTurista.getUsername()));
		System.out.println(userTurista.getUsername());
		return "turista-editar";
	}
	@PostMapping("/turista/miperfil/editar")
	public String miPerfilTuristaModificar(@Valid @ModelAttribute("unTurista") Turista turistaModificado, BindingResult resultado, Model model) throws Exception{
		if (resultado.hasErrors()) 
		{
			LOGGER.error("METHOD: Esto va a dar un error");
			model.addAttribute("unTurista", turistaModificado);
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
			return("turista-editar");
		}
		else
		{
			try {
				turistaService.modificarTurista(turistaModificado);
				model.addAttribute("unTurista", new Turista());				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				turistaService.modificarTurista(turistaModificado);
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unTurista", turistaModificado);
			}
			return "redirect:/turista/miperfil";
		}
	}
	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista(@Valid @ModelAttribute("unTurista") Turista nuevoTurista, BindingResult resultado ,Model model) {
		
		if (resultado.hasErrors()) 
		{
			LOGGER.info("METHOD: Esto va a dar un error");
			model.addAttribute("unTurista", nuevoTurista);
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
			return("turista");
		}
		else 
		{
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			try {
				nuevoTurista.setActivo("SI");
				turistaService.guardarTurista(nuevoTurista);
				return "redirect:/home";
			}
			catch (Exception e){
				LOGGER.info("METHOD: Esto va a dar un error");
				model.addAttribute("unTurista", nuevoTurista);
				return("error");

			}
		}
	}
	//ESTA PARTE INHABILITA EL TURISTA(NO LO BORRA)
	@GetMapping("/turista/eliminarTurista/{idTurista}")
	public String eliminarTurista(Model model, @PathVariable(name="idTurista") int id) {
		LOGGER.info("METHOD: ingresando el metodo Eliminar");
		try {
			Turista turistaEncontrado = turistaService.encontrarUnTurista(id);
			turistaService.modificarTurista3(turistaEncontrado);
			model.addAttribute("unTurista", new Turista());				
			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/home";
	}
}
