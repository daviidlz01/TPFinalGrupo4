package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Pol;

public interface IPolService {
	public Pol crearPol();
	public void guardarPol(Pol unPol);
	public List<Pol> obtenerTodosPols();
	public void eliminarPol(Integer codigo)throws Exception;
	public void modificarPol(Pol polModificado)throws Exception;
	public Pol encontrarUnPol(Integer codigo)throws Exception;
}
