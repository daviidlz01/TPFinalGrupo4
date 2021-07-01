package ar.edu.unju.edm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPolService;
import ar.edu.unju.edm.service.ITuristasService;

@Controller
public class RankingsController {
	@Autowired
	ITuristasService turistaService;
	@Autowired
	IPolService polService;
	//ESTA PARTE MOSTRARA EL RANKING DE LOS TURISTAS CON MAS PUNTOS Y ADEMAS EL RANKING DE LOS POIS MAS VALORADOS
		@GetMapping("/turista/rankings")
		public String verRankings(Model model) {
			List <Turista> rankings=new ArrayList<Turista>();
			List <Pol> rankings2=new ArrayList<Pol>();
			rankings=turistaService.obtenerTodosTuristas();
			rankings2=polService.obtenerTodosPols();
			 Collections.sort(rankings);
			 Collections.reverse(rankings);
			 Collections.sort(rankings2);
			 Collections.reverse(rankings2);
			model.addAttribute("rankings",rankings);
			model.addAttribute("rankings2",rankings2);
			return "rankings";
		}
}
