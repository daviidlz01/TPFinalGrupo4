package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.repository.IPolDAO;
import ar.edu.unju.edm.service.IPolService;

@Service
public class PolServiceMySQL implements IPolService {
	@Autowired
	Pol unPol;
	@Autowired
	IPolDAO polDAO;

	@Override
	public Pol crearPol() {
		return unPol;
	}

	@Override
	public void guardarPol(Pol unPol) {
		polDAO.save(unPol);
	}

	@Override
	public List<Pol> obtenerTodosPols() {
		return (List<Pol>) polDAO.findAll();
	}

	@Override
	public void eliminarPol(Integer codigo) throws Exception {
		Pol polEliminar = polDAO.findByCodigo(codigo)
				.orElseThrow(() -> new Exception("El punto de interes a borrar NO existe"));
		polDAO.delete(polEliminar);
	}

	@Override
	public Pol encontrarUnPol(Integer codigo) throws Exception {
		return polDAO.findByCodigo(codigo).orElseThrow(() -> new Exception("El punto de interes NO existe"));
	}

	@Override
	public void modificarPol(Pol polModificado) throws Exception {
		Pol polAModificar = polDAO.findByCodigo(polModificado.getCodigo())
				.orElseThrow(() -> new Exception("El Cliente no fue encontrado"));
		cambiarPol(polModificado, polAModificar);
		polDAO.save(polModificado);
	}

	private void cambiarPol(Pol desde, Pol hacia) {
		hacia.setBarrio(desde.getBarrio());
		hacia.setDescripcion(desde.getDescripcion());
		hacia.setEtiqueta(desde.getEtiqueta());
		hacia.setLocalidad(desde.getLocalidad());
		hacia.setNombre(desde.getNombre());
		hacia.setNumeroCasa(desde.getNumeroCasa());
		hacia.setSitioWeb(desde.getSitioWeb());
	}

}
