package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristasDAO;
import ar.edu.unju.edm.service.ITuristasService;

@Service
public class TuristasServiceMySQL implements ITuristasService {

@Autowired
	Turista unTurista;

	@Autowired
	ITuristasDAO turistaDAO; 
 
	
	@Override
	public void guardarTurista(Turista unTurista) {
		// TODO Auto-generated method stub
		turistaDAO.save(unTurista);
	}

	public List<Turista> obtenerTodosTuristas() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaDAO.findAll();
	}

	@Override
	public Turista crearTurista() {
		// TODO Auto-generated method stub
		return unTurista;
	}


	@Override
	public Turista encontrarUnTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findById(id).orElseThrow(()->new Exception("El Turista no existe"));
	}

	@Override
	public void eliminarTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAEliminar= turistaDAO.findById(id).orElseThrow(()->new Exception("El Turista no fue encontrado"));
		turistaDAO.delete(turistaAEliminar);
	}
	@Override
	public void modificarTurista(Turista turistaModificado) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAModificar = turistaDAO.findById(turistaModificado.getIdTurista()).orElseThrow(()->new Exception("El Turista no fue encontrado"));  
		cambiarTurista(turistaModificado, turistaAModificar);
		turistaDAO.save(turistaAModificar);
	}
	private void cambiarTurista (Turista desde, Turista hacia) {
	hacia.setNombre(desde.getNombre());
	hacia.setApellido(desde.getApellido());
	hacia.setPaisPro(desde.getPaisPro());
	hacia.setLatitud(desde.getLatitud());
	hacia.setLongitud(desde.getLongitud());
	}


}