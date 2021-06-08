package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.model.Turista_Pols;
import ar.edu.unju.edm.service.IPolService;
import ar.edu.unju.edm.service.ITurista_PolsService;
import ar.edu.unju.edm.service.ITuristasService;

@Controller
public class Turista_PolsController {
	@Autowired
	ITurista_PolsService t_pService;
	@Autowired
	ITuristasService turistaService;
	@Autowired
	IPolService polService;
	@Autowired
	Pol polSeleccionado;
	@Autowired
	Turista_Pols t_p;
	@GetMapping("/pol/comentarios")
	public String cargarComentario(Model model) {
		model.addAttribute("pols",polService.obtenerTodosPols());
		return "comentarios";
	}
	@GetMapping("/pol/comentarios/{codigo}")
	public String realizarComentario(Model model, @PathVariable(name="codigo") Integer codigo)throws Exception {
		try {
			polSeleccionado = polService.encontrarUnPol(codigo);
			t_p = t_pService.crearTurista_pols();
			t_p.setPol(polSeleccionado);
			model.addAttribute("comentarios",t_p);
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
		}
		catch (Exception e){
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
		}
		return "modal-comentarios";
	}
	@PostMapping("/pol/comentarios/realizado")
	public String guardarComentario(@ModelAttribute("comentarios") Turista_Pols comentario,Model model) {
		t_pService.guardarTurista_pols(comentario);
		return ("redirect:/pols/comentarios");
	}
}
