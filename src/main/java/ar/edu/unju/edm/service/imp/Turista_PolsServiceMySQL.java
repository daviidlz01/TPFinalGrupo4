package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista_Pols;
import ar.edu.unju.edm.repository.ITurista_PolsDAO;
import ar.edu.unju.edm.service.ITurista_PolsService;


@Service
public class Turista_PolsServiceMySQL implements ITurista_PolsService{
	@Autowired
	ITurista_PolsDAO t_pDAO;
	@Autowired
	Turista_Pols t_p;
	@Override
	public void guardarTurista_pols(Turista_Pols valoracion) {
		t_pDAO.save(valoracion);
	}

	@Override
	public Turista_Pols crearTurista_pols() {
		return t_p;
	}

	@Override
	public List<Turista_Pols> obtenerTodosTurista_pols() {
		// TODO Auto-generated method stub
		return (List<Turista_Pols>) t_pDAO.findAll();
	}

	@Override
	public Turista_Pols encontrarTurista_pols(int id) throws Exception {
		// TODO Auto-generated method stub
		return t_pDAO.findById(id).orElseThrow(()->new Exception("El Comentario no fue encontrado"));
	}
}
