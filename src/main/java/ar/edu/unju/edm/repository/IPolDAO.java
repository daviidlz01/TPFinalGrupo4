package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Pol;


@Repository
public interface IPolDAO extends CrudRepository<Pol,Integer>{
	public Optional<Pol> findByCodigo(Integer codigo);
}
