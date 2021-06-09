package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Turista_Pols;

public interface ITurista_PolsService {
	public void guardarTurista_pols(Turista_Pols valoracion);
	public Turista_Pols crearTurista_pols();
	public List<Turista_Pols> obtenerTodosTurista_pols();
	public Turista_Pols encontrarTurista_pols()throws Exception;
}
