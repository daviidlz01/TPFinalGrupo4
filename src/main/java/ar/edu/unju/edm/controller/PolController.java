package ar.edu.unju.edm.controller;

import java.io.IOException;

import java.util.Base64;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPolService;
import ar.edu.unju.edm.service.ITuristasService;

@Controller
public class PolController {
	@Autowired
	IPolService polservice;
	@Autowired
	ITuristasService turistaService;
	private static final Log LOGGER = LogFactory.getLog(PolController.class);
	@GetMapping("/pol/mostrar")
	public String cargarPol(Model model, Authentication authentication)throws Exception {
		model.addAttribute("unPol", polservice.crearPol());
		model.addAttribute("unPol", new Pol());
		UserDetails userTurista = (UserDetails) authentication.getPrincipal();
		model.addAttribute("unTurista",turistaService.encontrarUnTurista(userTurista.getUsername()));
		model.addAttribute("pols", polservice.obtenerTodosPols());
		return "pol";
	}

	@GetMapping("/pol/editar/{codigo}")
	public String editarPol(Model model, @PathVariable(name = "codigo") Integer codigo,Authentication authentication) {
		try {
			UserDetails userTurista = (UserDetails) authentication.getPrincipal();
			model.addAttribute("unTurista",turistaService.encontrarUnTurista(userTurista.getUsername()));
			Pol polEncontrado = polservice.encontrarUnPol(codigo);
			polEncontrado.setTurista(turistaService.encontrarUnTurista(userTurista.getUsername()));
			model.addAttribute("unPol", polEncontrado);
			model.addAttribute("editMode", "true");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unPol", polservice.crearPol());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("pols",polservice.obtenerTodosPols());
		return "pol";
	}
	@PostMapping(value="/pol/guardar",consumes="multipart/form-data")
	public String guardarPol(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3, @Valid @ModelAttribute("unPol") Pol nuevoPol, BindingResult resultado,@ModelAttribute("unTurista") Turista nuevoTurista, Model model)throws Exception {
		if (resultado.hasErrors()) {
			LOGGER.info("Esto lanzara un error");
			
			model.addAttribute("unPol", nuevoPol);
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "pol";
		} else {
			LOGGER.info("Guardando poi");
			
			byte[] content = file.getBytes();
			byte[] content2 = file2.getBytes();
			byte[] content3 = file3.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			String base65 = Base64.getEncoder().encodeToString(content2);
			String base66 = Base64.getEncoder().encodeToString(content3);
			nuevoPol.setFotoEnlace(base64);
			if(base65.equals("")) {
				
			}
			else {
				nuevoPol.setFotoEnlace2(base65);
			}
			if(base66.equals("")) {
				
			}
			else {
				nuevoPol.setFotoEnlace2(base66);
			}
			
			polservice.guardarPol(nuevoPol);
			nuevoPol.setLocalizacionLatitud((Math.random() * -1000) + 1);
			nuevoPol.setLocalizacionLongitud((Math.random() * -1000) + 1);
			nuevoTurista.setPuntos(10+nuevoTurista.getPuntos());
			
			try {
				turistaService.modificarTurista2(nuevoTurista);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("unPol", new Pol());
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "redirect:/pol/mostrar";
		}

	}

	@PostMapping(value="/pol/modificar",consumes="multipart/form-data")
	public String modificarPol(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3 ,@ModelAttribute("unPol") Pol polModificado, Model model)throws IOException {
		try {
			byte[] content = file.getBytes();
			byte[] content2 = file2.getBytes();
			byte[] content3 = file3.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			String base65 = Base64.getEncoder().encodeToString(content2);
			String base66 = Base64.getEncoder().encodeToString(content3);
			polModificado.setFotoEnlace(base64);
			if(base65.equals("")) {
				
			}
			else {
				polModificado.setFotoEnlace2(base65);
			}
			if(base66.equals("")) {
				
			}
			else {
				polModificado.setFotoEnlace3(base66);
			}
			polModificado.setLocalizacionLatitud((Math.random() * -1000) + 1);
			polModificado.setLocalizacionLongitud((Math.random() * -1000) + 1);
			polservice.modificarPol(polModificado);
			model.addAttribute("unPol", new Pol());
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unPol", polModificado);
			model.addAttribute("pols", polservice.obtenerTodosPols());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("pols", polservice.obtenerTodosPols());
		return "redirect:/pol/mostrar";
	}

	@GetMapping("/pol/eliminarPol/{codigo}")
	public String eliminarPol(Model model, @PathVariable(name = "codigo") Integer codigo) {
		try {
			polservice.eliminarPol(codigo);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/pol/mostrar";
	}
}
