package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.unju.edm.service.ITuristasService;

@Controller
public class TuristaController {
	
	@Autowired
	ITuristasService turistasService;

}
