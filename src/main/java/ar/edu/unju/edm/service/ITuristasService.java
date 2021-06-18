package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Turista;

public interface ITuristasService {
	public void guardarTurista(Turista turistaGuardado);
	public Turista crearTurista();
	public Turista encontrarUnTurista(int idTurista) throws Exception;
	public Turista encontrarUnTurista(String email) throws Exception;
	public void modificarTurista(Turista turistaModificado) throws Exception;
	public void modificarTurista2(Turista turistaModificado) throws Exception;
	public void eliminarTurista(int idTurista) throws Exception;
	List<Turista> obtenerTodosTuristas();


}
