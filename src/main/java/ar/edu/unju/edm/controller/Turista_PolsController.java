package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.model.Turista;
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
	Turista turistaSeleccionado;
	@Autowired
	Turista_Pols t_p;
	@GetMapping("/pol/comentarios")
	public String cargarComentario(Model model) {
		model.addAttribute("pols",polService.obtenerTodosPols());
		model.addAttribute("comments",t_pService.obtenerTodosTurista_pols());
		return "comentarios";
	}
	@GetMapping("/pol/comentarios/{codigo}/algo")
	public String realizarComentario(Model model, @PathVariable(name="codigo") Integer codigo,Authentication authentication)throws Exception {
		Turista_Pols tp=new Turista_Pols();
		try {
			polSeleccionado = polService.encontrarUnPol(codigo);
			tp = t_pService.crearTurista_pols();
			tp.setPol(polSeleccionado);
			model.addAttribute("comentarios",tp);
			UserDetails userTurista = (UserDetails) authentication.getPrincipal();
			model.addAttribute("unTurista",turistaService.encontrarUnTurista(userTurista.getUsername()));
			model.addAttribute("turistas",turistaService.obtenerTodosTuristas());
			model.addAttribute("unPol",tp.getPol());
			model.addAttribute("valora",tp.getValoracionGeneral(t_pService.obtenerTodosTurista_pols(),tp.getPol().getCodigo()));
			model.addAttribute("verificar",tp.getVerificarValoracion(t_pService.obtenerTodosTurista_pols(), tp.getPol().getCodigo(), turistaService.encontrarUnTurista(userTurista.getUsername()).getIdTurista()));
			model.addAttribute("cont",tp.getVotosPols(t_pService.obtenerTodosTurista_pols(),tp.getPol().getCodigo()));
		}
		catch (Exception e){
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
		}
		model.addAttribute("comments",t_pService.obtenerTodosTurista_pols());
		return "pol-detalles";
	}
	@PostMapping("/pol/comentarios/realizado")
	public String guardarComentario(@ModelAttribute("comentarios") Turista_Pols comentario,@ModelAttribute("unTurista") Turista unturista,@ModelAttribute("unPol") Pol unpol,Model model) throws Exception{
		t_pService.guardarTurista_pols(comentario);
		unturista.setPuntos(5+unturista.getPuntos());
		Integer codigo=comentario.getPol().getCodigo();
		String cadena=String.valueOf(codigo);
		turistaService.modificarTurista2(unturista);
		model.addAttribute("comments",t_pService.obtenerTodosTurista_pols());
		model.addAttribute("unTurista", new Turista());	
		return ("redirect:/pol/comentarios/"+cadena+"/algo");
	}
	@PostMapping("/pol/valoraciones/realizado")
	public String guardarComentario2(@ModelAttribute("comentarios") Turista_Pols comentario,@ModelAttribute("unTurista") Turista unturista,@ModelAttribute("unPol") Pol unpol,Model model) throws Exception{
		t_pService.guardarTurista_pols(comentario);
		unturista.setPuntos(8+unturista.getPuntos());
		Integer codigo=comentario.getPol().getCodigo();
		String cadena=String.valueOf(codigo);
		turistaService.modificarTurista2(unturista);
		model.addAttribute("comments",t_pService.obtenerTodosTurista_pols());
		model.addAttribute("unTurista", new Turista());	
		return ("redirect:/pol/comentarios/"+cadena+"/algo");
	}
}
