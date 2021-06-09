package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Turista_Pols;

@Repository
public interface ITurista_PolsDAO extends CrudRepository<Turista_Pols,Integer>{

}
