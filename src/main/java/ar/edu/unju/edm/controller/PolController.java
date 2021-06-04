package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Fotografia;
import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.service.IFotografiaService;
import ar.edu.unju.edm.service.IPolService;

@Controller
public class PolController {
	@Autowired
	IPolService polservice;
	@Autowired
	Fotografia foto;
	@Autowired
	IFotografiaService fotoservice;
	@GetMapping("/pol/mostrar")
	public String cargarPol(Model model) {
		model.addAttribute("unPol", polservice.crearPol());
		model.addAttribute("unPol", new Pol());
		model.addAttribute("pols", polservice.obtenerTodosPols());
		model.addAttribute("foto",foto);
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
	//prueba
	@PostMapping(value="/pol/guardar",consumes="multipart/form-data")
	public String guardarPol(@Valid @RequestParam("file") MultipartFile file,@ModelAttribute("unPol") Pol nuevoPol, BindingResult resultado, Model model)throws IOException {
		if (resultado.hasErrors()) {
			model.addAttribute("unPol", nuevoPol);
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "pol";
		} else {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			nuevoPol.setFotoEnlace(base64);
			polservice.guardarPol(nuevoPol);
			model.addAttribute("unPol", new Pol());
			model.addAttribute("pols", polservice.obtenerTodosPols());
			return "redirect:/pol/mostrar";
		}

	}

	@PostMapping(value="/pol/modificar",consumes="multipart/form-data")
	public String modificarPol(@RequestParam("file") MultipartFile file, @ModelAttribute("unPol") Pol polModificado, Model model)throws IOException {
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			polModificado.setFotoEnlace(base64);
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
