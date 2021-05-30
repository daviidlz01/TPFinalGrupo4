package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.service.IPolService;

@Controller
public class PolController {
	@Autowired
	IPolService polservice;

	@GetMapping("/pol/mostrar")
	public String cargarPol(Model model) {
		model.addAttribute("unPol", polservice.crearPol());
		model.addAttribute("unPol", new Pol());
		model.addAttribute("pols", polservice.obtenerTodosPols());
		return "pol";
	}

	@GetMapping("/pol/editar/{codigo}")
	public String editarPol(Model model, @PathVariable(name = "codigo") Integer codigo) {
		try {
			Pol polEncontrado = polservice.encontrarUnPol(codigo);
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

	@PostMapping("/pol/guardar")
	public String guardarPol(@Valid @ModelAttribute("unPol") Pol nuevoPol, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("unPol", nuevoPol);
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "pol";
		} else {
			polservice.guardarPol(nuevoPol);
			model.addAttribute("unPol", new Pol());
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "redirect:/pol/mostrar";
		}

	}

	@PostMapping("/pol/modificar")
	public String modificarPol(@ModelAttribute("unPol") Pol polModificado, Model model) {
		try {
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
