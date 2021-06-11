package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.imp.TuristasServiceMySQL;

@SpringBootApplication
public class TpFinalEquipo4Application implements CommandLineRunner{
	@Autowired
	Turista turista;
	@Autowired
	TuristasServiceMySQL turistaService;
	public static void main(String[] args) {
		SpringApplication.run(TpFinalEquipo4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//inicializo un turista para pruebas
			//turista.setApellido("Apellido");
			//turista.setEmail("correo@gmail");
			//turista.setNombre("Tester");
			//turista.setPaisPro("Argentina");
			//turista.setPassword("123456789");
			//turistaService.guardarTurista(turista);
	}

}
